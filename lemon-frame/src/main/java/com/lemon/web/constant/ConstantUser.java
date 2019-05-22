package com.lemon.web.constant;

/**
 * ConstantUser
 *
 * @author sjp
 * @date 2019/5/22
 */
public interface ConstantUser {

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
