package com.lemon.properties;

/**
 * Resource
 *
 * @author sjp
 * @date 2019/5/23
 */

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class LemonResource {
	/**
	 * 当前环境
	 */
	@Value("${spring.profiles.active}")
	private String	env;

	/**
	 * 异步回调路径
	 */
	@Value("${com.lemon.pay.returnUrl}")
	private String	returnUrl;
	/**
	 * 返回页面路径
	 */
	@Value("${com.lemon.pay.notifyUrl}")
	private String	notifyUrl;
	/**
	 * 支付宝Url
	 */
	@Value("${com.lemon.pay.url}")
	private String	aliPayUrl;
	/**
	 * 编码格式
	 */
	@Value("${com.lemon.pay.charset}")
	private String	aliPayCharset;
	/**
	 * 返回参数格式 json、xml
	 */
	@Value("${com.lemon.pay.format}")
	private String	format;
	/**
	 * 验签方式
	 */
	@Value("${com.lemon.pay.signType}")
	private String	signType;
	/**
	 * 支付宝appId
	 */
	@Value("${com.lemon.pay.appId}")
	private String	appId;
	/**
	 * 支付宝RSA2私钥
	 */
	@Value("${com.lemon.pay.appPrivateKey}")
	private String	appPrivateKey;
	/**
	 * 支付宝RSA2公钥
	 */
	@Value("${com.lemon.pay.aliPayPublicKey}")
	private String	aliPayPublicKey;
}
