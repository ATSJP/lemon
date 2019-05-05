package com.lemon.soa.api.dto;

import lombok.Data;

/**
 * @author sjp
 * @date 2019/5/1
 **/
@Data
public class VideoDetailDTO {
	/**
	 * 视频id
	 */
	private Long	videoId;
	/**
	 * 视频名称
	 */
	private String	videoName;
	/**
	 * 视频介绍
	 */
	private String	videoContext;
	/**
	 * 视频长度
	 */
	private String	time;
	/**
	 * 播放量
	 */
	private Integer	playNum;
	/**
	 * 分类id
	 */
	private Long	categoryId;
	/**
	 * 作者
	 */
	private Long	userId;
	/**
	 * 作者名称
	 */
	private String	userName;
	/**
	 * 审核状态： 0 未审核 1 审核通过 2 审核未通过 3 已删除
	 */
	private Short	auditStatus;
	/**
	 * 上传时间
	 */
	private String	createTime;
}
