package com.lemon.web.category.rest;

import com.lemon.soa.api.dto.CategoryDTO;
import com.lemon.soa.api.provider.CategoryProvider;
import com.lemon.web.category.response.CategoryResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * CategoryController
 *
 * @author sjp
 * @date 2019/5/2
 */
@RestController
public class CategoryController {

	@Resource
	private CategoryProvider categoryProvider;

	/**
	 * 获取分类树(带缓存)
	 *
	 * @return List<CategoryDTO>
	 */
	@GetMapping("/category/tree")
	public CategoryResponse getTree() {
		CategoryResponse response = new CategoryResponse();
		List<CategoryDTO> categoryDtoList = categoryProvider.getCategoryTreeCache();
		response.setCategoryDTOList(categoryDtoList);
		return response;
	}

}
