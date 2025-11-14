package com.atp.crm01.common.dto.response;

import java.time.LocalDateTime;

public record ExceptionResponse(String errCode, String errMessage, String timeStamp) {

}
