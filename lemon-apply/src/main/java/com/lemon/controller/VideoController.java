package com.lemon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sjp 2019/1/10
 */
@Controller
@RequestMapping("/video")
public class VideoController {

	@RequestMapping("/get")
	public String getVideo() {
		return "success";
	}

}
