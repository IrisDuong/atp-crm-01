package com.atp.crm01.gateway.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import reactor.core.publisher.Mono;

@Configuration
public class SecurityConfig {
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration config = new CorsConfiguration();
	    config.setAllowedOrigins(List.of("http://localhost:3000"));
	    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
	    config.setAllowedHeaders(List.of("*"));
	    config.setAllowCredentials(new Boolean(true));
	    config.setMaxAge(3600L);

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", config);
	    return source;
	}
	
	public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http, Oauth2AuthenticationSuccessHandler successHandler) {
		http
        .csrf(ServerHttpSecurity.CsrfSpec::disable)
        .authorizeExchange(auth-> auth.anyExchange().authenticated())
		.oauth2Login(oauth2Login-> oauth2Login.authenticationSuccessHandler(successHandler));
		return http.build();
	}
}
