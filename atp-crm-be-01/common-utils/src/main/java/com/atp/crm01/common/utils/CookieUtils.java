package com.atp.crm01.common.utils;

import java.time.Duration;

import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpResponse;

public class CookieUtils {

	public static final String ACCESS_TOKEN_COOKIE_NAME = "CAT";
	public static final String REFRESH_TOKEN_COOKIE_NAME = "CRT";
	
	public static void setCookie(ServerHttpResponse respopnse, String cookieName, String value, long maxAge) {
		ResponseCookie cookie = ResponseCookie.from(cookieName, value)
				.path("/")
				.httpOnly(true)
				.secure(false)
				.maxAge(Duration.ofMillis(maxAge))
				.sameSite("Lax")
				.build();
		respopnse.addCookie(cookie);
	}
}
