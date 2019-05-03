package com.lemon.web.constant;

/**
 * 基础常量枚举
 *
 * @author sjp
 * @date 2019/4/30
 **/
public interface ConstantBaseData {

	enum IS_DELETE {
		/**
		 * 0 未删除 1 已删除
		 */
		FALSE((short) 0, "未删除"),
		TRUE((short) 1, "已删除");

		public Short	code;
		public String	desc;

		IS_DELETE(Short code, String desc) {
			this.code = code;
			this.desc = desc;
		}
	}

	enum IS_DISPLAY {
		/**
		 * 是否显示（0：显示；1：隐藏）
		 */
		DISPLAY((short) 0, "显示"),
		NOT_DISPLAY((short) 1, "隐藏");

		public Short	code;
		public String	desc;

		IS_DISPLAY(Short code, String desc) {
			this.code = code;
			this.desc = desc;
		}
	}

}