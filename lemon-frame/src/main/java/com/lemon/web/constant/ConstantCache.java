package com.lemon.web.constant;

/**
 * 缓存公用cacheKey定义
 * 
 * @author sjp
 * @date 2019/4/16
 **/
public interface ConstantCache {

	enum KEY {
		/**
		 * 缓存key、描述、过期时间
		 */
		LOGIN_TOKEN("LOGIN_TOKEN_TIME_OUT_", "登陆token缓存key", 30 * 60);

		public String	key;
		public String	desc;
		public int		timeOut;

		KEY(String key, String desc, int timeOut) {
			this.key = key;
			this.desc = desc;
			this.timeOut = timeOut;
		}

	}

}
