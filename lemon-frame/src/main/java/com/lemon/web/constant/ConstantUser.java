package com.lemon.web.constant;

/**
 * ConstantUser
 *
 * @author sjp
 * @date 2019/5/22
 */
public interface ConstantUser {

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

	enum USER_TYPE {
		/**
		 * 用户类型 0 普通会员 1 vip会员
		 */
		NORMAL((short) 0, "普通会员"),
		VIP((short) 1, "vip会员");

		public Short	code;
		public String	desc;

		USER_TYPE(Short code, String desc) {
			this.code = code;
			this.desc = desc;
		}
	}

	enum GENDER {
		/**
		 * 性别: 0 女 1 男
		 */
		FEMALE((short) 0, "女"),
		MALE((short) 1, "男");

		public Short	code;
		public String	desc;

		GENDER(Short code, String desc) {
			this.code = code;
			this.desc = desc;
		}
	}
}
