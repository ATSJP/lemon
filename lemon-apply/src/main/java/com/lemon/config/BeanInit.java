package com.lemon.config;

import com.lemon.utils.SnowFlake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * BeanInit
 *
 * @author sjp
 * @date 2019/5/3
 */
@Configuration
public class BeanInit {

    /**
     * 雪花算法
     *
     * @return SnowFlake
     */
	@Bean
	public SnowFlake snowFlake() {
		return new SnowFlake(1, 1);
	}

}
