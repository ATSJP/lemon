package com.lemon.web.constant;

/**
 * ConstantCollect
 *
 * @author sjp
 * @date 2019/5/7
 */
public interface ConstantUp {

	enum UP_MESSAGE {
		/**
		 * 返回状态及自定义信息
		 */
		UP_REPEAT("您已经点赞过该视频，请刷新查看"),
		CANCEL_REPEAT("您已经取消点赞，手下留情"),;

		private String desc;

		UP_MESSAGE(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}
	}

}
