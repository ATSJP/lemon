package com.lemon.web.video.rest;

import com.lemon.soa.api.contant.ConstantVideo;
import com.lemon.soa.api.dto.VideoDTO;
import com.lemon.soa.api.provider.VideoProvider;
import com.lemon.web.video.request.VideoRequest;
import com.lemon.web.video.response.VideoResponse;
import com.lemon.web.video.service.VideoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author sjp
 * @date 2019/1/16
 **/
@RestController
public class VideoController {

	@Resource
	private VideoProvider	videoProvider;
	@Resource
	private VideoService	videoService;

	/**
	 * 获取单个视频详情
	 * 
	 * @param request req
	 * @return VideoResponse
	 */
	@GetMapping("/video/get")
	public VideoResponse getVideo(VideoRequest request) {
		VideoResponse response = new VideoResponse();
		videoService.getVideo(request, response);
		return response;
	}

	/**
	 * 从缓存中获取指定排序方式进行排名的视频列表,仅仅取出detail信息，在首页或推荐处使用
	 *
	 * @return VideoResponse
	 */
	@GetMapping(value = "/video/getVideoOrderRank/{sortKey}")
	public VideoResponse getVideoOrderRank(@PathVariable(value = "sortKey") short sortKey) {
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

	/**
	 * 分页查找当前用户的视频列表
	 * 
	 * @param loginId 会员id
	 * @param pageIndex 页面
	 * @param size 分页大小
	 * @return List<VideoDTO>
	 */
	@GetMapping("/video/getVideoListByLoginId/{pageIndex}/{size}")
	public VideoResponse getVideoListByLoginId(@PathVariable(value = "pageIndex") int pageIndex,
			@PathVariable(value = "size") int size, @RequestParam(value = "uid") Long loginId) {
		VideoResponse response = new VideoResponse();
		List<VideoDTO> videoDTOList = videoProvider.getVideoListByLoginId(loginId, pageIndex, size);
		response.setVideoDTOList(videoDTOList);
		return response;
	}

	/**
	 * 分页查找当前用户收藏的视频列表
	 *
	 * @param loginId 会员id
	 * @param pageIndex 页面
	 * @param size 分页大小
	 * @return List<VideoDTO>
	 */
	@GetMapping("/video/getCollectVideoListByLoginId/{pageIndex}/{size}")
	public VideoResponse getCollectVideoListByLoginId(@PathVariable(value = "pageIndex") int pageIndex,
			@PathVariable(value = "size") int size, @RequestParam(value = "uid") Long loginId) {
		VideoResponse response = new VideoResponse();
		List<VideoDTO> videoDTOList = videoProvider.getCollectVideoListByLoginId(loginId, pageIndex, size);
		response.setVideoDTOList(videoDTOList);
		return response;
	}

	/**
	 * 播放记录
	 * 
	 * @param request req
	 * @return VideoResponse
	 */
	@PostMapping("/video/play")
	public VideoResponse playVideo(VideoRequest request) {
		VideoResponse response = new VideoResponse();
		videoService.playVideo(request, response);
		return response;
	}

	/**
	 * 添加视频
	 * 
	 * @param request req
	 * @return VideoResponse
	 */
	@PostMapping("/video")
	public VideoResponse addVideo(@Valid VideoRequest request) {
		VideoResponse response = new VideoResponse();
		videoService.addVideo(request, response);
		return response;
	}

	/**
	 * 修改视频
	 *
	 * @param request req
	 * @return VideoResponse
	 */
	@PutMapping("/video")
	public VideoResponse updateVideo(VideoRequest request) {
		VideoResponse response = new VideoResponse();
		videoService.updateVideo(request, response);
		return response;
	}

}
