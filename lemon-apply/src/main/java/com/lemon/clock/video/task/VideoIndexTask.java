package com.lemon.clock.video.task;

import com.lemon.clock.video.service.VideoIndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * VideoIndexTask
 *
 * @author sjp
 * @date 2019/5/23
 */
@Component
public class VideoIndexTask {

	private Logger				logger	= LoggerFactory.getLogger(this.getClass());
	@Resource
	private VideoIndexService	videoIndexService;

	// @Scheduled(cron = "0 0/30 9-17 * * ?")
	@Scheduled(cron = "*/30 * * * * ?")
	public void createIndex() {
		logger.info("start to createIndex for video");
		Long start = System.currentTimeMillis();
		videoIndexService.createIndex();
		Long end = System.currentTimeMillis();
		logger.info("end to createIndex for video->time:{}", (end - start));
	}

}
