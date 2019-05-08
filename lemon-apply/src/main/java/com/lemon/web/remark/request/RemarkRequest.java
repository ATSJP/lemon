package com.lemon.web.remark.request;

import com.lemon.web.base.request.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

/**
 * RemarkRequest
 *
 * @author sjp
 * @date 2019/5/8
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RemarkRequest extends BaseRequest {
	/**
	 * 评论的内容
	 */
	@Length(min = 1, max = 100)
	private String	remarkContext;
	/**
	 * 视频id
	 */
	@NonNull
	private Long	videoId;
	/**
	 * 回复人id
	 */
	@NonNull
	private Long	repeatId;
	/**
	 * 上级评论id
	 */
	@NonNull
	private Long	parentId;
}
