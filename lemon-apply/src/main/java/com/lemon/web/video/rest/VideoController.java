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

	@GetMapping("/video/getIndexVideo")
	public VideoResponse getIndexVideo() {
		VideoResponse response = new VideoResponse();
		List<VideoDTO> videoDTOList = videoProvider.getVideoOrderBySortKey(ConstantVideo.SORT_KEY.PLAY_NUM.code,
				ConstantVideo.SORT_VALUE.DESC.code);
		response.setVideoDTOList(videoDTOList);
		return response;
	}

	@GetMapping("/video/getVideoList/{categoryId}/{pageIndex}/{size}")
	public VideoResponse getVideoListByCategoryId(@PathVariable(value = "categoryId") Long categoryId,
			@PathVariable(value = "pageIndex") int pageIndex, @PathVariable(value = "size") int size) {
		VideoResponse response = new VideoResponse();
		List<VideoDTO> videoDTOList = videoProvider.getVideoListByCategoryId(categoryId, pageIndex, size);
		response.setVideoDTOList(videoDTOList);
		return response;
	}

}
