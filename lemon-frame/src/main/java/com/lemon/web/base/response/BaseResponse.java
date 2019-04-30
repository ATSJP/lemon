package com.lemon.web.base.response;

import com.lemon.web.constant.ConstantApiMsg;
import lombok.Data;

import java.io.Serializable;

/**
 * @author sjp
 * @date 2019/4/30
 **/
@Data
public class BaseResponse implements Serializable {
    /**
     * 用户的唯一id
     */
    private Long uid;
    /**
     * token
     */
    private String token;
    /**
     * 返回状态码
     */
    private Short code = ConstantApiMsg.CODE.SUCCESS.getCode();
    /**
     * 返回描述
     */
    private String msg = ConstantApiMsg.CODE.SUCCESS.getDesc();
    /**
     * 返回数据
     */
    private Object data;
}
