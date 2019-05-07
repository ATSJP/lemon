package com.lemon.soa.api.dto;

import lombok.Data;

/**
 * PayLogEntity
 *
 * @author sjp
 * @date 2019/5/6
 */
@Data
public class PayLogDTO {
	private Long	logId;
	private String	payTime;
	private Long	loginId;
}
