package com.lemon.web.up.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lemon.web.up.request.UpRequest;
import com.lemon.web.up.response.UpResponse;
import com.lemon.web.up.service.UpService;

import javax.annotation.Resource;

/**
 * UpController
 *
 * @author sjp
 * @date 2019/5/6
 */
@RestController
public class UpController {

	@Resource
	private UpService upService;

	/**
	 * 用户点赞视频
	 * 
	 * @param request req
	 * @return UpResponse
	 */
	@PutMapping("/up")
	public UpResponse upVideo(UpRequest request) {
		UpResponse response = new UpResponse();
		upService.upVideo(request, response);
		return response;
	}

	/**
	 * 用户取消点赞
	 * 
	 * @param request req
	 * @return UpResponse
	 */
	@DeleteMapping("/up")
	public UpResponse cancelUpVideo(UpRequest request) {
		UpResponse response = new UpResponse();
		upService.cancelUpVideo(request, response);
		return response;
	}

}
