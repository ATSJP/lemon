package com.lemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author shijianpeng
 */
@EnableConfigServer
@SpringBootApplication
public class LemonConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(LemonConfigApplication.class, args);
	}

}
