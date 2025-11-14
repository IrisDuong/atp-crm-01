package com.atp.crm01.setting.localeInput.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.atp.crm01.common.utils.CommonUtils;
import com.atp.crm01.setting.localeInput.dto.LocaleInputCodeDTO;
import com.atp.crm01.setting.localeInput.entity.LocaleInputCode;
import com.atp.crm01.setting.localeInput.entity.LocaleInputCodePK;
import com.atp.crm01.setting.localeInput.repo.LocaleInputCodeRepo;
import com.atp.crm01.setting.localeInput.service.LocaleInputCodeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocaleInputCodeServiceImpl implements LocaleInputCodeService{
	private final LocaleInputCodeRepo localeInputCodeRepo;

	@Override
	public List<LocaleInputCode> findByListLocaleCode(List<Integer> localeCodeNoParams) {
		return localeInputCodeRepo.findById_LocaleCodeNoIn(localeCodeNoParams);
	}

	@Override
	public boolean saveListLocaleInputCode(List<LocaleInputCode> entities) {
		try {
			localeInputCodeRepo.saveAllAndFlush(entities);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public Integer findMaxLocaleCode() {
		return localeInputCodeRepo.findMaxLocaleCode();
	}


	@Override
	public boolean deleteByIds(List<LocaleInputCodePK> ids) {
		try {
			localeInputCodeRepo.deleteAllByIdInBatch(ids);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public LocaleInputCodeDTO buildDTOFromEntity(LocaleInputCode e) {
		return LocaleInputCodeDTO.builder()
				.langCode(e.getId().getLangCode())
				.localeCodeNo(e.getId().getLocaleCodeNo())
				.codeName(e.getCodeName())
				.build();
	}

	@Override
	public LocaleInputCode buildEntityFromDto(LocaleInputCodeDTO d) {
		Integer maxLocaleCodeNo = localeInputCodeRepo.findMaxLocaleCode();
		LocaleInputCodePK localeInputCodeId = new LocaleInputCodePK();
		localeInputCodeId.setLangCode(d.getLangCode());
		localeInputCodeId.setLocaleCodeNo(maxLocaleCodeNo);
		return LocaleInputCode.builder()
				.id(localeInputCodeId)
				.codeName(d.getCodeName())
				.build();
	}

}
