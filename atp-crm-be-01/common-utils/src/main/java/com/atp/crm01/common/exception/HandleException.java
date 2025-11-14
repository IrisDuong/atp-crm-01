package com.atp.crm01.common.exception;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.atp.crm01.common.utils.DateFormats;
import com.atp.crm01.common.dto.response.ExceptionResponse;

@RestControllerAdvice
public class HandleException {

	private ResponseEntity<ExceptionResponse> doResponse(HttpStatus httpStatus, String message){
		ExceptionResponse response = new ExceptionResponse(httpStatus.toString(), message, getErrorTimeStamep());
		return new ResponseEntity<ExceptionResponse>(response,httpStatus);
		
	}
	
	private String getErrorTimeStamep() {
		return LocalDateTime.now().format(DateFormats.VN_DATE_TIME.getFormatter());
	}
	
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleDataNotFoundException(DataNotFoundException ex){
		System.out.println("{EXCEPTION :: DataNotFoundException} = "+ex.getMessage());
		return doResponse(HttpStatus.NOT_FOUND, ex.getMessage());
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestException ex){
		System.out.println("{EXCEPTION :: BadRequestException} = "+ex.getMessage());
		return doResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
	}
	
	@ExceptionHandler(UnAuthorizedException.class)
	public ResponseEntity<ExceptionResponse> handleUnAuthorizedExceptionn(UnAuthorizedException ex){
		System.out.println("{EXCEPTION :: UnAuthorizedException} = "+ex.getMessage());
		return doResponse(HttpStatus.UNAUTHORIZED, "You are unauthorized !");
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleCommonException(Exception ex){
		System.out.println("{EXCEPTION :: Common Exception} = "+ex.getMessage());
		return doResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	}
}
