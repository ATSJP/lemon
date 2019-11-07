package com.lemon.soa.api.provider;

import com.lemon.soa.api.dto.CategoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 分类服务
 *
 * @author sjp
 * @date 2019/5/2
 */
@FeignClient("provider")
public interface CategoryProvider {

	/**
	 * 获取分类树(DB查询)
	 *
	 * @return List<CategoryDTO>
	 */
	@GetMapping(value = "/category/getTree")
	List<CategoryDTO> getCategoryTree();

	/**
	 * 获取分类树(带缓存)
	 *
	 * @return List<CategoryDTO>
	 */
	@GetMapping(value = "/category/getTreeCache")
	List<CategoryDTO> getCategoryTreeCache();

	/**
	 * 刷新分类树缓存
	 *
	 */
	@GetMapping(value = "/category/refreshTreeCache")
	void refreshCategoryTreeCache();
}
