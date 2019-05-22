package com.lemon.dto;

import lombok.Data;

import java.sql.Date;

/**
 * UserInfoDTO
 *
 * @author sjp
 * @date 2019/5/22
 */
@Data
public class UserInfoDTO {
	/**
	 * 用户id
	 */
	private Long	loginId;
	/**
	 * 真实姓名
	 */
	private String	userName;
	/**
	 * 英文名
	 */
	private String	engName;
	/**
	 * 性别: 0 女 1 男
	 */
	private Short	gender;
	/**
	 * 生日
	 */
	private Date	birthday;
	/**
	 * 会员等级
	 */
	private Integer	grade;
	/**
	 * 用户类型 0 普通会员 1 vip会员
	 */
	private String	userType;
	/**
	 * 身份证
	 */
	private String	idCard;
	/**
	 * qq号
	 */
	private String	qqAccount;
	/**
	 * 微信号
	 */
	private String	weChatAccount;
}
