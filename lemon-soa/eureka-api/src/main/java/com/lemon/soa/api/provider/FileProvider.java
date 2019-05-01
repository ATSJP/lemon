package com.lemon.soa.api.provider;

import com.lemon.soa.api.dto.BizFileDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 文件服务
 *
 * @author sjp
 * @date 2019/5/1
 **/
@FeignClient("eureka-provider")
public interface FileProvider {

	/**
	 * 获取文件所有信息
	 * 
	 * @param linkId 关联id
	 * @param linkType 关联类型 ：0 视频 1 图片
	 * @return BizFileDTO
	 */
	@RequestMapping(value = "/file")
	BizFileDTO getFile(long linkId, long linkType);

}
