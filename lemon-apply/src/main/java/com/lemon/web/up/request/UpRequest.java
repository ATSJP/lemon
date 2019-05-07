package com.lemon.web.up.request;

import com.lemon.web.base.request.BaseRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * UpRequest
 *
 * @author sjp
 * @date 2019/5/6
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UpRequest extends BaseRequest {
	private Long videoId;
}
