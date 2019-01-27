package com.lemon.consumer.controller;

import com.lemon.consumer.soa.TestProvider;
import org.springframework.web.bind.annotation.GetMapping;
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

	/**
	 * feign 方式
	 */
	@Resource
	private TestProvider testProvider;

	@GetMapping(value = "/hello")
	public String hello() {
		return testProvider.consumer(1);
	}
}
