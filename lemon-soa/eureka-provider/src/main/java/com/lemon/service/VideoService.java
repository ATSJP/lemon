package com.lemon.service;

import com.lemon.entity.LoginInfoEntity;
import com.lemon.entity.VideoEntity;
import com.lemon.repository.LoginInfoRepository;
import com.lemon.repository.PlayDetailRepository;
import com.lemon.repository.RemarkRepository;
import com.lemon.repository.VideoRepository;
import com.lemon.soa.api.contant.ConstantVideo;
import com.lemon.soa.api.dto.BizFileDTO;
import com.lemon.soa.api.dto.VideoDTO;
import com.lemon.soa.api.dto.VideoDetailDTO;
import com.lemon.soa.api.provider.FileProvider;
import com.lemon.web.constant.ConstantBizFile;
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

	private Logger					logger	= LoggerFactory.getLogger(this.getClass());
	@Resource
	private VideoRepository			videoRepository;
	@Resource
	private PlayDetailRepository	playDetailRepository;
	@Resource
	private RemarkRepository		remarkRepository;
	@Resource
	private LoginInfoRepository		loginInfoRepository;
	@Resource
	private FileProvider			fileProvider;

	/**
	 * 根据当前排序规则，删选数据
	 *
	 * @param sortKey 排序key: 0 播放率 1 评价数
	 * @param sortValue 排序value: 0 升序 1 降序
	 * @return List<VideoDTO>
	 */
	public List<VideoDTO> getVideoOrderBySortKey(short sortKey, short sortValue) {
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

	private void getVideoListByCountRemarkNum(String sortValueSql, List<VideoDTO> videoDTOList) {
		List<Map<String, Object>> resultMap = remarkRepository.countRemarkNumByVideoId(sortValueSql);
		if (!CollectionUtils.isEmpty(resultMap)) {
			resultMap.forEach(item -> {
				Long videoId = Long.parseLong(item.get("video_id").toString());
				Long totalRemarkNum = Long.parseLong(item.get("total_remark_num").toString());
				logger.info("query video info->videoId:{},totalRemarkNum:{}", videoId, totalRemarkNum);
				VideoDTO videoDTO = getVideoDTOById(videoId);
				videoDTOList.add(videoDTO);
			});
		}
	}

	private void getVideoListByCountPlayNum(String sortValueSql, List<VideoDTO> videoDTOList) {
		List<Map<String, Object>> resultMap = playDetailRepository.countPlayNumByVideoId(sortValueSql);
		if (!CollectionUtils.isEmpty(resultMap)) {
			resultMap.forEach(item -> {
				Long videoId = Long.parseLong(item.get("video_id").toString());
				Long totalPlayNum = Long.parseLong(item.get("total_play_num").toString());
				logger.info("query video info->videoId:{},totalPlayNum:{}", videoId, totalPlayNum);
				VideoDTO videoDTO = getVideoDTOById(videoId);
				videoDTOList.add(videoDTO);
			});
		}
	}

	private VideoDTO getVideoDTOById(Long videoId) {
		Optional<VideoEntity> videoEntityOptional = videoRepository.findById(videoId);
		if (videoEntityOptional.isPresent()) {
			VideoEntity videoEntity = videoEntityOptional.get();
			VideoDTO videoDTO = new VideoDTO();
			VideoDetailDTO videoDetailDTO = new VideoDetailDTO();
			BeanUtils.copyProperties(videoEntity, videoDetailDTO);
			videoDTO.setVideoDetailDTO(videoDetailDTO);
			return videoDTO;
		}
		return null;
	}

	/**
	 * 根据分类获取视频列表
	 * 
	 * @param categoryId 分类id
	 * @return List<VideoDetailDTO>
	 */
	public List<VideoDTO> getVideoListByCategoryId(Long categoryId) {
		List<VideoEntity> videoEntityList = videoRepository.findAllByCategoryId(categoryId);
		if (!CollectionUtils.isEmpty(videoEntityList)) {
			List<VideoDTO> videoDTOList = new LinkedList<>();
			videoEntityList.forEach(item -> {
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
				BizFileDTO picFileDTO = fileProvider.getEffectFile(ConstantBizFile.LINK_TYPE.PIC.code,
						item.getVideoId());
				// 组装
				List<BizFileDTO> bizFileDTOList = new LinkedList<>();
				bizFileDTOList.add(picFileDTO);
				videoDTO.setBizFileDTOList(bizFileDTOList);
				videoDTOList.add(videoDTO);
			});
			return videoDTOList;
		}
		return Collections.emptyList();
	}
}
