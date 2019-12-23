package com.lemon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lemon.utils.MD5Utils;

/**
 * BeanInit
 *
 * @author sjp
 * @date 2019/5/3
 */
@Configuration
public class BeanInit {

	@Bean
	public MD5Utils md5Utils() {
		return new MD5Utils(5, "md5");
	}

}
