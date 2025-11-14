package com.atp.crm01.setting.baseData.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atp.crm01.common.enums.BaseCodeTypeEnums;
import com.atp.crm01.common.enums.BaseUseStatusEnums;
import com.atp.crm01.common.exception.BadRequestException;
import com.atp.crm01.common.utils.CommonUtils;
import com.atp.crm01.common.utils.JpaUtils;
import com.atp.crm01.setting.baseData.dto.CommonCodeRequestDTO;
import com.atp.crm01.setting.baseData.dto.CommonCodeResponseDTO;
import com.atp.crm01.setting.baseData.dto.GeneralCodeResponseDTO;
import com.atp.crm01.setting.baseData.entity.CommonCode;
import com.atp.crm01.setting.baseData.repo.CommonCodeRepo;
import com.atp.crm01.setting.baseData.service.CommonCodeService;
import com.atp.crm01.setting.localeInput.dto.LocaleInputCodeDTO;
import com.atp.crm01.setting.localeInput.entity.LocaleInputCode;
import com.atp.crm01.setting.localeInput.entity.LocaleInputCodePK;
import com.atp.crm01.setting.localeInput.repo.LocaleInputCodeRepo;
import com.atp.crm01.setting.localeInput.service.LocaleInputCodeService;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommonCodeServiceImpl implements CommonCodeService{
	private final CommonCodeRepo commonCodeRepo;
	private final LocaleInputCodeService localeInputCodeService;

	@Override
	@Transactional
	public boolean createCommonCode(CommonCodeRequestDTO param) {
		
		if(commonCodeRepo.existsById(param.getCommonCodeNo())) {
			log.info("DUPLICATE COMMON CODE NO  = {}", param.getCommonCodeNo());
			throw new BadRequestException("Duplicate common code");
		}
		
		List<LocaleInputCode> localeInputCodes = param.getLocaleInputCodes().stream()
				.map(localeInputCodeService::buildEntityFromDto)
				.toList();
		localeInputCodeService.saveListLocaleInputCode(new ArrayList<>(localeInputCodes));
		
		CommonCode commonCodeToSave = new  CommonCode();
		commonCodeToSave.setCodeTypeNo(param.getCodeTypeNo());
		commonCodeToSave.setUseStatusNo(param.getUseStatusNo());
		commonCodeToSave.setFeatureCodeNo(param.getFeatureCodeNo());
		commonCodeToSave.setLocaleCodeNo(localeInputCodes.get(0).getId().getLocaleCodeNo());
		try {
			commonCodeRepo.save(commonCodeToSave);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<CommonCodeResponseDTO> searchCommonCodes(CommonCodeRequestDTO param) {
		Specification<CommonCode> specSearch = (root,query,cb)->{
			List<Predicate> predicates = new ArrayList<Predicate>();
			predicates.add(cb.equal(root.get("featureCodeNo"),param.getFeatureCodeNo()));
//			predicates.add(cb.or(cb.isNull(root.get("commonCodeNo")), cb.like(cb.lower(root.get("commonCodeNo")), JpaUtils.likeParamsFormater(param.getCommonCodeNo(), false))));
			predicates.add(cb.or(cb.isNull(root.get("codeTypeNo")), cb.equal(root.get("codeTypeNo"), param.getCodeTypeNo())));
			predicates.add(cb.or(cb.isNull(root.get("useStatusNo")), cb.equal(root.get("useStatusNo"), param.getUseStatusNo())));
			return cb.and(predicates.toArray(new Predicate[0]));
		};
		var dataResult = commonCodeRepo.findAll(specSearch);
		
		List<Integer> localeCodeParams = dataResult.stream()
				.map(CommonCode::getLocaleCodeNo).toList();
		List<LocaleInputCode> listLocaleInputCodes = localeInputCodeService.findByListLocaleCode(localeCodeParams);
		
		return dataResult.stream()
				.map(item->  {
					List<LocaleInputCodeDTO> listLocaleInputCodesDTO = listLocaleInputCodes.stream()
							.filter(localeInputCode-> localeInputCode.getId().getLocaleCodeNo() == item.getLocaleCodeNo())
							.map(localeInputCodeService::buildDTOFromEntity).toList();
					return CommonCodeResponseDTO.builder()
					.commonCodeNo(item.getCommonCodeNo())
					.featureCodeNo(item.getFeatureCodeNo())
					.codeType(BaseCodeTypeEnums.buildFromCodeTypeNo(item.getCodeTypeNo()))
					.useStatus(BaseUseStatusEnums.buildFromStatusNo(item.getUseStatusNo()))
					.localeInputCodes(listLocaleInputCodesDTO)
					.build();
				}
		).toList();
	}

}
