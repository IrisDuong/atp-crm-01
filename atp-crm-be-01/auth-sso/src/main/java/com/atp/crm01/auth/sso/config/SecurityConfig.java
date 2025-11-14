package com.atp.crm01.auth.sso.config;

import java.util.Arrays;

import javax.security.auth.login.LoginContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.atp.crm01.common.utils.CookieUtils;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	Oauth2SuccessHandler successHandler;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors(Customizer.withDefaults())
//		.cors(cors-> cors.configurationSource(corsConfigurationSource()))
		.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(auth-> auth
				.requestMatchers("/login/**", "/oauth2/**").permitAll()
				.anyRequest().authenticated()
		)
		.oauth2Login(login-> login
				.loginPage("/oauth2/authorization/google")
				.successHandler(successHandler)
		)
		.logout(logout-> logout
				.logoutSuccessHandler((request, response, authentication)->{
					CookieUtils.setCookie(response, CookieUtils.ACCESS_TOKEN_COOKIE_NAME, "", 0);
				})
		);
		return http.build();
	}

//	@Bean
//	public CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration corsConfiguration = new CorsConfiguration();
//		corsConfiguration.setAllowCredentials(true);
//		corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
//		corsConfiguration.setAllowedMethods(Arrays.asList("POST","GET","PUT","PATCH","DELETE","OPTIONS"));
//		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000","http://localhost:8080"));
//		corsConfiguration.setMaxAge(3600L);
//		
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", corsConfiguration);
//		return source;
//	}
}
