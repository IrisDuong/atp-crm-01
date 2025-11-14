package com.atp.crm01.auth.sso.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.atp.crm01.common.utils.CookieUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Oauth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	@Autowired
	TokenProvider tokenProvider;
	

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException { 
		// TODO Auto-generated method stub
		if(authentication.getPrincipal() instanceof OAuth2User oAuth2User) {
			Map<String, Object> attributes = oAuth2User.getAttributes();
			String email = (String) attributes.get("email");
			String pic ;
			try {
				pic = (String) attributes.get("pic");
			} catch (NullPointerException e) {
				// TODO: handle exception
				pic= "";
			}
			Map<String, String> claims = new HashMap<String, String>();
			claims.put("email", email);
			claims.put("name", (String) attributes.get("name"));
			claims.put("pic", pic);
			
			String token = tokenProvider.generateToken(email, claims);
			CookieUtils.setCookie(response, CookieUtils.ACCESS_TOKEN_COOKIE_NAME, token, (int) tokenProvider.atExpiry / 1000);
			getRedirectStrategy().sendRedirect(request, response, "http://localhost:8080");
			log.info("{Oauth2 Authentication} - Successfully with Gmail");
		}
	}
}
