package com.lemon.soa.api.dto;

import lombok.Data;

/**
 * VideoCountDTO
 *
 * @author sjp
 * @date 2019/5/3
 */
@Data
public class VideoCountDTO {
	/**
	 * 视频id
	 */
	private Long	videoId;
	/**
	 * 总播放量
	 */
	private Long	totalPlayCount;
	/**
	 * 总评论数
	 */
	private Long	totalRemarkCount;
}
