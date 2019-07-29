package com.lemon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author sys
 * @date 2019/4/15
 **/
@Data
@EqualsAndHashCode()
@Component
public class ConfigProperties {
    /**
     * redission配置文件
     */
    @Value("${redission.config}")
    private String	redissionConfig;
}