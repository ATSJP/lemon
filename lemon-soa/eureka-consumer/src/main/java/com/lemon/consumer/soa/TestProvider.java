package com.lemon.consumer.soa;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sjp
 * @date 2019/1/27
 **/
@FeignClient("eureka-provider")
public interface TestProvider {

	@RequestMapping(value = "/{videoId}", consumes = "application/json")
	String consumer(@PathVariable("videoId") int num);

}
