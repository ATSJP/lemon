package com.lemon.web.user.request;

import com.lemon.web.base.request.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * UserInfoRequest
 *
 * @author sjp
 * @date 2019/5/22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfoRequest extends BaseRequest {
	/**
	 * 真实姓名
	 */
	@Length(min = 1, max = 5)
	private String	userName;
	/**
	 * 英文名
	 */
	@Length(min = 1, max = 20)
	private String	engName;
	/**
	 * 性别： 0 女 1 男
	 */
	@NotNull
	private Short	gender;
	/**
	 * 生日
	 */
	@NotNull
	private Date	birthday;
	/**
	 * 身份证
	 */
	@Length(min = 10, max = 18)
	private String	idCard;
	/**
	 * qq
	 */
	@Length(min = 1, max = 15)
	private String	qqAccount;
	/**
	 * 微信号
	 */
	@Length(min = 1, max = 20)
	private String	weChatAccount;
}
