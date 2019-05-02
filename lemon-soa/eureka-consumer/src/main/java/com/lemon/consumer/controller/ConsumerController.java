package com.lemon.consumer.controller;

import com.lemon.soa.api.dto.CategoryDTO;
import com.lemon.soa.api.dto.VideoDTO;
import com.lemon.soa.api.provider.CategoryProvider;
import com.lemon.soa.api.provider.VideoProvider;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/")
	public VideoDTO index() {
		return restTemplate.getForObject("http://eureka-provider/video/1", VideoDTO.class);
	}

	/**
	 * feign 方式
	 */
	@Resource
	private VideoProvider		videoProvider;
	@Resource
	private CategoryProvider	categoryProvider;

	@GetMapping(value = "/video")
	public VideoDTO video() {
		return videoProvider.getVideo(1);
	}

	@GetMapping(value = "/category")
	public List<CategoryDTO> getCategoryTree() {
		return categoryProvider.getCategoryTree();
	}

}
