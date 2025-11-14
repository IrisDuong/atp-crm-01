package com.atp.crm01.common.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record ApiResponse<T>(String message,T data,int httpStatusCode,LocalDateTime timeStamp) {

}
