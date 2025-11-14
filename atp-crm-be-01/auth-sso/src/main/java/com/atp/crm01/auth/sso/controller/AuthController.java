package com.atp.crm01.auth.sso.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atp.crm01.auth.sso.config.TokenProvider;
import com.atp.crm01.auth.sso.dto.AuthenResponse;
import com.atp.crm01.common.utils.CookieUtils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/authen")
@Slf4j
public class AuthController {

	@Autowired
	TokenProvider tokenProvider;
	
	@GetMapping("/test")
	public void login(){
		log.info("test auth");
	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response
			, @RequestParam(value = "redirect_uri" ,defaultValue = "") String redirectUri) throws IOException{
		CookieUtils.setCookie(response, CookieUtils.ACCESS_TOKEN_COOKIE_NAME, "", 0);
		CookieUtils.setCookie(response, CookieUtils.REFRESH_TOKEN_COOKIE_NAME, "", 0);
		return ResponseEntity.ok("Logout success");
	}
	
	@PostMapping("/info")
	public ResponseEntity<?> logout(@AuthenticationPrincipal OAuth2User principal) throws IOException{
		AuthenResponse authenResponse;
		if(principal != null) {
			authenResponse= new AuthenResponse(principal.getAttribute("email"), principal.getAttribute("id"), principal.getAttribute("name"), principal.getAttribute("pic"));
		}else {
			authenResponse= new AuthenResponse("","","","");
		}
		return ResponseEntity.ok(authenResponse);
	}
}
