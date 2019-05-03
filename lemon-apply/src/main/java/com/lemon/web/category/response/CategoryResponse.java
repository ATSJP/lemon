package com.lemon.web.category.response;

import com.lemon.soa.api.dto.CategoryDTO;
import com.lemon.web.base.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * CategoryResponse
 *
 * @author sjp
 * @date 2019/5/2
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CategoryResponse extends BaseResponse {
    private List<CategoryDTO> categoryDTOList;
}
