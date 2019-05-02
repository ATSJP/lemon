package com.lemon.web.constant;

/**
 * 基础文件
 */
public interface ConstantBizFile {

    enum LINK_TYPE {
        /**
         * 关联类型 ：0 视频 1 图片
         */
        FALSE((short) 0, "视频"),
        TRUE((short) 1, "图片");

        public Short	code;
        public String	desc;

        LINK_TYPE(Short code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }

}
