package com.lemon.web.request;

import lombok.Data;

/**
 * PayCallbackRequest
 *
 * @author sjp
 * @date 2019/5/25
 */
@Data
public class PayCallbackRequest {
	/**
	 * 编码格式
	 */
	private String	charset;
	/**
	 * 商家订单号
	 */
	private String	out_trade_no;
	/**
	 * 支付宝交易号
	 */
	private String	trade_no;
	/**
	 * 交易的订单金额，单位为元，两位小数。该参数的值为支付时传入的total_amount
	 */
	private String	total_amount;
	/**
	 *
	 */
	private String	auth_app_id;
	/**
	 * 版本
	 */
	private String	version;
	/**
	 * appId
	 */
	private String	app_id;
	/**
	 * 签名类型
	 */
	private String	sign_type;
	/**
	 * 收款支付宝账号对应的支付宝唯一用户号
	 */
	private String	seller_id;
	/**
	 * 时间
	 */
	private String	timestamp;
	private String	method;
	/**
	 * 签名
	 */
	private String	sign;

}
