package com.atp.crm01.common.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

public class UriMapping {

	public static final List<String> PUBLIC_ENDPOINTS = Arrays.asList(
	        "/", "/auth/", "/login/", "/oauth2/", "/logout", "/static/", 
	        "/favicon.ico", "/public/");


	public static final List<String> UNAUTHORIZED_ENDPOINTS = Arrays.asList("/api/");
	public static final String HOST = "http://localhost";
	
}
