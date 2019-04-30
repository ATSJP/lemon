package com.lemon.web.base.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author sjp
 * @date 2019/4/30
 **/
@Getter
@Setter
public class BaseRequest implements Serializable {
	/**
	 * 用户的唯一id
	 */
	private Long	uid;
	/**
	 * 用户所在平台
	 */
	private String	sid;
	/**
	 * token
	 */
	private String	token;
}
