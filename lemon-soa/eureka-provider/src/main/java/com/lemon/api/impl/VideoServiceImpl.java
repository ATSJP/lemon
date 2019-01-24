package com.lemon.api.impl;

import org.springframework.stereotype.Service;

import com.lemon.soa.api.VideoService;

/**
 * @author sjp
 * @date 2019/1/24
 **/
@Service
public class VideoServiceImpl implements VideoService {
	@Override
	public double getVideo(long videoId) {
		return Math.random();
	}
}
