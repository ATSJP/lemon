package com.lemon.clock.video.service;

import com.lemon.config.ConfigProperties;
import com.lemon.entity.VideoEntity;
import com.lemon.repository.VideoPageRepository;
import com.lemon.web.search.service.LuceneService;
import org.apache.lucene.document.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * VideoIndexService
 *
 * @author sjp
 * @date 2019/5/23
 */
@Service
public class VideoIndexService {
	private Logger				logger	= LoggerFactory.getLogger(this.getClass());
	@Resource
	private LuceneService		luceneService;
	@Resource
	private VideoPageRepository	videoPageRepository;
	@Resource
	private ConfigProperties	configProperties;

	public void createIndex() {
		int currentPage = 0;
		int pageSize = 100;

		List<Document> documentList = new LinkedList<>();

		Pageable pageable = PageRequest.of(currentPage, pageSize);
		Page<VideoEntity> videoEntityPage = videoPageRepository.findAll(pageable);
		while (videoEntityPage.getContent().size() > 0) {
			// 当前页建立索引
			List<VideoEntity> videoEntityList = videoEntityPage.getContent();

			videoEntityList.forEach(video -> {
				Document document = luceneService.createDocument(video.getVideoId(), video.getVideoName(),
						video.getVideoContext());
				documentList.add(document);
			});
			// 查询下一页
			currentPage++;
			pageable = PageRequest.of(currentPage, pageSize);
			videoEntityPage = videoPageRepository.findAll(pageable);
		}

		try {
			luceneService.write(documentList, configProperties.getVideoIndexDir());
		} catch (IOException e) {
			logger.error("create index error->e:{}", e);
		}
	}

}
