package com.lemon.api.impl;

import com.lemon.entity.CategoryEntity;
import com.lemon.repository.CategoryRepository;
import com.lemon.soa.api.dto.CategoryDTO;
import com.lemon.soa.api.provider.CategoryProvider;
import com.lemon.tools.RedissonTools;
import com.lemon.web.constant.base.ConstantBaseData;
import com.lemon.web.constant.base.ConstantCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * CategoryProviderImpl
 *
 * @author sjp
 * @date 2019/5/2
 */
@Service
public class CategoryProviderImpl implements CategoryProvider {
	private Logger				logger	= LoggerFactory.getLogger(this.getClass());
	@Resource
	private CategoryRepository	categoryRepository;
	@Autowired
	private RedissonTools		redissonTools;

	/**
	 * 获取分类树(DB查询)
	 *
	 * @return List<CategoryDTO>
	 */
	@Override
	public List<CategoryDTO> getCategoryTree() {
		List<CategoryDTO> categoryDtoList = new LinkedList<>();
		// 获取所有一级目录
		List<CategoryEntity> categoryEntityList = categoryRepository.findAllByParentIdAndDisplay(-1L,
				ConstantBaseData.IS_DISPLAY.DISPLAY.code);
		if (!CollectionUtils.isEmpty(categoryEntityList)) {
			categoryEntityList.forEach(item -> {
				// 目录包装类型
				CategoryDTO categoryDTO = new CategoryDTO();
				BeanUtils.copyProperties(item, categoryDTO);
				// 根据当前一级分类，查询所有二级分类
				List<CategoryEntity> subCategoryEntityList = categoryRepository
						.findAllByParentIdAndDisplay(item.getCategoryId(), ConstantBaseData.IS_DISPLAY.DISPLAY.code);
				if (CollectionUtils.isEmpty(subCategoryEntityList)) {
					categoryDTO.setSubCategoryDTOList(Collections.emptyList());
				} else {
					List<CategoryDTO> subCategoryDtoList = new LinkedList<>();
					this.transformEntityList2DtoList(subCategoryEntityList, subCategoryDtoList);
					categoryDTO.setSubCategoryDTOList(subCategoryDtoList);
				}
				categoryDtoList.add(categoryDTO);
			});
		}
		return categoryDtoList;
	}

	/**
	 * 获取分类树(带缓存)
	 *
	 * @return List<CategoryDTO>
	 */
	@Override
	public List<CategoryDTO> getCategoryTreeCache() {
		List<CategoryDTO> categoryDtoList = redissonTools.get(ConstantCache.KEY.CATAGORY_TREE.key);
		if (CollectionUtils.isEmpty(categoryDtoList)) {
			categoryDtoList = this.getCategoryTree();
			redissonTools.set(ConstantCache.KEY.CATAGORY_TREE.key, categoryDtoList,
					ConstantCache.KEY.CATAGORY_TREE.timeOut);
		}
		return categoryDtoList;
	}

	/**
	 * 刷新分类树缓存
	 */
	@Override
	public void refreshCategoryTreeCache() {
		List<CategoryDTO> categoryDtoList = this.getCategoryTree();
		redissonTools.set(ConstantCache.KEY.CATAGORY_TREE.key, categoryDtoList,
				ConstantCache.KEY.CATAGORY_TREE.timeOut);
	}

	/**
	 * transform entity 2 dto
	 * 
	 * @param subCategoryEntityList entityList
	 * @param subCategoryDtoList dtoList
	 */
	private void transformEntityList2DtoList(List<CategoryEntity> subCategoryEntityList,
			List<CategoryDTO> subCategoryDtoList) {
		subCategoryEntityList.forEach(item -> {
			CategoryDTO categoryDTO = new CategoryDTO();
			BeanUtils.copyProperties(item, categoryDTO);
			subCategoryDtoList.add(categoryDTO);
		});
	}
}
