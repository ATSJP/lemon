package com.lemon.api.impl;

import com.lemon.entity.BizFileEntity;
import com.lemon.entity.CategoryEntity;
import com.lemon.entity.RemarkEntity;
import com.lemon.entity.VideoEntity;
import com.lemon.repository.BizFileRepository;
import com.lemon.repository.CategoryRepository;
import com.lemon.repository.RemarkRepository;
import com.lemon.repository.VideoRepository;
import com.lemon.soa.api.dto.*;
import com.lemon.soa.api.provider.VideoProvider;
import com.lemon.web.constant.ConstantBaseData;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author sjp
 * @date 2019/1/24
 **/
@Service
public class VideoServiceImpl implements VideoProvider {

	@Resource
	private VideoRepository		videoRepository;
	@Resource
	private BizFileRepository	bizFileRepository;
	@Resource
	private CategoryRepository	categoryRepository;
	@Resource
	private RemarkRepository	remarkRepository;

	@Override
	public VideoDTO getVideo(long videoId) {
		VideoDTO videoDTO = new VideoDTO();
		// 视频详情
		VideoDetailDTO videoDetailDTO = new VideoDetailDTO();
		// 文件
		BizFileDTO bizFileDTO = new BizFileDTO();
		// 分类DTO
		CategoryDTO categoryDTO = new CategoryDTO();
		// 评价
		List<RemarkDTO> remarkDTOList = new LinkedList<>();
		videoDTO.setVideoDetailDTO(videoDetailDTO);
		videoDTO.setBizFileDTO(bizFileDTO);
		videoDTO.setCategoryDTO(categoryDTO);
		videoDTO.setRemarkDTO(remarkDTOList);

		Optional<VideoEntity> optionalVideoEntity = videoRepository.findById(videoId);
		if (optionalVideoEntity.isPresent()) {
			// 详情
			VideoEntity videoEntity = optionalVideoEntity.get();
			BeanUtils.copyProperties(videoEntity, videoDetailDTO);
			// 文件
			BizFileEntity bizFileEntity = bizFileRepository.findAllByLinkTypeAndLinkIdAndIsDel(0, videoId,
					ConstantBaseData.IS_DELETE.FALSE.code);
			if (bizFileEntity != null) {
				BeanUtils.copyProperties(bizFileEntity, bizFileDTO);
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
		List<CategoryDTO> categoryDTOList = new LinkedList<>();
		categoryDTO.setCategoryDTOList(categoryDTOList);
		// 查询一级
		Optional<CategoryEntity> categoryEntityOptional = categoryRepository.findById(categoryId);
		if (categoryEntityOptional.isPresent()) {
			// 子分类
			CategoryEntity categoryEntity = categoryEntityOptional.get();
			CategoryDTO subCategoryDTO = new CategoryDTO();
			BeanUtils.copyProperties(categoryEntity, subCategoryDTO);
			categoryDTOList.add(subCategoryDTO);
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
}
