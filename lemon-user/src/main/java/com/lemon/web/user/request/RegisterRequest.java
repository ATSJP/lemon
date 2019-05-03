package com.lemon.web.user.request;

import com.lemon.web.base.request.BaseRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * RegisterRequest
 *
 * @author sjp
 * @date 2019/5/3
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterRequest extends BaseRequest {
	/**
	 * 用户名
	 */
    @Length(min = 1, max = 20)
	@NotBlank(message = "用户名不能为空")
	private String	loginName;
	/**
	 * 真实姓名
	 */
	@Length(min = 1, max = 20)
	@NotBlank(message = "真实姓名不能为空")
	private String	userName;
	/**
	 * 密码
	 */
    @Length(min = 1, max = 20)
	@NotBlank(message = "密码不能为空")
	private String	password;
}
