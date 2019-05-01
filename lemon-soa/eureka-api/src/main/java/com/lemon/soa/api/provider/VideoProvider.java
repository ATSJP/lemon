package com.lemon.soa.api.provider;

import com.lemon.soa.api.dto.VideoDTO;
import com.lemon.soa.api.dto.VideoDetailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
	@RequestMapping(value = "/video/{videoId}")
    VideoDTO getVideo(@PathVariable("videoId") long videoId);

    /**
     * 获取视频明细
     *
     * @param videoId 视频idd
     * @return 视频明细
     */
    @RequestMapping(value = "/video/detail/{videoId}")
    VideoDetailDTO getVideoDetail(@PathVariable("videoId") long videoId);
}
