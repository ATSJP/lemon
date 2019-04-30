package com.lemon.web.video.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lemon.tools.RedissonTools;

/**
 * @author sjp
 * @date 2019/1/16
 **/
@Service
public class VideoService {

	private Logger			logger	= LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RedissonTools	redissonTools;

	public String getVideo(String videoId) {
		logger.info("get video", videoId);
		String video = redissonTools.get(videoId);
		if (StringUtils.isEmpty(video)) {
			video = videoId;
			redissonTools.set(videoId, video);
		}
		return video;
	}
}
