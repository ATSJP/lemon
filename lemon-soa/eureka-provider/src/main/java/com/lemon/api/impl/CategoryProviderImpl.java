package com.lemon.api.impl;

import com.lemon.entity.CategoryEntity;
import com.lemon.repository.CategoryRepository;
import com.lemon.soa.api.dto.CategoryDTO;
import com.lemon.soa.api.provider.CategoryProvider;
import com.lemon.web.constant.base.ConstantBaseData;
import org.springframework.beans.BeanUtils;
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

	@Resource
	private CategoryRepository categoryRepository;

	@Override
	public List<CategoryDTO> getCategoryTree() {
		List<CategoryDTO> categoryDTOList = new LinkedList<>();
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
					List<CategoryDTO> subCategoryDTOList = new LinkedList<>();
					this.transformEntityList2DTOList(subCategoryEntityList, subCategoryDTOList);
					categoryDTO.setSubCategoryDTOList(subCategoryDTOList);
				}
				categoryDTOList.add(categoryDTO);
			});
		}
		return categoryDTOList;
	}

	private void transformEntityList2DTOList(List<CategoryEntity> subCategoryEntityList,
			List<CategoryDTO> subCategoryDTOList) {
		subCategoryEntityList.forEach(item -> {
			CategoryDTO categoryDTO = new CategoryDTO();
			BeanUtils.copyProperties(item, categoryDTO);
			subCategoryDTOList.add(categoryDTO);
		});
	}
}
