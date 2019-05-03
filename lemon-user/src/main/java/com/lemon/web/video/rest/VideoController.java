package com.lemon.web.video.rest;

import com.lemon.soa.api.contant.ConstantVideo;
import com.lemon.soa.api.dto.VideoDTO;
import com.lemon.soa.api.provider.VideoProvider;
import com.lemon.web.video.response.VideoResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sjp
 * @date 2019/1/16
 **/
@Controller
@RequestMapping("/video")
public class VideoController {

	@Resource
	private VideoProvider videoProvider;

	@ResponseBody
	@RequestMapping("/get/{videoId}")
	public VideoResponse getVideo(@PathVariable(value = "videoId") long videoId) {
		VideoResponse response = new VideoResponse();
		VideoDTO videoDTO = videoProvider.getVideo(videoId);
		response.setVideoDTO(videoDTO);
		return response;
	}

	@ResponseBody
	@RequestMapping("/getIndexVideo")
	public VideoResponse getIndexVideo() {
		VideoResponse response = new VideoResponse();
		List<VideoDTO> videoDTOList = videoProvider.getVideoOrderBySortKey(ConstantVideo.SORT_KEY.PLAY_NUM.code,
				ConstantVideo.SORT_VALUE.DESC.code);
		response.setVideoDTOList(videoDTOList);
		return response;
	}

}
