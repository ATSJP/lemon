package com.lemon.dto;

import lombok.Data;

/**
 * UserDTO
 *
 * @author sjp
 * @date 2019/5/8
 */
@Data
public class UserDTO {
	/**
	 * 用户id
	 */
	private Long	loginId;
	/**
	 * 用户名
	 */
	private String	loginName;
}
