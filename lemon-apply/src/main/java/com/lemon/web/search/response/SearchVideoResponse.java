package com.lemon.web.search.response;

import com.lemon.soa.api.dto.VideoDTO;
import com.lemon.web.base.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * SearchVideoResponse
 *
 * @author sjp
 * @date 2019/5/23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SearchVideoResponse extends BaseResponse {
	/**
	 * 视频结果
	 */
	private List<VideoDTO> videoDTOList;
}
