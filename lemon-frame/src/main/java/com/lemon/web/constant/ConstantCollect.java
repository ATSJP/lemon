package com.lemon.web.constant;

/**
 * ConstantCollect
 *
 * @author sjp
 * @date 2019/5/7
 */
public interface ConstantCollect {

	enum COLLECT_MESSAGE {
		/**
		 * 返回状态及自定义信息
		 */
		COLLECT_REPEAT("您已经收藏过该视频，请刷新查看"),
		COLLECT_CANCEL_REPEAT("您已经成功取消，手下留情"),;

		private String desc;

		COLLECT_MESSAGE(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}
	}

}
