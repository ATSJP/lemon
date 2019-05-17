package com.lemon.web.constant.base;

/**
 * 公用的lockKey定义类（业务锁）
 * 
 * @author sjp
 * @date 2019/4/30
 **/
public interface ConstantLock {

	enum KEY {
		/**
		 * 业务分布式锁key、描述、超时时间
		 */
		COLLECT_LOCK("COLLECT_LOCK_", "收藏锁", 2, 5),
		UP_LOCK("UP_LOCK_", "点赞锁", 2, 5);

		public String	key;
		public String	desc;
		public int		timeOut;
		public int		expires;

		KEY(String key, String desc, int timeOut, int expires) {
			this.key = key;
			this.desc = desc;
			this.timeOut = timeOut;
			this.expires = expires;
		}
	}

}
