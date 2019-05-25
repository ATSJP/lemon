package com.lemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author sjp
 */
@EnableScheduling
@EnableFeignClients(basePackages = { "com.lemon.soa.api" })
@EnableDiscoveryClient
@SpringBootApplication
public class LemonApplyApplication {

	public static void main(String[] args) {
		SpringApplication.run(LemonApplyApplication.class, args);
	}

}
