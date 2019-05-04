package com.lemon.api.impl;

import com.google.common.collect.Iterables;
import com.lemon.entity.*;
import com.lemon.repository.*;
import com.lemon.service.VideoService;
import com.lemon.soa.api.dto.*;
import com.lemon.soa.api.provider.VideoProvider;
import com.lemon.tools.RedissonTools;
import com.lemon.web.constant.ConstantBaseData;
import com.lemon.web.constant.ConstantBizFile;
import com.lemon.web.constant.ConstantCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author sjp
 * @date 2019/1/24
 **/
@Service
public class VideoServiceImpl implements VideoProvider {

	private Logger				logger	= LoggerFactory.getLogger(this.getClass());

	@Resource
	private RedissonTools		redissonTools;
	@Resource
	private VideoService		videoService;
	@Resource
	private VideoRepository		videoRepository;
	@Resource
	private BizFileRepository	bizFileRepository;
	@Resource
	private CategoryRepository	categoryRepository;
	@Resource
	private RemarkRepository	remarkRepository;
	@Resource
	private LoginInfoRepository	loginInfoRepository;

	@Override
	public VideoDTO getVideo(long videoId) {
		VideoDTO videoDTO = new VideoDTO();
		// 视频详情
		VideoDetailDTO videoDetailDTO = new VideoDetailDTO();
		// 文件
		List<BizFileDTO> bizFileDTOList = new LinkedList<>();
		// 分类DTO
		CategoryDTO categoryDTO = new CategoryDTO();
		// 评价
		List<RemarkDTO> remarkDTOList = new LinkedList<>();
		videoDTO.setVideoDetailDTO(videoDetailDTO);
		videoDTO.setBizFileDTOList(bizFileDTOList);
		videoDTO.setCategoryDTO(categoryDTO);
		videoDTO.setRemarkDTO(remarkDTOList);

		Optional<VideoEntity> optionalVideoEntity = videoRepository.findById(videoId);
		if (optionalVideoEntity.isPresent()) {
			// 详情
			VideoEntity videoEntity = optionalVideoEntity.get();
			BeanUtils.copyProperties(videoEntity, videoDetailDTO);
			Optional<LoginInfoEntity> loginInfoEntityOptional = loginInfoRepository.findById(videoEntity.getUserId());
			loginInfoEntityOptional
					.ifPresent(loginInfoEntity -> videoDetailDTO.setUserName(loginInfoEntity.getLoginName()));
			// 文件
			List<BizFileEntity> bizFileEntityList = bizFileRepository.findAllByLinkTypeAndLinkIdAndIsDel(
					ConstantBizFile.LINK_TYPE.VIDEO.code, videoId, ConstantBaseData.IS_DELETE.FALSE.code);
			if (!CollectionUtils.isEmpty(bizFileEntityList)) {
				if (bizFileEntityList.size() > 1) {
					logger.info("more than one file effect->linkId:{}", videoId);
				}
///				BeanUtils.copyProperties(bizFileEntityList.get(0), bizFileDTO);
			}
			// 分类
			this.getCategoryById(videoEntity.getCategoryId(), categoryDTO);
			// 一级评价
			List<RemarkEntity> remarkEntityList = remarkRepository.findAllByVideoIdAndParentIdAndDelFlag(videoId, -1L,
					ConstantBaseData.IS_DELETE.FALSE.code);
			remarkEntityList.forEach(item -> {
				RemarkDTO remarkDTO = new RemarkDTO();
				BeanUtils.copyProperties(item, remarkDTO);
				// 查询子评论
				// TODO 待完成
				remarkDTOList.add(remarkDTO);
			});
		}
		return videoDTO;
	}

	private void getCategoryById(long categoryId, CategoryDTO categoryDTO) {
		List<CategoryDTO> subCategoryDTOList = new LinkedList<>();
		categoryDTO.setSubCategoryDTOList(subCategoryDTOList);
		// 查询一级
		Optional<CategoryEntity> categoryEntityOptional = categoryRepository.findById(categoryId);
		if (categoryEntityOptional.isPresent()) {
			// 子分类
			CategoryEntity categoryEntity = categoryEntityOptional.get();
			CategoryDTO subCategoryDTO = new CategoryDTO();
			BeanUtils.copyProperties(categoryEntity, subCategoryDTO);
			subCategoryDTOList.add(subCategoryDTO);
			// 查询上级分类
			Optional<CategoryEntity> categoryEntityOptionalTop = categoryRepository
					.findById(categoryEntity.getParentId());
			if (categoryEntityOptionalTop.isPresent()) {
				CategoryEntity categoryEntityTop = categoryEntityOptionalTop.get();
				BeanUtils.copyProperties(categoryEntityTop, categoryDTO);
			}
			// TODO 待完成
		}
	}

	@Override
	public VideoDetailDTO getVideoDetail(long videoId) {
		Optional<VideoEntity> optionalVideoEntity = videoRepository.findById(videoId);
		if (optionalVideoEntity.isPresent()) {
			// 详情
			VideoEntity videoEntity = optionalVideoEntity.get();
			VideoDetailDTO videoDetailDTO = new VideoDetailDTO();
			BeanUtils.copyProperties(videoEntity, videoDetailDTO);
			return videoDetailDTO;
		}
		return null;
	}

	@Override
	public List<VideoDTO> getVideoOrderBySortKey(short sortKey, short sortValue) {
		// 从缓存取数据
		List<VideoDTO> videoDTOList = redissonTools
				.get(ConstantCache.KEY.INDEX_VIDEO_LIST.key + sortKey + "_" + sortValue);
		if (!CollectionUtils.isEmpty(videoDTOList)) {
			return videoDTOList;
		}
		// 主动加载数据
		videoDTOList = videoService.getVideoOrderBySortKey(sortKey, sortValue);
		redissonTools.set(ConstantCache.KEY.INDEX_VIDEO_LIST.key + sortKey + "_" + sortValue, videoDTOList);
		return videoDTOList;
	}

	@Override
	public List<VideoDTO> getVideoListByCategoryId(Long categoryId, int pageIndex, int pageSize) {
		// 从缓存取数据
		List<VideoDTO> videoDTOList = redissonTools
				.get(ConstantCache.KEY.CATEGORY_VIDEO_LIST.key + categoryId);
		if (!CollectionUtils.isEmpty(videoDTOList)) {
			return videoDTOList;
		}
		// 主动加载数据
		List<VideoDTO> allVideoDTOList = videoService.getVideoListByCategoryId(categoryId);
		videoDTOList = this.getPageSizeVideoList(allVideoDTOList, pageIndex, pageSize);
		redissonTools.set(ConstantCache.KEY.CATEGORY_VIDEO_LIST.key + categoryId, videoDTOList);
		return videoDTOList;
	}

	private List<VideoDTO> getPageSizeVideoList(List<VideoDTO> allVideoDTOList, int pageIndex,
			int pageSize) {
		if (allVideoDTOList.size() > pageSize) {
			// 分页
			Iterable<List<VideoDTO>> pagesIterable = Iterables.partition(allVideoDTOList, pageSize);
			if (Iterables.size(pagesIterable) < pageIndex) {
				// 超出分页范围
				return Collections.emptyList();
			}
			// 取出第pageIndex页
			int index = 1;
			for (List<VideoDTO> pageItemList : pagesIterable) {
				if (index == pageIndex) {
					return pageItemList;
				}
				index++;
			}
		}
		return allVideoDTOList;
	}

}
