package com.lemon.soa.api.dto;

import lombok.Data;

import java.util.List;

/**
 * 视频DTO
 * 
 * @author sjp
 * @date 2019/5/1
 **/
@Data
public class VideoDTO {
	/**
	 * 视频明细
	 */
	private VideoDetailDTO	videoDetailDTO;
	/**
	 * 视频文件
	 */
	private BizFileDTO		bizFileDTO;
	/**
	 * 分类DTO
	 */
	private CategoryDTO		categoryDTO;
	/**
	 * 评价
	 */
	private List<RemarkDTO>	remarkDTO;
}
