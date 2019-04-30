package com.lemon.web.video.rest;

import com.lemon.web.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author sjp
 * @date 2019/1/16
 **/
@Controller
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @ResponseBody
    @RequestMapping("/get")
    public String getVideo(@RequestParam String videoId) {
        return videoService.getVideo(videoId);
    }

}

