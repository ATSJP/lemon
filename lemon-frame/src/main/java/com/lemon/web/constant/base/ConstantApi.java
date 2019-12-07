package com.lemon.web.constant.base;

/**
 * API接口公用返回提示code定义
 * 
 * @author sjp
 * @date 2019/4/15
 **/
public interface ConstantApi {

    String	TOKEN	= "token";
    String	UID		= "uid";
    String	SID		= "sid";

	enum CODE {
		/**
		 * 系统错误code
		 */
		SUCCESS((short) 0, "成功"),
		FAIL((short) 1, "失败"),
		SYSTEM_ERROR((short) 2, "系统错误"),
		ILLEGAL_REQUEST((short) 3, "非法请求"),
		TOKEN_INVALID((short) 4, "token失效,请重新登陆"),
		PARAM_NULL((short) 5, "请求参数不为空"),
		AUTH_EMPTY((short) 6, "权限不足");

		private Short	code;
		private String	desc;

		CODE(Short code, String desc) {
			this.code = code;
			this.desc = desc;
		}

		public Short getCode() {
			return code;
		}

		public String getDesc() {
			return desc;
		}
	}



}
