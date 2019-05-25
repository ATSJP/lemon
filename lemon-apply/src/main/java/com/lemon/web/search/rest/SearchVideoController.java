package com.lemon.web.search.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lemon.web.search.request.SearchVideoRequest;
import com.lemon.web.search.response.SearchVideoResponse;
import com.lemon.web.search.service.SearchVideoService;

import javax.annotation.Resource;

/**
 * SearchVideoController
 *
 * @author sjp
 * @date 2019/5/23
 */
@RestController
public class SearchVideoController {

	@Resource
	private SearchVideoService searchVideoService;

	/**
	 * 搜索视频
	 *
	 * @param request req
	 * @return response res
	 */
	@GetMapping("/video/search")
	public SearchVideoResponse search(SearchVideoRequest request) {
		SearchVideoResponse response = new SearchVideoResponse();
		searchVideoService.search(request, response);
		return response;
	}

}
