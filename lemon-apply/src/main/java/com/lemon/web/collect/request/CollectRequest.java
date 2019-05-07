package com.lemon.web.collect.request;

import com.lemon.web.base.request.BaseRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * CollectRequest
 *
 * @author sjp
 * @date 2019/5/6
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CollectRequest extends BaseRequest {
	private Long videoId;
}
