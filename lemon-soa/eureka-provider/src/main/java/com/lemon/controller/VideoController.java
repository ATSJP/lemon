package com.lemon.controller;

import com.lemon.soa.api.dto.VideoDTO;
import com.lemon.soa.api.dto.VideoDetailDTO;
import com.lemon.soa.api.provider.VideoProvider;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author sjp
 * @date 2019/1/24
 **/
@RestController
public class VideoController {

	@Resource
	private VideoProvider videoService;

	@GetMapping(value = "/video/{videoId}")
	public VideoDTO getVideo(@PathVariable long videoId) {
		return videoService.getVideo(videoId);
	}

	@GetMapping(value = "/video/detail/{videoId}")
	public VideoDetailDTO getVideoDetail(@PathVariable long videoId) {
		return videoService.getVideoDetail(videoId);
	}
}
