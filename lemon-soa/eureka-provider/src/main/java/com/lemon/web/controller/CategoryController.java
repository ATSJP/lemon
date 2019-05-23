package com.lemon.web.controller;

import com.lemon.soa.api.dto.CategoryDTO;
import com.lemon.soa.api.provider.CategoryProvider;
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

	@GetMapping(value = "/category/getTree")
	public List<CategoryDTO> getCategoryTree() {
		return categoryProvider.getCategoryTree();
	}

}
