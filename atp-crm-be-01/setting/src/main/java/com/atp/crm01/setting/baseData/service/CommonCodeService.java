package com.atp.crm01.setting.baseData.service;

import java.util.List;

import org.springframework.data.domain.PageImpl;

import com.atp.crm01.setting.baseData.dto.CommonCodeRequestDTO;
import com.atp.crm01.setting.baseData.dto.CommonCodeResponseDTO;
import com.atp.crm01.setting.baseData.entity.CommonCode;

public interface CommonCodeService {

	boolean createCommonCode(CommonCodeRequestDTO param);
	List<CommonCodeResponseDTO> searchCommonCodes(CommonCodeRequestDTO param);
}
