package com.atp.crm01.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.config.Customizer;
@Configuration
public class SecurityConfig {

	@Bean
	@Order(1)
	public SecurityFilterChain authServerSecurityFilterChain(HttpSecurity http) throws Exception {
		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
		http.exceptionHandling(ex-> ex.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/signin")));
		return http.build();
	}
	

	@Bean
	@Order(2)
	public SecurityFilterChain oauth2ClientSecurityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(auth-> auth.requestMatchers("/signin","/oauth2/**").permitAll().anyRequest().authenticated())
		.oauth2Login(Customizer.withDefaults());
		return http.build();
	}
}
