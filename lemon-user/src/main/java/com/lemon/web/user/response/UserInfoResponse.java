package com.lemon.web.user.response;

import com.lemon.dto.UserInfoDTO;
import com.lemon.web.base.response.BaseResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * UserInfoResponse
 *
 * @author sjp
 * @date 2019/5/22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfoResponse extends BaseResponse {
	private UserInfoDTO userInfoDTO;
}
