package com.lemon.web.video.service;

import com.lemon.entity.VideoEntity;
import com.lemon.repository.CollectionDetailRepository;
import com.lemon.repository.UpDetailRepository;
import com.lemon.repository.VideoRepository;
import com.lemon.soa.api.contant.ConstantVideo;
import com.lemon.soa.api.dto.VideoDTO;
import com.lemon.soa.api.dto.VideoDetailDTO;
import com.lemon.soa.api.provider.VideoProvider;
import com.lemon.utils.DateUtils;
import com.lemon.web.constant.base.ConstantApi;
import com.lemon.web.constant.base.ConstantBaseData;
import com.lemon.web.video.request.VideoRequest;
import com.lemon.web.video.response.VideoResponse;
import org.springframework.beans.BeanUtils;
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
	private VideoRepository				videoRepository;
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

	/**
	 * 添加视频
	 * 
	 * @param request req
	 * @param response res
	 */
	public void addVideo(VideoRequest request, VideoResponse response) {
		Long categoryId = request.getCategoryId();
		String videoName = request.getVideoName();
		String videoContext = request.getVideoContext();
		VideoEntity videoEntity = new VideoEntity();
		videoEntity.setVideoName(videoName);
		videoEntity.setVideoContext(videoContext);
		videoEntity.setCategoryId(categoryId);
		videoEntity.setUserId(request.getUid());
		videoEntity.setAuditStatus(ConstantVideo.AUDIT_STATUS.WAIT_AUDIT.code);
		videoEntity.setCreateId(request.getUid());
		videoEntity.setCreateTime(DateUtils.getCurrentTime());
		videoRepository.save(videoEntity);
		VideoDetailDTO videoDetailDTO = new VideoDetailDTO();
		videoDetailDTO.setVideoId(videoEntity.getVideoId());
		videoDetailDTO.setVideoName(videoName);
		response.setVideoDetailDTO(videoDetailDTO);
	}

	/**
	 * 修改视频
	 * 
	 * @param request req
	 * @param response res
	 */
	public void updateVideo(VideoRequest request, VideoResponse response) {
		Long videoId = request.getVideoId();
		if (videoId == null) {
			response.setCode(ConstantApi.CODE.PARAM_NULL.getCode());
			response.setMsg(ConstantApi.CODE.PARAM_NULL.getDesc());
			return;
		}
		VideoDetailDTO videoDetailDTO = videoProvider.getVideoSimpleDetail(videoId);
		if (videoDetailDTO == null || !ConstantVideo.AUDIT_STATUS.PASS.code.equals(videoDetailDTO.getAuditStatus())) {
			response.setCode(ConstantApi.CODE.ILLEGAL_REQUEST.getCode());
			response.setMsg(ConstantApi.CODE.ILLEGAL_REQUEST.getDesc());
			return;
		}
		VideoEntity videoEntity = new VideoEntity();
		BeanUtils.copyProperties(request, videoEntity);
		videoEntity.setUpdateId(request.getUid());
		videoEntity.setUpdateTime(DateUtils.getCurrentTime());
		videoRepository.save(videoEntity);
	}
}
