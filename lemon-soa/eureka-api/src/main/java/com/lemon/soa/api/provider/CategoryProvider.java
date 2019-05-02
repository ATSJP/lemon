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
@FeignClient("eureka-provider")
public interface CategoryProvider {

	/**
	 * 获取有效文件所有信息
	 *
	 * @return List<CategoryDTO>
	 */
	@GetMapping(value = "/category/getTree")
	List<CategoryDTO> getCategoryTree();

}
