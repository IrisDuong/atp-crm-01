package com.atp.crm01.auth.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atp.crm01.auth.dto.AuthenticatedUser;
import com.atp.crm01.common.dto.response.ApiResponse;
import com.atp.crm01.common.utils.ApiUtils;

@RestController
@RequestMapping("/authen")
public class AuthenController {

	@GetMapping("/info")
	public ResponseEntity<ApiResponse<AuthenticatedUser>> authenInfo(@AuthenticationPrincipal OAuth2User oAuth2User){
		if(Optional.ofNullable(oAuth2User).isPresent()) {
			AuthenticatedUser authenticatedUser = AuthenticatedUser.builder()
					.name(oAuth2User.getAttribute("name"))
					.email(oAuth2User.getAttribute("email"))
					.avatar(oAuth2User.getAttribute("picture"))
					.build();
			return ApiUtils.buildAPIResponse(authenticatedUser, HttpStatus.OK, "Logged user has found");
		}
		return ApiUtils.buildAPIResponse(null, HttpStatus.NO_CONTENT, "Logged user has not found");
	}
}
