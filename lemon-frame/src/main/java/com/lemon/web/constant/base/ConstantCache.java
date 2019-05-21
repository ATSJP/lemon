package com.lemon.web.constant.base;

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
		LOGIN_TOKEN("LOGIN_TOKEN_KEY_", "登陆token缓存key", 30 * 60),
		INDEX_VIDEO_LIST("INDEX_VIDEO_KEY_", "首页视频列表缓存key", 60 * 60),
		CATEGORY_VIDEO_LIST("CATEGORY_VIDEO_LIST_KEY_", "已经分类的视频列表", 60 * 60),
		CATEGORY_VIDEO_SELF_LIST("CATEGORY_VIDEO_SELF_LIST_KEY_", "用户的视频列表", 60 * 60),;

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
