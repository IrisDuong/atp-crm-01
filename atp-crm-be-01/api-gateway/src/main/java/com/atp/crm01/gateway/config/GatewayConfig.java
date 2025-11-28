package com.atp.crm01.gateway.config;


import java.util.Arrays;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.persistence.MappedSuperclass;

@Configuration
@MappedSuperclass
public class GatewayConfig {
	
	@Bean
	public WebClient.Builder webClientBuilder(){
		return WebClient.builder();
	}

	@Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        corsConfig.setAllowedHeaders(Arrays.asList("*"));
        corsConfig.setExposedHeaders(Arrays.asList(
            "Authorization", "Content-Type", "Content-Disposition",
            "X-Requested-With", "X-User-Authenticated", "X-User-Id"
        ));
        corsConfig.setAllowCredentials(true);
        corsConfig.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }


	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("AUTH-SSO-SERVICE", r-> r
						.path("/auth-mgt/**")
						.filters(f-> f
								.stripPrefix(1)
						 )
						.uri("lb://auth-sso")
				)
				.route("ACCOUNT-SERVICE", r-> r
						.path("/account-mgt/**")
						.filters(f-> f
								.stripPrefix(1)
						 )
						.uri("lb://account")
				)
				.route("SETTING-SERVICE", r-> r
						.path("/setting-mgt/**")
						.filters(f-> f
								.stripPrefix(1)
						)
						.uri("lb://setting")
				)
				.route("FE", r-> r
						.path("/")
						.filters(f-> f
								.stripPrefix(1)
						 )
						.uri("http://localhost:3000")
				).route("public-routes", r -> r
	                    .path("/", "/public/**", "/static/**", "/favicon.ico")
	                    .uri("http://localhost:3000")
	                )

                .build();
	}
}
