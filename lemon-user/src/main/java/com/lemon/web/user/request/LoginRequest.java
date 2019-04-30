package com.lemon.web.user.request;

import com.lemon.web.base.request.BaseRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @author sjp
 * @date 2019/4/30
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginRequest extends BaseRequest {
	/**
	 * 用户名
	 */
	@NotBlank(message = "用户名不能为空")
	private String	loginName;
	/**
	 * 密码
	 */
	@NotBlank(message = "密码不能为空")
	private String	password;
}
