package com.atp.crm01.setting.localeInput.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.atp.crm01.common.service.CustomDataConverter;
import com.atp.crm01.setting.localeInput.dto.LocaleInputCodeDTO;
import com.atp.crm01.setting.localeInput.entity.LocaleInputCode;
import com.atp.crm01.setting.localeInput.entity.LocaleInputCodePK;

public interface LocaleInputCodeService extends CustomDataConverter<LocaleInputCode, LocaleInputCodeDTO>{
	List<LocaleInputCode> findByListLocaleCode(List<Integer> localeCodeParams);
	boolean saveListLocaleInputCode(List<LocaleInputCode> entities);
	boolean deleteByIds(List<LocaleInputCodePK> ids);
	Integer findMaxLocaleCode();
	
}
