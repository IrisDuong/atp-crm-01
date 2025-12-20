package com.atp.crm01.gateway.config;

import java.net.URI;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.web.server.DefaultServerRedirectStrategy;
import org.springframework.security.web.server.ServerRedirectStrategy;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;

import com.atp.crm01.common.utils.SecurityUtils;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class Oauth2AuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler{

//	private final OAuth2AuthorizedClientService clientService;
	private final ServerRedirectStrategy redirectStrategy;
	private final ReactiveOAuth2AuthorizedClientService auth2AuthorizedClientService;

	@Value("${host.main}")
	private String mainHost;

	@Value("${gateway.client-id}")
	private String gatewayClientId;
	
	public Oauth2AuthenticationSuccessHandler(ReactiveOAuth2AuthorizedClientService auth2AuthorizedClientService) {
		super();
		this.auth2AuthorizedClientService = auth2AuthorizedClientService;
		this.redirectStrategy = new DefaultServerRedirectStrategy();
	}

	@Override
	public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
		log.info("[Oauth2AuthenticationSuccessHandler] - handle login successfully from spring AS");
		ServerWebExchange exchange = webFilterExchange.getExchange();
		return authorizedClient(exchange,authentication)
				.flatMap(client->{
					String accessToken = client.getAccessToken().getTokenValue();
					
					ResponseCookie atCookie = ResponseCookie.from(SecurityUtils.COOKIE_NAME_ACCESS_TOKEN, accessToken)
							.path("/")
							.httpOnly(true)
							.secure(true)
							.maxAge(Duration.ofMinutes(30))
							.build();
					
					URI redirectURI = URI.create(new StringBuilder(mainHost).append(":").append("3000").toString());
					return redirectStrategy.sendRedirect(exchange, redirectURI);
				});
	}
	
	private Mono<OAuth2AuthorizedClient> authorizedClient(ServerWebExchange exchange, Authentication authentication){
		return auth2AuthorizedClientService.loadAuthorizedClient(gatewayClientId, authentication.getName());
	}
}
