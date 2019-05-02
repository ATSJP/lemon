package com.lemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author sjp
 */
@EnableFeignClients(basePackages = { "com.lemon.soa.api" })
@EnableDiscoveryClient
@SpringBootApplication
public class LemonUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(LemonUserApplication.class, args);
	}

}
