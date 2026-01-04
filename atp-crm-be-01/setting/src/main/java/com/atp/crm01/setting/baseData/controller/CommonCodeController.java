package com.atp.crm01.setting.baseData.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atp.crm01.common.dto.response.ApiResponse;
import com.atp.crm01.common.enums.BaseCodeTypeEnums;
import com.atp.crm01.common.exception.BadRequestException;
import com.atp.crm01.common.exception.DataNotFoundException;
import com.atp.crm01.common.utils.ApiUtils;
import com.atp.crm01.common.utils.CommonUtils;
import com.atp.crm01.setting.baseData.dto.CommonCodeRequestDTO;
import com.atp.crm01.setting.baseData.dto.CommonCodeResponseDTO;
import com.atp.crm01.setting.baseData.service.CommonCodeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/base-data/common-code")
@RequiredArgsConstructor
@Slf4j
public class CommonCodeController {
//	private final Logger log = LoggerFactory.getLogger(CommonCodeController.class);
	
	private final CommonCodeService commonCodeService;
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponse<Boolean>> createCommonCode(@RequestBody CommonCodeRequestDTO reqParams) throws Exception{
		if(CommonUtils.isEmptyData(reqParams))
			throw new BadRequestException("Params is invalid");
		
		var result = commonCodeService.createCommonCode(reqParams);
		ResponseEntity<ApiResponse<Boolean>> dataResponse =  ApiUtils.buildAPIResponse(result, HttpStatus.CREATED, "Create common code successfully");
		log.info("[COMMON-CODE-CONTROLLER] - createCommonCode :: {} !",dataResponse.getBody().message());
		return dataResponse;
	}
	
	@PostMapping("/search")
	public ResponseEntity<ApiResponse<List<CommonCodeResponseDTO>>> searchCommonCode(@RequestBody CommonCodeRequestDTO reqParams){
		List<CommonCodeResponseDTO> result = commonCodeService.searchCommonCodes(reqParams);
		if(CommonUtils.isEmptyData(result))
			throw new DataNotFoundException("No data");
		
		ResponseEntity<ApiResponse<List<CommonCodeResponseDTO>>> dataResponse = ApiUtils.buildAPIResponse(result, HttpStatus.OK, "Get list general code successfully !");
		log.info("[COMMON-CODE-CONTROLLER] - createCommonCode :: {} !",dataResponse.getBody().message());
		return dataResponse;
	}
	
//	@GetMapping("/detail/{commonCodeNo}")
//	public ResponseEntity<ApiResponse<CommonCodeResponseDTO>> searchCommonCode(@PathVariable Integer commonCodeNo){
//		CommonCodeResponseDTO result = CommonCodeResponseDTO.builder()
//				.commonCodeNo(commonCodeNo)
//				.featureCodeNo("IN")
//				.codeType(BaseCodeTypeEnums.MULTI)
//				.build();
//		if(CommonUtils.isEmptyData(result))
//			throw new DataNotFoundException("No data");
//		
//		ResponseEntity<ApiResponse<CommonCodeResponseDTO>> dataResponse = ApiUtils.buildAPIResponse(result, HttpStatus.OK, "Get list general code successfully !");
//		log.info("[COMMON-CODE-CONTROLLER] - createCommonCode :: {} !",dataResponse.getBody().message());
//		return dataResponse;
//	}
}
