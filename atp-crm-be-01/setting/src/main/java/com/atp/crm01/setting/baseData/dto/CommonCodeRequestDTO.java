package com.atp.crm01.setting.baseData.dto;

import java.util.List;
import java.util.Map;

import com.atp.crm01.common.dto.request.BaseRequest;
import com.atp.crm01.setting.localeInput.dto.LocaleInputCodeDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonCodeRequestDTO{
	private int commonCodeNo;
	private String featureCodeNo;
	protected String codeTypeNo;
	protected String useStatusNo;
	private List<LocaleInputCodeDTO> localeInputCodes;
}
