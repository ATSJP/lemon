package com.lemon.web.remark.response;

import com.lemon.web.base.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * RemarkResponse
 *
 * @author sjp
 * @date 2019/5/8
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RemarkResponse extends BaseResponse {
	/**
	 * 评价id
	 */
	private Long remarkId;
}
