package com.atp.crm01.gateway.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.atp.crm01.common.exception","com.atp.crm01.account","com.atp.crm01.auth.sso","com.atp.crm01.gateway"
		,"com.atp.crm01.setting"
		}
)
@EnableDiscoveryClient
public class GatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
