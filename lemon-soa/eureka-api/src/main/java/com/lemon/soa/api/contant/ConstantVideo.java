package com.lemon.soa.api.contant;

/**
 * ConstantVideo
 *
 * @author sjp
 * @date 2019/5/3
 */
public interface ConstantVideo {

	enum SORT_KEY {
		/**
		 * 排序key: 0 播放率 1 评价数
		 */
		PLAY_NUM((short) 0, "播放率"),
		COMMENT_NUM((short) 1, "评价数");

		public Short	code;
		public String	desc;

		SORT_KEY(Short code, String desc) {
			this.code = code;
			this.desc = desc;
		}

	}

	enum SORT_VALUE {
		/**
		 * 排序value: 0 升序 1 降序
		 */
		ASC((short) 0, "升序"),
		DESC((short) 1, "降序");

		public Short	code;
		public String	desc;

		SORT_VALUE(Short code, String desc) {
			this.code = code;
			this.desc = desc;
		}

		public static String transformCodeToSqlKey(Short code) {
			if (ASC.code.equals(code)) {
				return ASC.getClass().getName();
			}
			if (DESC.code.equals(code)) {
				return DESC.getClass().getName();
			}
			return DESC.getClass().getName();
		}
	}

}
