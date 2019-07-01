package com.lemon.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.lemon.utils.MD5Utils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;

/**
 * BeanInit
 *
 * @author sjp
 * @date 2019/5/3
 */
@Configuration
public class BeanInit {

	@Resource
	private ConfigProperties resource;

	@Bean
	public MD5Utils md5Utils() {
		return new MD5Utils(5, "md5");
	}

	@Lazy
	@Bean
	public AlipayClient initAlipayClient() {
		return new DefaultAlipayClient(resource.getAliPayUrl(), resource.getAppId(), resource.getAppPrivateKey(),
				resource.getFormat(), resource.getAliPayCharset(), resource.getAliPayPublicKey(),
				resource.getSignType());
	}
}
