package com.lemon.web.video.request;

import com.lemon.web.base.request.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author sjp
 * @date 2019/5/1
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class VideoRequest extends BaseRequest {
	private Long	videoId;
	@Length(min = 1, max = 50)
	private String	videoName;
	@Length(min = 1, max = 200)
	private String	videoContext;
	@NotNull
	private Long	categoryId;
}
