package com.lemon.controller;

import com.lemon.soa.api.VideoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sjp
 * @date 2019/1/24
 **/
@RestController
public class VideoController {

	@Resource
	private VideoService videoService;

	@RequestMapping(value = "/{videoId}", method = RequestMethod.GET)
	public Double getVideo(@PathVariable long videoId) {
		return videoService.getVideo(videoId);
	}
}
