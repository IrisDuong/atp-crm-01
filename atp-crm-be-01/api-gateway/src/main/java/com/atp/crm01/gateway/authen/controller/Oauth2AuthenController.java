package com.atp.crm01.gateway.authen.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import org.springframework.web.util.UriComponentsBuilder;
import com.atp.crm01.common.dto.response.ApiResponse;
import com.atp.crm01.common.utils.ApiUtils;
import com.atp.crm01.common.utils.CommonUtils;
import com.atp.crm01.common.utils.CookieUtils;
import com.atp.crm01.gateway.authen.dto.AuthenticatedUser;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/oauth2-authen")
public class Oauth2AuthenController {

	@Value("${app.baseUrl.auth-server}")
	private String authServerHost;

	@Value("${app.baseUrl.gateway}")
	private String gatewayHost;
	
	@GetMapping("/oidcLogout/success")
	public Mono<Void> handleLogoutSuccess(ServerWebExchange exchange){
	    return exchange.getSession()
	    		.flatMap(WebSession::invalidate)
	    		.contextWrite(ReactiveSecurityContextHolder.clearContext())
	    		.then(Mono.fromRunnable(()->{
	    			CookieUtils.setCookie(exchange.getResponse(), CookieUtils.ACCESS_TOKEN_COOKIE_NAME, "", 0);
	    			exchange.getResponse().setStatusCode(HttpStatus.FOUND);
	    			exchange.getResponse().getHeaders().setLocation(URI.create("http://localhost:3000?isLogout=true"));
	    		}));
	}

	@GetMapping("/oidcLogout/handle")
	public Mono<Void> handlerLogout(ServerHttpResponse response){
		String asLogoutredirect = UriComponentsBuilder
				.fromUriString(authServerHost.concat("/logout"))
				.queryParam("post_logout_redirect_uri", gatewayHost.concat("/oauth2-authen/oidcLogout/success"))
				.build().toUriString();
		response.setStatusCode(HttpStatus.FOUND);
		response.getHeaders().setLocation(URI.create(asLogoutredirect));
		return response.setComplete();
	}
	
	@GetMapping("/verify")
	public Mono<ResponseEntity<ApiResponse<AuthenticatedUser>>> verifyAuthen(@AuthenticationPrincipal OAuth2User principal){
		if(!CommonUtils.isEmptyData(principal)) {
				String sub = principal.getAttribute("sub");
				String name = principal.getAttribute("name");
				String email = principal.getAttribute("email");
				String picture = principal.getAttribute("picture");
				AuthenticatedUser authenticatedUser = new AuthenticatedUser(sub, name, email, "", picture);
				ResponseEntity<ApiResponse<AuthenticatedUser>> apiResponse = ApiUtils.buildAPIResponse(authenticatedUser, HttpStatus.OK, "Logged user has found");
				return Mono.just(apiResponse);
		}else {
			return Mono.just(ApiUtils.buildAPIResponse(null, HttpStatus.UNAUTHORIZED, "User has no logged"));
		}
	}
}
