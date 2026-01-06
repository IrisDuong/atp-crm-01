package com.atp.crm01.gateway.config;

import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.atp.crm01.common.utils.CommonUtils;
import com.atp.crm01.common.utils.CookieUtils;

import reactor.core.publisher.Mono;

@Component
public class CookieJwtFilter implements WebFilter{

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		HttpCookie cookie = exchange.getRequest().getCookies()
				.getFirst(CookieUtils.ACCESS_TOKEN_COOKIE_NAME);
		if(CommonUtils.isEmptyData(cookie))
			return chain.filter(exchange);
		
		String accessToken = cookie.getValue();
		ServerHttpRequest mutatedReq = exchange.getRequest()
				.mutate().header(HttpHeaders.AUTHORIZATION, "Bearer "+accessToken)
				.build();
		return chain.filter(exchange.mutate().request(mutatedReq).build());
	}

}
