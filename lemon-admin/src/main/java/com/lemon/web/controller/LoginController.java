package com.lemon.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sjp 2019/1/10
 */
@Controller
@RestController
public class LoginController {

    @Value("${redission_config}")
    private String version;

    @GetMapping("/test")
    public String getVersion() {
        return this.version;
    }

}
