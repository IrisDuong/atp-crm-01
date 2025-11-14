package com.atp.crm01.common.utils;

import java.util.List;
import java.util.Optional;

public class CommonUtils {

	public static  boolean isEmptyData(Object data) {
		if(data == null) {
			return true;
		}else if(data instanceof String s) {
			return s.isEmpty();
		}
		return false;
	}
	
	public static String buildFullURL(String host, String port, String...others) {
		return host.concat(":").concat(port);
	}
	public static Integer getDefaultNumber(Integer number, int defaultNumber) {
		return (number instanceof Integer && number > 0) ? number : defaultNumber;
	}
}
