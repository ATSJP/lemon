package com.lemon.controller;

import com.lemon.soa.api.dto.BizFileDTO;
import com.lemon.soa.api.provider.FileProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * FileController
 *
 * @author sjp
 * @date 2019/5/2
 */
@RestController
public class FileController {

	@Resource
	private FileProvider fileProvider;

	@GetMapping(value = "/file/{linkType}/{linkId}")
	public BizFileDTO getEffectFile(@PathVariable(value = "linkType") short linkType,
			@PathVariable(value = "linkId") long linkId) {
		return fileProvider.getEffectFile(linkType, linkId);
	}

}
