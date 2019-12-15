package com.lemon.consumer.controller;

import com.lemon.soa.api.dto.CategoryDTO;
import com.lemon.soa.api.dto.VideoDTO;
import com.lemon.soa.api.provider.CategoryProvider;
import com.lemon.soa.api.provider.VideoProvider;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * FeginMutiNameController Fegin的一个缺陷，feginName不可以重复定义（除set allow-bean-definition-overriding: true的第二种方案）
 * @author sjp
 * @date 2019/12/15
 */
@RestController
@Import(FeignClientsConfiguration.class)
public class FeginMutiNameController {

	private VideoProvider		videoProvider;
	private CategoryProvider	categoryProvider;

	public FeginMutiNameController(Decoder decoder, Encoder encoder, Client client, Contract contract) {
		this.videoProvider = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
				// 默认是Logger.NoOpLogger
				.logger(new Slf4jLogger(VideoProvider.class))
				// 默认是Logger.Level.NONE
				.logLevel(Logger.Level.FULL).target(VideoProvider.class, "http://provider");

		this.categoryProvider = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
				// 默认是Logger.NoOpLogger
				.logger(new Slf4jLogger(CategoryProvider.class))
				// 默认是Logger.Level.NONE
				.logLevel(Logger.Level.FULL).target(CategoryProvider.class, "http://provider");
	}

	@GetMapping(value = "/test1")
	public List<CategoryDTO> getCategoryTree() {
		return categoryProvider.getCategoryTree();
	}

	@GetMapping(value = "/test2/{id}")
	public VideoDTO index(@PathVariable(value = "id") Long id) {
		return videoProvider.getVideo(id);
	}

}
