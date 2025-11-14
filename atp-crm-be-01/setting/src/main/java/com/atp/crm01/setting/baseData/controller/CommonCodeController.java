package com.atp.crm01.setting.baseData.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atp.crm01.common.dto.response.ApiResponse;
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
	private final CommonCodeService commonCodeService;
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponse<?>> createCommonCode(@RequestBody CommonCodeRequestDTO reqParams){
		if(CommonUtils.isEmptyData(reqParams))
			throw new BadRequestException("Params is invalid");
		
		var result = commonCodeService.createCommonCode(reqParams);
		
		ApiResponse<?> dataResponse;
		HttpStatus httpStatus;
		if(result) {
			httpStatus = HttpStatus.CREATED;
			dataResponse = ApiUtils.buildApiResponse(null, httpStatus, "Create list common code successfully !") ;
		}else {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			dataResponse = ApiUtils.buildApiResponse(null, httpStatus, "Create list common code failed !") ;
		}
		log.debug("[COMMON-CODE-CONTROLLER] - createCommonCode :: {} !",dataResponse.message());
		return new ResponseEntity<>(dataResponse, httpStatus);
	}
	
	@PostMapping("/search")
	public ResponseEntity<ApiResponse<?>> searchCommonCode(@RequestBody CommonCodeRequestDTO reqParams){
		List<CommonCodeResponseDTO> result = commonCodeService.searchCommonCodes(reqParams);
		if(CommonUtils.isEmptyData(result))
			throw new DataNotFoundException("No data");
		
		ApiResponse<?> dataResponse = ApiUtils.buildApiResponse(result, HttpStatus.OK, "Get list general code successfully !") ;
		log.debug("[COMMON-CODE-CONTROLLER] - createCommonCode :: {} !",dataResponse.message());
		return new ResponseEntity<>(dataResponse, HttpStatus.OK);
	}
}
