package com.atp.crm01.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.ComponentScan;

@EnableConfigServer
@SpringBootApplication
//@ComponentScan(basePackages = {"com.atp.crm01.common.exception","com.atp.crm01.account"})
public class CentralConfigApplication {
	public static void main(String[] args) {
		SpringApplication.run(CentralConfigApplication.class, args);
	}

}
