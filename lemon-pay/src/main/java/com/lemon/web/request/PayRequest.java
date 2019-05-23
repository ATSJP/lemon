package com.lemon.web.request;

import com.lemon.web.base.request.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * PayRequest
 *
 * @author sjp
 * @date 2019/5/23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PayRequest extends BaseRequest {
	@NotBlank
	private String orderIdEn;
}
