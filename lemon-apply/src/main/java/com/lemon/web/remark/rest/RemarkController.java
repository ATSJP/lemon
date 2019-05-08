package com.lemon.web.remark.rest;

import com.lemon.web.remark.request.RemarkRequest;
import com.lemon.web.remark.response.RemarkResponse;
import com.lemon.web.remark.service.RemarkService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * RemarkController
 *
 * @author sjp
 * @date 2019/5/8
 */
@RestController
public class RemarkController {

	@Resource
	private RemarkService remarkService;

	/**
	 * 评价
	 *
	 * @param request req
	 * @return response
	 */
	@PostMapping("/remark")
	public RemarkResponse remark(@Valid RemarkRequest request) {
		RemarkResponse response = new RemarkResponse();
		remarkService.remark(request, response);
		return response;
	}

}
