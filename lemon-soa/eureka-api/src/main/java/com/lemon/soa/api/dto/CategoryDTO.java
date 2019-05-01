package com.lemon.soa.api.dto;

import lombok.Data;

import java.util.List;

/**
 * @author sjp
 * @date 2019/5/1
 **/
@Data
public class CategoryDTO {
	/**
	 * 分类 id
	 */
	private Long				categoryId;
	/**
	 * 分类名称
	 */
	private String				categoryName;
	/**
	 * 父类id
	 */
	private Long				parentId;
	/**
	 * 是否展示
	 */
	private Short				display;
	/**
	 * 子类分类
	 */
	private List<CategoryDTO>	categoryDTOList;
}
