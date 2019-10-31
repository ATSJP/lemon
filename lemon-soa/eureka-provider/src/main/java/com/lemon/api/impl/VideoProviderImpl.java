package com.lemon.api.impl;

import com.lemon.entity.BizFileEntity;
import com.lemon.entity.CategoryEntity;
import com.lemon.entity.RemarkEntity;
import com.lemon.repository.BizFileRepository;
import com.lemon.repository.CategoryRepository;
import com.lemon.repository.RemarkRepository;
import com.lemon.repository.VideoRepository;
import com.lemon.service.VideoService;
import com.lemon.soa.api.contant.ConstantVideo;
import com.lemon.soa.api.dto.*;
import com.lemon.soa.api.provider.VideoProvider;
import com.lemon.tools.RedissonTools;
import com.lemon.utils.DateUtils;
import com.lemon.utils.PageUtils;
import com.lemon.web.constant.base.ConstantBaseData;
import com.lemon.web.constant.base.ConstantCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author sjp
 * @date 2019/1/24
 **/
@Service
public class VideoProviderImpl implements VideoProvider {

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

	@Override
	public VideoDetailDTO getVideoSimpleDetail(long videoId) {
		return videoService.getVideoSimpleDetail(videoId);
	}

	@Override
	public VideoDTO getVideo(long videoId) {
		VideoDTO videoDTO = new VideoDTO();
		// 视频详情
		VideoDetailDTO videoDetailDTO;
		// 文件
		List<BizFileDTO> bizFileDTOList = new LinkedList<>();
		// 分类DTO
		CategoryDTO categoryDTO = new CategoryDTO();
		// 评价
		List<RemarkDTO> remarkDTOList = new LinkedList<>();
		// 详情
		videoDetailDTO = this.getVideoSimpleDetail(videoId);
		// 文件
		List<BizFileEntity> bizFileEntityList = bizFileRepository.findAllByLinkIdAndIsDel(videoId,
				ConstantBaseData.IS_DELETE.FALSE.code);
		if (!CollectionUtils.isEmpty(bizFileEntityList)) {
			// 有效封面一张 有效视频文件一张,超过2张属于系统异常,日志打印
			int picAndVideoSize = 2;
			if (bizFileEntityList.size() > picAndVideoSize) {
				logger.error("more than one file effect->linkId:{}", videoId);
			}
			bizFileEntityList.forEach(item -> {
				BizFileDTO bizFileDTO = new BizFileDTO();
				BeanUtils.copyProperties(item, bizFileDTO);
				bizFileDTOList.add(bizFileDTO);
			});
		}
		// 分类
		this.getCategoryById(videoDetailDTO.getCategoryId(), categoryDTO);
		// 一级评价
		List<RemarkEntity> parentRemarkEntityList = remarkRepository.findAllByVideoIdAndParentIdAndIsDel(videoId, -1L,
				ConstantBaseData.IS_DELETE.FALSE.code);
		if (!CollectionUtils.isEmpty(parentRemarkEntityList)) {
			parentRemarkEntityList.forEach(parent -> {
				RemarkDTO remarkDTO = this.transformEntity2DTO(parent);
				// 查询子评论
				List<RemarkEntity> childRemarkEntityList = remarkRepository.findAllByVideoIdAndParentIdAndIsDel(videoId,
						parent.getRemarkId(), ConstantBaseData.IS_DELETE.FALSE.code);
				if (!CollectionUtils.isEmpty(childRemarkEntityList)) {
					List<RemarkDTO> childRemarkDTOList = new LinkedList<>();
					childRemarkEntityList.forEach(child -> childRemarkDTOList.add(this.transformEntity2DTO(child)));
					remarkDTO.setChildRemarkDTOList(childRemarkDTOList);
				}
				remarkDTOList.add(remarkDTO);
			});
		}
		// 组装数据
		videoDTO.setVideoDetailDTO(videoDetailDTO);
		videoDTO.setBizFileDTOList(bizFileDTOList);
		videoDTO.setCategoryDTO(categoryDTO);
		videoDTO.setRemarkDTO(remarkDTOList);
		return videoDTO;
	}

	private RemarkDTO transformEntity2DTO(RemarkEntity parent) {
		RemarkDTO remarkDTO = new RemarkDTO();
		BeanUtils.copyProperties(parent, remarkDTO);
		remarkDTO.setRemarkTime(DateUtils.formatTimestamp(parent.getCreateTime().getTime()));
		return remarkDTO;
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
		}
	}

	@Override
	public List<VideoDTO> getVideoOrderBySortKeyFromCache(short sortKey, short sortValue) {
		// 从缓存取数据
		List<VideoDTO> videoDTOList = redissonTools
				.get(ConstantCache.KEY.INDEX_VIDEO_LIST.key + sortKey + "_" + sortValue);
		if (!CollectionUtils.isEmpty(videoDTOList)) {
			return videoDTOList;
		}
		// 主动加载数据
		videoDTOList = videoService.getVideoOrderBySortKeyFromCache(sortKey, sortValue);
		redissonTools.set(ConstantCache.KEY.INDEX_VIDEO_LIST.key + sortKey + ConstantBaseData.CN + sortValue,
				videoDTOList);
		return videoDTOList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VideoDTO> getVideoListByCategoryIdFromCache(Long categoryId, int pageIndex, int pageSize) {
		// 从缓存取数据
		List<VideoDTO> videoDTOList = redissonTools.get(ConstantCache.KEY.CATEGORY_VIDEO_LIST.key + categoryId
				+ ConstantBaseData.CN + pageIndex + ConstantBaseData.CN + pageSize);
		if (!CollectionUtils.isEmpty(videoDTOList)) {
			return videoDTOList;
		}
		// 主动加载数据
		List<VideoDTO> allVideoDTOList = videoService.getVideoListByCategoryIdFromCache(categoryId,
				ConstantVideo.AUDIT_STATUS.PASS);
		videoDTOList = PageUtils.getPageList(allVideoDTOList, pageIndex, pageSize);
		redissonTools.set(ConstantCache.KEY.CATEGORY_VIDEO_LIST.key + categoryId + ConstantBaseData.CN + pageIndex
				+ ConstantBaseData.CN + pageSize, videoDTOList);
		return videoDTOList;
	}

	@Override
	public List<VideoDTO> getVideoListByLoginId(Long loginId, int pageIndex, int size) {
		return videoService.getVideoListByLoginId(loginId, pageIndex, size);
	}

	@Override
	public List<VideoDTO> getCollectVideoListByLoginId(Long loginId, int pageIndex, int size) {
		return videoService.getCollectVideoListByLoginId(loginId, pageIndex, size);
	}

}
