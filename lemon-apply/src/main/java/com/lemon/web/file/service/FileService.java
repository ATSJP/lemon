package com.lemon.web.file.service;

import com.lemon.config.ConfigProperties;
import com.lemon.entity.BizFileEntity;
import com.lemon.repository.BizFileRepository;
import com.lemon.soa.api.dto.BizFileDTO;
import com.lemon.utils.DateUtils;
import com.lemon.utils.FileUtils;
import com.lemon.web.constant.ConstantBizFile;
import com.lemon.web.constant.base.ConstantApi;
import com.lemon.web.constant.base.ConstantBaseData;
import com.lemon.web.file.request.FileRequest;
import com.lemon.web.file.response.FileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * FileService
 *
 * @author sjp
 * @date 2019/5/19
 */
@Service
public class FileService {
	private Logger				logger	= LoggerFactory.getLogger(this.getClass());
	@Resource
	private BizFileRepository	bizFileRepository;
	@Resource
	private ConfigProperties	configProperties;

	public void upload(FileRequest request, FileResponse response) {
		// Get file name
		MultipartFile[] files = request.getFiles();
		if (files == null) {
			response.setCode(ConstantApi.CODE.PARAM_NULL.getCode());
			response.setMsg(ConstantApi.FILE_UPLOAD.FAIL.getDesc());
			return;
		}
		String uploadedFileName = Arrays.stream(files).map(MultipartFile::getOriginalFilename)
				.filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));
		if (StringUtils.isEmpty(uploadedFileName)) {
			response.setCode(ConstantApi.CODE.FAIL.getCode());
			response.setMsg(ConstantApi.FILE_UPLOAD.FAIL.getDesc());
			return;
		}
		Long linkId = request.getLinkId();
		Short linkType = request.getLinkType();
		String basePath;
		if (ConstantBizFile.LINK_TYPE.VIDEO.code.equals(linkType)) {
			basePath = configProperties.getVideoSourceDir();
		} else if (ConstantBizFile.LINK_TYPE.PIC.code.equals(linkType)) {
			basePath = configProperties.getImageSourceDir();
		} else {
			response.setCode(ConstantApi.CODE.ILLEGAL_REQUEST.getCode());
			response.setMsg(ConstantApi.CODE.ILLEGAL_REQUEST.getDesc());
			return;
		}
		List<BizFileDTO> fileDtoList = new LinkedList<>();
		Arrays.asList(files).forEach(item -> {
			if (!item.isEmpty()) {
				String fileName = String.valueOf(System.currentTimeMillis());
				String fileSuffix = "." + FileUtils.getFileSuffix(item.getOriginalFilename());
				boolean isSaveFileSuccess = false;
				// 保存文件
				try {
					byte[] bytes = item.getBytes();
					Path path = Paths.get(basePath + fileName + fileSuffix);
					Files.write(path, bytes);
					isSaveFileSuccess = true;
				} catch (Exception e) {
					logger.error("upload fail->Exception:", e);
				}
				if (isSaveFileSuccess) {
					// 保存上传文件记录
					BizFileEntity fileEntity = new BizFileEntity();
					fileEntity.setLinkId(linkId);
					fileEntity.setLinkType(linkType);
					fileEntity.setFileName(fileName);
					fileEntity.setFileSuffix(fileSuffix);
					fileEntity.setIsDel(ConstantBaseData.IS_DELETE.FALSE.code);
					fileEntity.setCreateId(request.getUid());
					fileEntity.setCreateTime(DateUtils.getCurrentTime());
					fileEntity.setUpdateId(request.getUid());
					fileEntity.setUpdateTime(DateUtils.getCurrentTime());
					bizFileRepository.save(fileEntity);
					BizFileDTO bizFileDTO = new BizFileDTO();
					bizFileDTO.setFileId(fileEntity.getFileId());
					bizFileDTO.setFileName(fileEntity.getFileName());
					fileDtoList.add(bizFileDTO);
				}
			}
		});
		response.setCode(ConstantApi.CODE.SUCCESS.getCode());
		response.setFileDTOList(fileDtoList);
	}

}
