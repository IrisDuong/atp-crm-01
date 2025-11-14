package com.atp.crm01.setting.baseData.dto;

import java.util.ArrayList;
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
public class GeneralCodeResponseDTO {
	private int commonCodeNo;
	private int generalCodeNo;
	private String featureCodeNo;
	private BaseCodeTypeEnums codeType;
	private BaseUseStatusEnums useStatus;
	private int localeCodeNo;
	private boolean isTree;
	private int treeHierLevel;
	private List<LocaleInputCodeDTO> localeInputCodes;
	private List<GeneralCodeResponseDTO> children;
	
	public GeneralCodeResponseDTO(int commonCodeNo, int generalCodeNo, String featureCodeNo, String codeTypeNo,String useStatusNo,int localeCodeNo,boolean isTree) {
		super();
		this.commonCodeNo = commonCodeNo;
		this.generalCodeNo = generalCodeNo;
		this.featureCodeNo = featureCodeNo;
		this.codeType = BaseCodeTypeEnums.buildFromCodeTypeNo(codeTypeNo);
		this.useStatus = BaseUseStatusEnums.buildFromStatusNo(useStatusNo);
		this.localeCodeNo = localeCodeNo;
		this.isTree = isTree;
	}
	public GeneralCodeResponseDTO(int commonCodeNo, int generalCodeNo, String featureCodeNo) {
		super();
		this.commonCodeNo = commonCodeNo;
		this.generalCodeNo = generalCodeNo;
		this.featureCodeNo = featureCodeNo;
	}
}
