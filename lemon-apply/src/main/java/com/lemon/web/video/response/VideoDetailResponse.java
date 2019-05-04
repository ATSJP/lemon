package com.lemon.web.video.response;

import com.lemon.soa.api.dto.VideoDetailDTO;
import com.lemon.web.base.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * VideoDetailResponse
 *
 * @author sjp
 * @date 2019/5/4
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class VideoDetailResponse extends BaseResponse {
	private List<VideoDetailDTO> videoDetailDTOList;
}
