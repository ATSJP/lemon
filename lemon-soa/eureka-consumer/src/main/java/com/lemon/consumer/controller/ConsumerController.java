package com.lemon.consumer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author sjp
 * @date 2019/1/24
 **/
@RestController
public class ConsumerController {

	@Resource
	private RestTemplate restTemplate;

	@RequestMapping("/")
	public Double index() {
        return restTemplate.getForObject("http://eureka-provider/1", Double.class);
	}
}
