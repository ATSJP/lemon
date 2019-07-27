package com.lemon.soa.api.provider;

import com.lemon.soa.api.dto.BizFileDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 文件服务
 *
 * @author sjp
 * @date 2019/5/1
 **/
@FeignClient("provider")
public interface FileProvider {

	/**
	 * 获取有效文件所有信息
	 *
	 * @param linkType 关联类型 ：0 视频 1 图片
	 * @param linkId 关联id
	 * @return BizFileDTO
	 */
	@GetMapping(value = "/file/{linkType}/{linkId}")
	BizFileDTO getEffectFile(@PathVariable(value = "linkType") short linkType,
			@PathVariable(value = "linkId") long linkId);

}
