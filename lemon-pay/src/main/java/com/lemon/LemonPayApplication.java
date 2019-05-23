package com.lemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author sjp
 */
@SpringBootApplication
@EnableScheduling
public class LemonPayApplication {

	public static void main(String[] args) {
		SpringApplication.run(LemonPayApplication.class, args);
	}

}
