package com.lemon.web.collect.rest;

import com.lemon.web.collect.request.CollectRequest;
import com.lemon.web.collect.response.CollectResponse;
import com.lemon.web.collect.service.CollectService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * CollectController
 *
 * @author sjp
 * @date 2019/5/6
 */
@RestController
public class CollectController {

	@Resource
	private CollectService collectService;

	/**
	 * 用户收藏视频
	 * 
	 * @param request req
	 * @return CollectResponse
	 */
	@PutMapping("/collect")
	public CollectResponse collect(CollectRequest request) {
		CollectResponse response = new CollectResponse();
		collectService.collect(request, response);
		return response;
	}

	/**
	 * 用户取消收藏视频
	 *
	 * @param request req
	 * @return CollectResponse
	 */
	@DeleteMapping("/collect")
	public CollectResponse collectCancel(CollectRequest request) {
		CollectResponse response = new CollectResponse();
		collectService.collectCancel(request, response);
		return response;
	}

}
