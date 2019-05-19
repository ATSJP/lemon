package com.lemon.web.video.response;

import com.lemon.soa.api.dto.VideoDTO;
import com.lemon.soa.api.dto.VideoDetailDTO;
import com.lemon.web.base.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author sjp
 * @date 2019/5/1
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class VideoResponse extends BaseResponse {
	private VideoDTO		videoDTO;
	private VideoDetailDTO	videoDetailDTO;
	private List<VideoDTO>	videoDTOList;
}
