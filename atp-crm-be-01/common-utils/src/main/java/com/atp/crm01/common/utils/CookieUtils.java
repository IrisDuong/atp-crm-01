package com.atp.crm01.common.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtils {

	public static final String ACCESS_TOKEN_COOKIE_NAME = "C_AT";
	public static final String REFRESH_TOKEN_COOKIE_NAME = "C_RT";
	
	public static void setCookie(HttpServletResponse respopnse, String cookieName, String value, int age) {
		Cookie cookie = new Cookie(cookieName, value);
		cookie.setPath("/");
		cookie.setSecure(false);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(age);
		cookie.setDomain("localhost");
		respopnse.addCookie(cookie);
	}
}
