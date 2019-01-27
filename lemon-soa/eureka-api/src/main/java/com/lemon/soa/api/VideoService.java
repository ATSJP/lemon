package com.lemon.soa.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sjp
 * @date 2019/1/24
 **/
@FeignClient("eureka-provider")
public interface VideoService {
	/**
	 * 获取视频信息
	 * @param videoId 视频id
	 * @return 视频信息
	 */
	@RequestMapping(value = "/{videoId}")
	double getVideo(@PathVariable("videoId") long videoId);
}
