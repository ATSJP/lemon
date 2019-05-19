package com.lemon.web.file.rest;

import com.lemon.web.file.request.FileRequest;
import com.lemon.web.file.response.FileResponse;
import com.lemon.web.file.service.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * FileController
 *
 * @author sjp
 * @date 2019/5/19
 */
@RestController
public class FileController {
	@Resource
	private FileService fileService;

	@PostMapping("/file/upload")
	public FileResponse upload(@Valid FileRequest request) {
		FileResponse response = new FileResponse();
		fileService.upload(request, response);
		return response;
	}

}
