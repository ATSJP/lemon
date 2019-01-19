package com.lemon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author shijianpeng 2019/1/10
 */
@Controller
@RequestMapping("/payCenter")
public class PayController {

	@RequestMapping("/pay")
	public String pay() {
		return "success";
	}

}
