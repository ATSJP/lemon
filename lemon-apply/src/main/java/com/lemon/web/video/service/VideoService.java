package com.lemon.web.video.service;

import com.lemon.repository.CollectionDetailRepository;
import com.lemon.repository.UpDetailRepository;
import com.lemon.soa.api.dto.VideoDTO;
import com.lemon.soa.api.provider.VideoProvider;
import com.lemon.web.constant.base.ConstantBaseData;
import com.lemon.web.video.request.VideoRequest;
import com.lemon.web.video.response.VideoResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * VideoService
 *
 * @author sjp
 * @date 2019/5/7
 */
@Service
public class VideoService {
	@Resource
	private VideoProvider				videoProvider;
	@Resource
	private UpDetailRepository			upDetailRepository;
	@Resource
	private CollectionDetailRepository	collectionDetailRepository;

	/**
	 * 获取单个视频详情
	 *
	 * @param request req
	 * @param response res
	 */
	public void getVideo(VideoRequest request, VideoResponse response) {
		Long videoId = request.getVideoId();
		VideoDTO videoDTO = videoProvider.getVideo(videoId);
		// 如果当前已有用户登陆，则需要查询当前用户的数据
		if (request.getUid() != null) {
			// 是否已经点赞
			int upNum = upDetailRepository.countAllByVideoIdAndCreateIdAndIsDel(videoId, request.getUid(),
					ConstantBaseData.IS_DELETE.FALSE.code);
			// 是否已经收藏
			int collectNum = collectionDetailRepository.countAllByVideoIdAndCreateIdAndIsDel(videoId, request.getUid(),
					ConstantBaseData.IS_DELETE.FALSE.code);
			videoDTO.setIsCollect(
					collectNum <= 0 ? ConstantBaseData.IS_DO.FALSE.code : ConstantBaseData.IS_DO.TRUE.code);
			videoDTO.setIsUp(upNum <= 0 ? ConstantBaseData.IS_DO.FALSE.code : ConstantBaseData.IS_DO.TRUE.code);
			response.setVideoDTO(videoDTO);
			return;
		}
		videoDTO.setIsCollect(ConstantBaseData.IS_DO.FALSE.code);
		videoDTO.setIsUp(ConstantBaseData.IS_DO.FALSE.code);
		response.setVideoDTO(videoDTO);
	}

}
