package com.lemon.soa.api.provider;

import com.lemon.soa.api.dto.VideoDTO;
import com.lemon.soa.api.dto.VideoDetailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 视频服务
 *
 * @author sjp
 * @date 2019/1/24
 **/
@FeignClient("eureka-provider")
public interface VideoProvider {

	/**
	 * 获取视频所有信息
	 *
	 * @param videoId 视频id
	 * @return 视频信息
	 */
	@GetMapping(value = "/video/{videoId}")
	VideoDTO getVideo(@PathVariable("videoId") long videoId);

	/**
	 * 获取视频明细
	 *
	 * @param videoId 视频idd
	 * @return 视频明细
	 */
	@GetMapping(value = "/video/detail/{videoId}")
	VideoDetailDTO getVideoDetail(@PathVariable("videoId") long videoId);

	/**
	 * 从缓存中获取指定排序方式进行排名的视频列表,仅仅取出detail信息，在首页或推荐处使用
	 * 
	 * @param sortKey 排序key: 0 播放率 1 评价数
	 * @param sortValue 排序value: 0 升序 1 降序
	 * @return List<VideoDTO>
	 */
	@GetMapping(value = "/video/getVideoOrderBySortKey/{sortKey}/{sortValue}")
	List<VideoDTO> getVideoOrderBySortKey(@PathVariable(value = "sortKey") short sortKey,
			@PathVariable(value = "sortValue") short sortValue);

}
