package com.atp.crm01.setting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.atp.crm01.common.*","com.atp.crm01.setting.*"
		}
)
@EnableDiscoveryClient
@EnableJpaAuditing
public class SettingApplication {
	public static void main(String[] args) {
		SpringApplication.run(SettingApplication.class, args);
	}
	

}
