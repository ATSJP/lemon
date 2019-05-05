package com.lemon.dto;

import lombok.Data;

/**
 * RankDTO
 *
 * @author sjp
 * @date 2019/5/5
 */
@Data
public class RankDTO {
	/**
	 * 唯一性区分
	 */
	private Long	uniqueId;
	/**
	 * 
	 * 排名、统计
	 */
	private Long	rankNum;
	/**
	 * 描述
	 */
	private String	desc;
}
