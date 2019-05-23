package com.lemon.web.order.request;

import com.lemon.web.base.request.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * OrderController
 *
 * @author sjp
 * @date 2019/5/23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderRequest extends BaseRequest {
	@NotNull
	private Short prodId;
}
