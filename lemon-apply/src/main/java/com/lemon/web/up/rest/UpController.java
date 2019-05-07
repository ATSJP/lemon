package com.lemon.web.up.rest;

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

	@PutMapping("/up")
	public UpResponse up(UpRequest request) {
		UpResponse response = new UpResponse();
		upService.add(request, response);
		return response;
	}

}
