package com.atp.crm01.common.utils;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.atp.crm01.common.dto.response.ApiResponse;

public class ApiUtils<T> {

	public static <T> ApiResponse<T> buildApiResponse(T data, HttpStatus httpStatus, String message){
//		DataResponse.builder()
//		.message(message)
//		.data(data)
//		.httpStatusCode(httpStatus.value())
//		.timeStamp(LocalDateTime.now())
//		.build();
		return new ApiResponse<T>(message, data, httpStatus.value(), LocalDateTime.now());
	}
}
