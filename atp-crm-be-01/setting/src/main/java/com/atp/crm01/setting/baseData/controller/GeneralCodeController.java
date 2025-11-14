package com.atp.crm01.setting.baseData.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atp.crm01.common.dto.response.ApiResponse;
import com.atp.crm01.common.exception.BadRequestException;
import com.atp.crm01.common.utils.ApiUtils;
import com.atp.crm01.common.utils.CommonUtils;
import com.atp.crm01.setting.baseData.dto.GeneralCodeRequestDTO;
import com.atp.crm01.setting.baseData.service.GeneralCodeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/base-data/general-code")
@RequiredArgsConstructor
@Slf4j
public class GeneralCodeController {
	private final GeneralCodeService generalCodeService;
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponse<?>> createCommonCode(@RequestBody GeneralCodeRequestDTO reqParams){
		if(CommonUtils.isEmptyData(reqParams))
			throw new BadRequestException("Params is invalid");
		
		var result = generalCodeService.createGeneralCode(reqParams);
		
		ApiResponse<?> dataResponse;
		HttpStatus httpStatus;
		if(result) {
			httpStatus = HttpStatus.CREATED;
			dataResponse = ApiUtils.buildApiResponse(null, httpStatus, "Create list general code successfully !") ;
		}else {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			dataResponse = ApiUtils.buildApiResponse(null, httpStatus, "Create list general code failed !") ;
		}
		log.debug("[GENERAL-CODE-CONTROLLER] - createCommonCode :: {} !",dataResponse.message());
		return new ResponseEntity<>(dataResponse, httpStatus);
	}
	
	@PostMapping("/list-by-common-code")
	public ResponseEntity<ApiResponse<?>> getListGeneralByCommonCode(@RequestBody GeneralCodeRequestDTO param){
		if(CommonUtils.isEmptyData(param))
			throw new BadRequestException("Params is invalid");
		
		var result = generalCodeService.getListGeneralByCommonCode(param);
		
		ApiResponse<?> dataResponse;
		HttpStatus httpStatus;
		if(!CommonUtils.isEmptyData(result)) {
			httpStatus = HttpStatus.OK;
			dataResponse = ApiUtils.buildApiResponse(result, httpStatus, "Get list general code successfully !") ;
		}else {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			dataResponse = ApiUtils.buildApiResponse(null, httpStatus, "Get list general code failed !") ;
		}

		log.debug("[GENERAL-CODE-CONTROLLER] - getListGeneralByCommonCode :: {} !",dataResponse.message());
		return new ResponseEntity<>(dataResponse, httpStatus);
	}
	
}
