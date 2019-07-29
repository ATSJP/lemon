package com.lemon.config;

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
    private ConfigProperties	configProperties;

    @Bean
    public RedissonClient redisson() throws IOException {
        // 本模块使用的是json格式的配置文件，读取使用Config.fromJSON，如果是yaml文件，则使用Config.fromYAML
        Config config = Config.fromJSON(configProperties.getRedissionConfig());
        return Redisson.create(config);
    }

}
