package com.atp.crm01.setting.baseData.dto;

import java.util.List;

import com.atp.crm01.common.enums.BaseCodeTypeEnums;
import com.atp.crm01.common.enums.BaseUseStatusEnums;
import com.atp.crm01.setting.localeInput.dto.LocaleInputCodeDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonCodeResponseDTO {
	private int commonCodeNo;
	private String featureCodeNo;
	private BaseCodeTypeEnums codeType;
	private BaseUseStatusEnums useStatus;
	private List<LocaleInputCodeDTO> localeInputCodes;
	
}
