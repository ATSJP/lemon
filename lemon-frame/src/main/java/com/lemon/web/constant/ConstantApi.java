package com.lemon.web.constant;

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

	enum LOGIN_MESSAGE {
		/**
		 * 返回状态及自定义信息
		 */
		SUCCESS("登陆成功"),
		FAIL("登陆失败，请确认您的用户名或者密码");

		private String desc;

		LOGIN_MESSAGE(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}
	}

	enum REGISTER_MESSAGE {
		/**
		 * 返回状态及自定义信息
		 */
		SUCCESS("注册成功"),
		FAIL("注册失败，用户名已存在");

		private String desc;

		REGISTER_MESSAGE(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}
	}

	enum FILE_UPLOAD {
		/**
		 * 文件上传自定义提示
		 */
		FAIL("上传失败，文件不为空");

		private String desc;

		FILE_UPLOAD(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}
	}

	enum FILE_DOWNLOAD {
		/**
		 * 文件下载自定义提示
		 */
		FAIL("文件不存在");

		private String desc;

		FILE_DOWNLOAD(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}
	}

}
