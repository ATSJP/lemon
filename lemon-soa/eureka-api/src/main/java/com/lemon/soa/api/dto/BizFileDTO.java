package com.lemon.soa.api.dto;

import lombok.Data;

/**
 * @author sjp
 * @date 2019/5/1
 **/
@Data
public class BizFileDTO {
	/**
	 * 文件id
	 */
	private Long	fileId;
	/**
	 * 关联id
	 */
	private Long	linkId;
	/**
	 * 关联类型 ：0 视频 1 图片
	 */
	private Short	linkType;
	/**
	 * 文件名
	 */
	private String	fileName;
	/**
	 * 文件内容
	 */
	private Byte[]	fileContent;
	/**
	 * 文件后缀
	 */
	private String	fileSuffix;
}
