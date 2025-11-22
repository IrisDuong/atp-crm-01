package com.atp.crm01.setting.baseData.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atp.crm01.common.enums.BaseCodeTypeEnums;
import com.atp.crm01.common.enums.BaseUseStatusEnums;
import com.atp.crm01.common.exception.DataNotFoundException;
import com.atp.crm01.common.utils.CommonUtils;
import com.atp.crm01.setting.baseData.dto.GeneralCodeRequestDTO;
import com.atp.crm01.setting.baseData.dto.GeneralCodeResponseDTO;
import com.atp.crm01.setting.baseData.entity.CommonCode;
import com.atp.crm01.setting.baseData.entity.GeneralCode;
import com.atp.crm01.setting.baseData.entity.GeneralCodeID;
import com.atp.crm01.setting.baseData.repo.CommonCodeRepo;
import com.atp.crm01.setting.baseData.repo.GeneralCodeRepo;
import com.atp.crm01.setting.baseData.service.GeneralCodeService;
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
public class GeneralCodeServiceImpl implements GeneralCodeService{
	private final GeneralCodeRepo generalCodeRepo;
	private final LocaleInputCodeService localeInputCodeService;
	private final CommonCodeRepo commonCodeRepo;
	/**
	 * Create single general code
	 */
	@Override
	@Transactional
	public boolean createGeneralCode(GeneralCodeRequestDTO param) {
		
		try {
			// find the owner common code
			CommonCode ownerCommonCode = commonCodeRepo.findById(param.getCommonCodeNo())
					.orElseThrow(()-> new DataNotFoundException("Common code not found"));

			boolean isTree = false;
			Integer treeLevel = null;
			if(BaseCodeTypeEnums.TREE.getcodeTypeNo().equals(ownerCommonCode.getCodeTypeNo())) {
				isTree = true;
			    treeLevel = 1;
			}
			
			GeneralCode rootGeneralCode = this.buildGeneralCode(param,ownerCommonCode,null,isTree,treeLevel);
			param.getChildren().forEach(item-> this.buildGeneralCode(item,ownerCommonCode,rootGeneralCode,false,2));
			return true;
		} catch (Exception e) {
			log.error("[GeneralCodeServiceImpl] - create SINGLE  general code failed");
			return false;
		}
	}

	/**
	 * Build single general code from request param
	 */
	private GeneralCode buildGeneralCode(GeneralCodeRequestDTO param, CommonCode ownerCommonCode, GeneralCode parentGeneralCode,boolean isTree,int treeLevel) {
		
		//  Create locale input code for multi-language
		if(param.getGeneralCodeNo() > 0) {
			// delete all locale before create new all to void complicate
			List<LocaleInputCodePK> existedLocaleInputCodeIds = param.getLocaleInputCodes().stream()
					.map(item-> new LocaleInputCodePK(item.getLangCode(), item.getLocaleCodeNo())).toList();
			localeInputCodeService.deleteByIds(existedLocaleInputCodeIds);
		}
		List<LocaleInputCode> newLocaleInputCodes = param.getLocaleInputCodes().stream().map(localeInputCodeService::buildEntityFromDto).toList();
		localeInputCodeService.saveListLocaleInputCode(new ArrayList<>(newLocaleInputCodes));


		// find max general code and create new
		Integer maxGeneralCodeNo = generalCodeRepo.findMaxGeneralcode(param.getCommonCodeNo(),param.getFeatureCodeNo(), BaseUseStatusEnums.USE.getUseStatusNo());
		GeneralCode generalCode = new GeneralCode();
		generalCode.setId(GeneralCodeID.builder()
				.commonCodeNo(ownerCommonCode.getCommonCodeNo())
				.generalCodeNo(CommonUtils.getDefaultNumber(maxGeneralCodeNo, 0)+1)
				.build()
		);
		generalCode.setFeatureCodeNo(param.getFeatureCodeNo());
		generalCode.setCodeTypeNo(param.getCodeTypeNo());
		generalCode.setUseStatusNo(param.getUseStatusNo());
		generalCode.setLocaleCodeNo(newLocaleInputCodes.get(0).getId().getLocaleCodeNo());
		generalCode.setOwnerCommonCode(ownerCommonCode);
		generalCode.setTree(isTree);
		generalCode.setParent(parentGeneralCode);
		generalCode.setTreeLevel(treeLevel);
		
		return generalCodeRepo.save(generalCode);
	}

	/**
	 * Get list general code from common code & group code
	 */
	@Override
	@Transactional
	public List<GeneralCodeResponseDTO> getListGeneralByCommonCode(GeneralCodeRequestDTO param) {
		try {

			List<GeneralCodeResponseDTO> dataResult = generalCodeRepo.getListGeneralByCommonCode(param);
			
			List<Integer> localeCodeParams = dataResult.stream()
					.map(GeneralCodeResponseDTO::getLocaleCodeNo).toList();
			List<LocaleInputCode> listLocaleInputCodes = localeInputCodeService.findByListLocaleCode(localeCodeParams);
			dataResult.stream().forEach(generalCode->{
				List<LocaleInputCodeDTO> listLocaleInputCodesDTO = listLocaleInputCodes.stream()
						.filter(item-> item.getId().getLocaleCodeNo() == generalCode.getLocaleCodeNo())
						.map(localeInputCodeService::buildDTOFromEntity)
						.toList();
				generalCode.setLocaleInputCodes(listLocaleInputCodesDTO);
			});
			return dataResult;
		} catch (Exception e) {
			return null;
		}
	}
}
