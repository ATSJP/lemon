package com.lemon.soa.api;

/**
 * @author sjp
 * @date 2019/1/24
 **/
public interface VideoService {
	/**
	 * 获取视频信息
	 * @param videoId 视频id
	 * @return 视频信息
	 */
	double getVideo(long videoId);
}
