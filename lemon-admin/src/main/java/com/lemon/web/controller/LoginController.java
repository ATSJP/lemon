package com.lemon.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sjp 2019/1/10
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

	@RequestMapping("login")
	public String login() {
		return "success";
	}

}