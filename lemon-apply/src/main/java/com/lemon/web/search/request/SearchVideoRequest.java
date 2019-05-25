package com.lemon.web.search.request;

import com.lemon.web.base.request.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * SerarchVideoController
 *
 * @author sjp
 * @date 2019/5/23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SearchVideoRequest extends BaseRequest {
	/**
	 * 关键词
	 */
	@NotBlank
	public String keyWord;
}
