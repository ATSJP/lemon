package com.lemon.clock.video.task;

import com.lemon.clock.video.service.VideoIndexService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * VideoIndexTaskTest
 *
 * @author sjp
 * @date 2019/5/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoIndexTaskTest {

	@Resource
	private VideoIndexService videoIndexService;

	@Test
	public void createIndex() {
		videoIndexService.createIndex();
	}
}