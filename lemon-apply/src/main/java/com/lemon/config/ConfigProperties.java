package com.lemon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author sys
 * @date 2019/4/15
 **/
@Data
@EqualsAndHashCode()
@Component
public class ConfigProperties {
	/**
	 * 视频保存路径
	 */
	@Value("${video_source_path}")
	private String	videoSourceDir;
	/**
	 * 图片保存路径
	 */
	@Value("${image_source_path}")
	private String	imageSourceDir;
}