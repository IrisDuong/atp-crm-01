package com.atp.crm01.gateway.config;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.atp.crm01.common.utils.CookieUtils;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class Oauth2AuthenSuccessHandler implements ServerAuthenticationSuccessHandler{
	
	private final ReactiveOAuth2AuthorizedClientService reactiveOAuth2AuthorizedClientService;
	
	@Value("${sec.oauth2.client.app.access-token.ttl}")
	private long appAccessTokenTTL;
	
	
	public Oauth2AuthenSuccessHandler(ReactiveOAuth2AuthorizedClientService reactiveOAuth2AuthorizedClientService) {
		super();
		this.reactiveOAuth2AuthorizedClientService = reactiveOAuth2AuthorizedClientService;
	}



	@Override
	public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
		try {

			log.info("[Oauth2AuthenSuccessHandler] - start...............");
			ServerWebExchange exchange = webFilterExchange.getExchange();
			ServerHttpResponse response = exchange.getResponse();
			OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
			String principalName = authentication.getName();
			String clientRegistrationId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
			return reactiveOAuth2AuthorizedClientService.loadAuthorizedClient(clientRegistrationId, principalName)
					.flatMap(authorizedClient->{
						OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
						String accessTokenValue = accessToken.getTokenValue();
						log.info("[Oauth2AuthenSuccessHandler] - authen successfully with [principalName] = {} ; [clientRegistrationId] = {}",principalName,clientRegistrationId);
						
						CookieUtils.setCookie(response, CookieUtils.ACCESS_TOKEN_COOKIE_NAME, accessTokenValue, appAccessTokenTTL);
						
						response.setStatusCode(HttpStatus.FOUND);
						response.getHeaders().setLocation(URI.create("http://localhost:3000"));
						return response.setComplete();
					});
		} catch (Exception e) {
			log.error("[Oauth2AuthenSuccessHandler] - authenfalied...............");
			return Mono.empty();
		}
	}

}
