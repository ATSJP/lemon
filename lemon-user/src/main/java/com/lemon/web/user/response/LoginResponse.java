package com.lemon.web.user.response;

import com.lemon.web.base.response.BaseResponse;
import com.lemon.web.user.vo.LoginInfoVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author sjp
 * @date 2019/4/30
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginResponse extends BaseResponse {
    private LoginInfoVo loginInfoVo;
}
