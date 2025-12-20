package com.atp.crm01.gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.atp.crm01.common.utils.HttpConst;
import com.atp.crm01.common.utils.SecurityUtils;

import reactor.core.publisher.Mono;

@Component
public class CookieToAuthFilter implements GlobalFilter{

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		return Mono.justOrEmpty(exchange.getRequest().getCookies().getFirst("GATEWAY_JWT"))
				.map(cookie -> cookie.getValue())
				.map(token -> exchange.mutate().request(exchange.getRequest().mutate()
				.header("Authorization", "Bearer " + token).build()))
				.
				.flatMap(chain::filter);
	}

}
