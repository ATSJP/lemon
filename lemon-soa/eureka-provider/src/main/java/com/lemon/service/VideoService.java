package com.lemon.service;

import com.lemon.entity.CollectionDetailEntity;
import com.lemon.entity.LoginInfoEntity;
import com.lemon.entity.VideoEntity;
import com.lemon.repository.*;
import com.lemon.soa.api.contant.ConstantVideo;
import com.lemon.soa.api.dto.BizFileDTO;
import com.lemon.soa.api.dto.VideoDTO;
import com.lemon.soa.api.dto.VideoDetailDTO;
import com.lemon.soa.api.provider.FileProvider;
import com.lemon.utils.DateUtils;
import com.lemon.utils.PageUtils;
import com.lemon.web.constant.ConstantBizFile;
import com.lemon.web.constant.base.ConstantBaseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * VideoManager
 *
 * @author sjp
 * @date 2019/5/3
 */
@Service
public class VideoService {

	private Logger						logger	= LoggerFactory.getLogger(this.getClass());
	@Resource
	private VideoRepository				videoRepository;
	@Resource
	private PlayDetailRepository		playDetailRepository;
	@Resource
	private RemarkRepository			remarkRepository;
	@Resource
	private CollectionDetailRepository	collectionDetailRepository;
	@Resource
	private LoginInfoRepository			loginInfoRepository;
	@Resource
	private FileProvider				fileProvider;

	/**
	 * 根据当前排序规则，删选数据
	 *
	 * @param sortKey 排序key: 0 播放率 1 评价数
	 * @param sortValue 排序value: 0 升序 1 降序
	 * @return List<VideoDTO>
	 */
	public List<VideoDTO> getVideoOrderBySortKeyFromCache(short sortKey, short sortValue) {
		String sortValueSql = ConstantVideo.SORT_VALUE.transformCodeToSqlKey(sortValue);
		List<VideoDTO> videoDTOList = new LinkedList<>();
		if (ConstantVideo.SORT_KEY.PLAY_NUM.code == sortKey) {
			this.getVideoListByCountPlayNum(sortValueSql, videoDTOList);
		}
		if (ConstantVideo.SORT_KEY.COMMENT_NUM.code == sortKey) {
			this.getVideoListByCountRemarkNum(sortValueSql, videoDTOList);
		}
		return videoDTOList;
	}

	/**
	 * 根据评论数进行排序，筛选数据
	 * 
	 * @param sortValueSql 排序规则
	 * @param videoDTOList 视频列表
	 */
	private void getVideoListByCountRemarkNum(String sortValueSql, List<VideoDTO> videoDTOList) {
		List<Map<String, Object>> resultMap = remarkRepository.countRemarkNumByVideoId(sortValueSql);
		if (!CollectionUtils.isEmpty(resultMap)) {
			resultMap.forEach(item -> {
				Long videoId = Long.parseLong(item.get("video_id").toString());
				Long totalRemarkNum = Long.parseLong(item.get("total_remark_num").toString());
				logger.info("query video info->videoId:{},totalRemarkNum:{}", videoId, totalRemarkNum);
				VideoDTO videoDTO = this.getVideoDTOById(videoId);
                // 仅展示已审核过的视频
                if (ConstantVideo.AUDIT_STATUS.PASS.code.equals(videoDTO.getVideoDetailDTO().getAuditStatus())) {
                    videoDTOList.add(videoDTO);
                }
			});
		}
	}

	/**
	 * 根据播放次数排序，筛选数据
	 * 
	 * @param sortValueSql 排序规则
	 * @param videoDTOList 视频列表
	 */
	private void getVideoListByCountPlayNum(String sortValueSql, List<VideoDTO> videoDTOList) {
		List<Map<String, Object>> resultMap = playDetailRepository.countPlayNumByVideoId(sortValueSql);
		if (!CollectionUtils.isEmpty(resultMap)) {
			resultMap.forEach(item -> {
				Long videoId = Long.parseLong(item.get("video_id").toString());
				Long totalPlayNum = Long.parseLong(item.get("total_play_num").toString());
				logger.info("query video info->videoId:{},totalPlayNum:{}", videoId, totalPlayNum);
				VideoDTO videoDTO = this.getVideoDTOById(videoId);
				// 仅展示已审核过的视频
				if (ConstantVideo.AUDIT_STATUS.PASS.code.equals(videoDTO.getVideoDetailDTO().getAuditStatus())) {
					videoDTOList.add(videoDTO);
				}
			});
		}
	}

	private VideoDTO getVideoDTOById(Long videoId) {
		VideoDTO videoDTO = new VideoDTO();
		VideoDetailDTO videoDetailDTO = this.getVideoSimpleDetail(videoId);
		videoDTO.setVideoDetailDTO(videoDetailDTO);
		return videoDTO;
	}

	public VideoDetailDTO getVideoSimpleDetail(long videoId) {
		// 视频明细
		VideoDetailDTO videoDetailDTO = new VideoDetailDTO();
		Optional<VideoEntity> optionalVideoEntity = videoRepository.findById(videoId);
		if (optionalVideoEntity.isPresent()) {
			// 明细
			VideoEntity videoEntity = optionalVideoEntity.get();
			BeanUtils.copyProperties(videoEntity, videoDetailDTO);
			Optional<LoginInfoEntity> loginInfoOptional = loginInfoRepository.findById(videoEntity.getUserId());
			videoDetailDTO.setPlayNum(playDetailRepository.countAllByVideoId(videoEntity.getVideoId()));
			loginInfoOptional.ifPresent(loginInfoEntity -> videoDetailDTO.setUserName(loginInfoEntity.getLoginName()));
			videoDetailDTO.setCreateTime(DateUtils.formatTimestamp(videoEntity.getCreateTime().getTime()));
		}
		return videoDetailDTO;
	}

	/**
	 * 根据分类获取视频列表
	 * 
	 * @param categoryId 分类id
	 * @param auditStatus 审核状态
	 * @return List<VideoDetailDTO>
	 */
	public List<VideoDTO> getVideoListByCategoryIdFromCache(Long categoryId, ConstantVideo.AUDIT_STATUS auditStatus) {
		List<VideoEntity> videoEntityList = videoRepository.findAllByCategoryIdAndAuditStatus(categoryId,
				auditStatus.code);
		if (!CollectionUtils.isEmpty(videoEntityList)) {
			List<VideoDTO> videoDTOList = new LinkedList<>();
			videoEntityList.forEach(item -> {
				VideoDTO videoDTO = this.assemblePackVideo(item);
				videoDTOList.add(videoDTO);
			});
			return videoDTOList;
		}
		return Collections.emptyList();
	}

	/**
	 * 获取视频的简要信息，及封面图片等
	 * 
	 * @param item VideoEntity
	 * @return VideoDTO
	 */
	private VideoDTO assemblePackVideo(VideoEntity item) {
		VideoDTO videoDTO = new VideoDTO();
		// 详情
		VideoDetailDTO videoDetailDTO = new VideoDetailDTO();
		BeanUtils.copyProperties(item, videoDetailDTO);
		Optional<LoginInfoEntity> loginInfoEntityOptional = loginInfoRepository.findById(item.getUserId());
		loginInfoEntityOptional
				.ifPresent(loginInfoEntity -> videoDetailDTO.setUserName(loginInfoEntity.getLoginName()));
		videoDetailDTO.setPlayNum(playDetailRepository.countAllByVideoId(item.getVideoId()));
		videoDTO.setVideoDetailDTO(videoDetailDTO);
		// 图片文件
		BizFileDTO picFileDTO = fileProvider.getEffectFile(ConstantBizFile.LINK_TYPE.PIC.code, item.getVideoId());
		// 组装
		List<BizFileDTO> bizFileDTOList = new LinkedList<>();
		bizFileDTOList.add(picFileDTO);
		videoDTO.setBizFileDTOList(bizFileDTOList);
		return videoDTO;
	}

	/**
	 * 分页查找当前用户的视频列表
	 *
	 * @param loginId 会员id
	 * @param pageIndex 页面
	 * @param size 分页大小
	 * @return List<VideoDTO>
	 */
	public List<VideoDTO> getVideoListByLoginId(Long loginId, int pageIndex, int size) {
		List<VideoDTO> videoDTOList = new LinkedList<>();
		List<VideoEntity> videoEntityList = videoRepository.findAllByUserId(loginId);
		if (!CollectionUtils.isEmpty(videoEntityList)) {
			videoEntityList = PageUtils.getPageList(videoEntityList, pageIndex, size);
			videoEntityList.forEach(item -> {
				VideoDTO videoDTO = this.assemblePackVideo(item);
				videoDTOList.add(videoDTO);
			});
			return videoDTOList;
		}
		return videoDTOList;
	}

	/**
	 * 分页查找当前用户收藏的视频列表
	 *
	 * @param loginId 用户id
	 * @return List<VideoDetailDTO>
	 */
	public List<VideoDTO> getCollectVideoListByLoginId(Long loginId, int pageIndex, int size) {
		List<CollectionDetailEntity> collectionDetailEntityList = collectionDetailRepository
				.findAllByCreateIdAndIsDel(loginId, ConstantBaseData.IS_DELETE.FALSE.code);
		if (!CollectionUtils.isEmpty(collectionDetailEntityList)) {
			List<VideoDTO> videoDTOList = new LinkedList<>();
			collectionDetailEntityList = PageUtils.getPageList(collectionDetailEntityList, pageIndex, size);
			collectionDetailEntityList.forEach(item -> {
				Optional<VideoEntity> optionalVideoEntity = videoRepository.findById(item.getVideoId());
				if (optionalVideoEntity.isPresent()) {
					// 明细
					VideoEntity videoEntity = optionalVideoEntity.get();
					VideoDTO videoDTO = this.assemblePackVideo(videoEntity);
					videoDTOList.add(videoDTO);
				}
			});
			return videoDTOList;
		}
		return Collections.emptyList();
	}
}
