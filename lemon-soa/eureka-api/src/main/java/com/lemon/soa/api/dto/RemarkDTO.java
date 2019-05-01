package com.lemon.soa.api.dto;

import lombok.Data;

import java.util.List;

/**
 * @author sjp
 * @date 2019/5/1
 **/
@Data
public class RemarkDTO {
	/**
	 * 评价id
	 */
	private Long			remarkId;
	/**
	 * 评价内容
	 */
	private String			remarkContext;
	/**
	 * 回复目标用户id
	 */
	private Long			repeatId;
	/**
	 * 回复目标用户名称
	 */
	private String			repeatName;
	/**
	 * 当前用户id
	 */
	private Long			loginId;
	/**
	 * 当前用户名称
	 */
	private String			loginName;
	/**
	 * 视频id
	 */
	private Long			videoId;
	/**
	 * 评价父类名称
	 */
	private Long			parentId;
	/**
	 * 逻辑删除标识（0：显示；1：隐藏）
	 */
	private String			delFlag;
	/**
	 * 子评论
	 */
	private List<RemarkDTO>	remarkDTOList;
}
