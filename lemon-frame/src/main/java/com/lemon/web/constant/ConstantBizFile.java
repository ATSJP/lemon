package com.lemon.web.constant;

/**
 * 基础常量
 *
 * @author sjp
 */
public interface ConstantBizFile {

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

	enum LINK_TYPE {
		/**
		 * 关联类型 ：0 视频 1 图片
		 */
		VIDEO((short) 0, "视频"),
		PIC((short) 1, "图片");

		public Short	code;
		public String	desc;

		LINK_TYPE(Short code, String desc) {
			this.code = code;
			this.desc = desc;
		}
	}

}
