package com.lemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author sjp
 * @date 2019/1/21
 **/
@SpringBootApplication
@EnableEurekaServer
public class LemonSoaApplication {
    public static void main(String[] args) {
        SpringApplication.run(LemonSoaApplication.class, args);
    }
}
