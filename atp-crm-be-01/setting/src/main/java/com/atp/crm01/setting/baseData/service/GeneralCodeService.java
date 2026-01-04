package com.atp.crm01.setting.baseData.service;

import java.util.List;

import org.springframework.data.domain.PageImpl;

import com.atp.crm01.setting.baseData.dto.GeneralCodeRequestDTO;
import com.atp.crm01.setting.baseData.dto.GeneralCodeResponseDTO;

public interface GeneralCodeService {

	boolean createGeneralCode(GeneralCodeRequestDTO param) throws Exception;
//	boolean createGeneralCode(List<GeneralCodeRequestDTO> params);
	List<GeneralCodeResponseDTO> getListGeneralByCommonCode(GeneralCodeRequestDTO param);
}
