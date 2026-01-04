package com.atp.crm01.common.utils;

import java.time.LocalDateTime;

public class DateUtils {
	
	public static String convertDateTimeToString(LocalDateTime param, DateFormats formatter) {
		return param.format(formatter.getFormatter());
	}
	
	public static String getNow(DateFormats formatter) {
		return LocalDateTime.now().format(formatter.getFormatter());
	}
	

	public static LocalDateTime getNow() {
		return LocalDateTime.now();
	}
}
