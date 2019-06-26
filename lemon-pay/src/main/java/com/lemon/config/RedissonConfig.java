package com.lemon.config;

import com.lemon.properties.LemonResource;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author sjp
 * @date 2019/4/30
 **/
@Configuration
public class RedissonConfig {

	@Resource
	private LemonResource resource;

	@Bean
	public RedissonClient redisson() throws IOException {
		// 本模块使用的是yaml格式的配置文件，读取使用Config.fromYAML，如果是Json文件，则使用Config.fromJSON
		Config config = Config.fromYAML(
				RedissonConfig.class.getClassLoader().getResource("redisson-config-" + resource.getEnv() + ".yml"));
		return Redisson.create(config);
	}

}