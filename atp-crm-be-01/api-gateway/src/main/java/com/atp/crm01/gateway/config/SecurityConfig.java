package com.atp.crm01.gateway.config;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
	
	private final Oauth2AuthenSuccessHandler oauth2AuthenSuccessHandler;

	@Value("${app.baseUrl.auth-server}")
	private String authServerHost;

	@Value("${app.baseUrl.gateway}")
	private String gatewayHost;

	public SecurityConfig(Oauth2AuthenSuccessHandler oauth2AuthenSuccessHandler) {
		super();
		this.oauth2AuthenSuccessHandler = oauth2AuthenSuccessHandler;
	}

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
	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
				.authorizeExchange(exchange-> exchange
//						.pathMatchers("/oauth2-authen/verify").authenticated()
						.pathMatchers(
								"/login/**",
								"/oauth2/**",
								"/static/**",
								"/oauth2-authen/**"
								).permitAll()
						.anyExchange().authenticated()
				)
				.oauth2Login(oauth2Login-> oauth2Login.authenticationSuccessHandler(oauth2AuthenSuccessHandler))
				.build();
	}
}
