package com.atp.crm01.setting.localeInput.dto;

import com.atp.crm01.setting.localeInput.entity.LocaleInputCodePK;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocaleInputCodeDTO {

	private String langCode;
	private Integer localeCodeNo;
	private String codeName;
}
