package com.atp.crm01.common.utils;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.atp.crm01.common.dto.response.ApiResponse;

public class ApiUtils<T> {

	public static <T> ResponseEntity<ApiResponse<T>> buildAPIResponse(T data, HttpStatus httpStatus, String message){
		return new ResponseEntity<ApiResponse<T>>(new ApiResponse<T>(message, data, httpStatus.value(), DateUtils.getNow(DateFormats.ISO_DATE_TIME)), httpStatus);
	}
}
