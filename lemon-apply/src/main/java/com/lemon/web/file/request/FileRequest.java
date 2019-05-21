package com.lemon.web.file.request;

import com.lemon.web.base.request.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * FileRequest
 *
 * @author sjp
 * @date 2019/5/19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FileRequest extends BaseRequest {
	/**
	 * 关联类型 ：0 视频 1 图片
	 */
	@NotNull
	private Short			linkType;
	/**
	 * 关联id
	 */
	@NotNull
	private Long			linkId;
	/**
	 * 上传的文件List
	 */
	private MultipartFile[]	files;
}
