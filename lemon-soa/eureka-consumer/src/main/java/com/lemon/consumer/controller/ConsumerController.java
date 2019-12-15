package com.lemon.consumer.controller;

import com.lemon.soa.api.dto.CategoryDTO;
import com.lemon.soa.api.dto.VideoDTO;
import com.lemon.soa.api.provider.CategoryProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sjp
 * @date 2019/1/24
 **/
@RestController
public class ConsumerController {

	@Resource
	private RestTemplate restTemplate;

	@GetMapping("/{id}")
	public VideoDTO index(@PathVariable(value = "id") Long id) {
		return restTemplate.getForObject("http://provider/video/" + id, VideoDTO.class);
	}

	/**
	 * feign 方式
	 */
	@Resource
	private CategoryProvider	categoryProvider;

	@GetMapping(value = "/category")
	public List<CategoryDTO> getCategoryTree() {
		return categoryProvider.getCategoryTree();
	}

}
