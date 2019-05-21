package com.lemon.controller;

import com.lemon.soa.api.dto.VideoDTO;
import com.lemon.soa.api.dto.VideoDetailDTO;
import com.lemon.soa.api.provider.VideoProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sjp
 * @date 2019/1/24
 **/
@RestController
public class VideoController {

	@Resource
	private VideoProvider videoProvider;

	@GetMapping(value = "/video/simple/{videoId}")
	public VideoDetailDTO getVideoSimpleDetail(@PathVariable long videoId) {
		return videoProvider.getVideoSimpleDetail(videoId);
	}

	@GetMapping(value = "/video/{videoId}")
	public VideoDTO getVideo(@PathVariable long videoId) {
		return videoProvider.getVideo(videoId);
	}

	@GetMapping(value = "/video/getVideoOrderBySortKey/{sortKey}/{sortValue}")
	List<VideoDTO> getVideoOrderBySortKey(@PathVariable(value = "sortKey") short sortKey,
			@PathVariable(value = "sortValue") short sortValue) {
		return videoProvider.getVideoOrderBySortKey(sortKey, sortValue);
	}

	@GetMapping(value = "/video/getVideoList/{categoryId}/{pageIndex}/{size}")
	List<VideoDTO> getVideoListByCategoryId(@PathVariable(value = "categoryId") Long categoryId,
			@PathVariable(value = "pageIndex") int pageIndex, @PathVariable(value = "size") int size) {
		return videoProvider.getVideoListByCategoryId(categoryId, pageIndex, size);
	}

	@GetMapping("/video/getVideoListByLoginId/{loginId}/{pageIndex}/{size}")
	List<VideoDTO> getVideoListByLoginId(@PathVariable(value = "loginId") Long loginId,
			@PathVariable(value = "pageIndex") int pageIndex, @PathVariable(value = "size") int size) {
		return videoProvider.getVideoListByLoginId(loginId, pageIndex, size);
	}

}
