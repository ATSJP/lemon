package com.lemon.web.file.response;

import com.lemon.soa.api.dto.BizFileDTO;
import com.lemon.web.base.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * FileResponse
 *
 * @author sjp
 * @date 2019/5/19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FileResponse extends BaseResponse {
	private List<BizFileDTO> fileDTOList;
}
