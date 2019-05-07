package com.lemon.soa.api.dto;

import lombok.Data;

/**
 * CollectionDetailEntity
 *
 * @author sjp
 * @date 2019/5/6
 */
@Data
public class CollectionDetailDTO {
	private Long	collectionId;
	private Long	videoId;
	private Short	isDel;
	private Long	loginId;
}
