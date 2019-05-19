package com.lemon.web.video.request;

import com.lemon.web.base.request.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author sjp
 * @date 2019/5/1
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class VideoRequest extends BaseRequest {
	private Long	videoId;
	private String	videoName;
	private String	videoContext;
	private Long	categoryId;
}
