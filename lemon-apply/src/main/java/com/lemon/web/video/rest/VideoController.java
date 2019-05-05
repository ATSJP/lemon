package com.lemon.web.video.rest;

import com.lemon.soa.api.contant.ConstantVideo;
import com.lemon.soa.api.dto.VideoDTO;
import com.lemon.soa.api.provider.VideoProvider;
import com.lemon.web.video.response.VideoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sjp
 * @date 2019/1/16
 **/
@RestController
public class VideoController {

	@Resource
	private VideoProvider videoProvider;

	@GetMapping("/video/get/{videoId}")
	public VideoResponse getVideo(@PathVariable(value = "videoId") long videoId) {
		VideoResponse response = new VideoResponse();
		VideoDTO videoDTO = videoProvider.getVideo(videoId);
		response.setVideoDTO(videoDTO);
		return response;
	}

	/**
	 * 从缓存中获取指定排序方式进行排名的视频列表,仅仅取出detail信息，在首页或推荐处使用
	 *
	 * @return VideoResponse
	 */
	@GetMapping(value = "/video/getVideoOrderBySortKey/{sortKey}")
	public VideoResponse getIndexVideo(@PathVariable(value = "sortKey") short sortKey) {
		VideoResponse response = new VideoResponse();
		List<VideoDTO> videoDTOList = videoProvider.getVideoOrderBySortKey(sortKey, ConstantVideo.SORT_VALUE.DESC.code);
		response.setVideoDTOList(videoDTOList);
		return response;
	}

    /**
     * 按照指定分类查找视频列表，并按指定大小分页，取出第page页的list
     *
     * @param categoryId 分类id
     * @param pageIndex 页码
     * @param size 大小
     * @return VideoResponse
     */
	@GetMapping("/video/getVideoList/{categoryId}/{pageIndex}/{size}")
	public VideoResponse getVideoListByCategoryId(@PathVariable(value = "categoryId") Long categoryId,
			@PathVariable(value = "pageIndex") int pageIndex, @PathVariable(value = "size") int size) {
		VideoResponse response = new VideoResponse();
		List<VideoDTO> videoDTOList = videoProvider.getVideoListByCategoryId(categoryId, pageIndex, size);
		response.setVideoDTOList(videoDTOList);
		return response;
	}

}
