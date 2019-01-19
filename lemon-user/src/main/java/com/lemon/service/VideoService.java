package com.lemon.service;

import com.lemon.tools.RedissonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author sjp
 * @date 2019/1/16
 **/
@Service
public class VideoService {

    @Autowired
    private RedissonTools redissonTools;

    public String getVideo(String videoId) {
        String video = redissonTools.get(videoId);
        if (StringUtils.isEmpty(video)) {
            video = videoId;
            redissonTools.set(videoId, video);
        }
        return video;
    }
}
