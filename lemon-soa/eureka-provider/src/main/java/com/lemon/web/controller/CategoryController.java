package com.lemon.web.controller;

import com.lemon.soa.api.dto.CategoryDTO;
import com.lemon.soa.api.provider.CategoryProvider;
import com.lemon.web.constant.base.ConstantApi;
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
	 * 前台获取分类树(带缓存)
	 *
	 * @return List<CategoryDTO> 分类树
	 */
	@GetMapping(value = "/category/getTreeCache")
	public List<CategoryDTO> getCategoryTreeCache() {
		return categoryProvider.getCategoryTreeCache();
	}

	/**
	 * 获取分类树(DB查询)
	 *
	 * @return List<CategoryDTO> 分类树
	 */
	@GetMapping(value = "/category/getTree")
	public List<CategoryDTO> getCategoryTree() {
		return categoryProvider.getCategoryTree();
	}

	/**
	 * 刷新分类树缓存
	 *
	 * @return List<CategoryDTO> 分类树
	 */
	@GetMapping(value = "/category/refreshTreeCache")
	public String refreshCategoryTreeCache() {
		categoryProvider.refreshCategoryTreeCache();
		return ConstantApi.CODE.SUCCESS.name();
	}

}
