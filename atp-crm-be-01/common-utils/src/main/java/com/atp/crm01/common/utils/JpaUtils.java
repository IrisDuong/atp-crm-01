package com.atp.crm01.common.utils;

public class JpaUtils {

	public static String likeParamsFormater(String param, boolean isLower) {
		return "%".concat(isLower ? param.toLowerCase() : param.toUpperCase()).concat("%");
	}
}
