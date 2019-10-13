package com.lemon.utils;

import com.google.common.collect.Iterables;

import java.util.Collections;
import java.util.List;

/**
 * PageUtils
 *
 * @author sjp
 * @date 2019/5/21
 */
public class PageUtils {

	/**
	 * 根据页数分页
	 *
	 * @param list 所有列表
	 * @param pageIndex 第几页
	 * @param pageSize 每页大小
	 * @return List<VideoDTO> 第几页数据
	 */
    @SuppressWarnings("unchecked")
	public static List getPageList(List list, int pageIndex, int pageSize) {
		if (list.size() > pageSize) {
			// 分页
			Iterable<List> pagesIterable = Iterables.partition(list, pageSize);
			if (Iterables.size(pagesIterable) < pageIndex) {
				// 超出分页范围
				return Collections.emptyList();
			}
			// 取出第pageIndex页
			int index = 1;
			for (List pageItemList : pagesIterable) {
				if (index == pageIndex) {
					return pageItemList;
				}
				index++;
			}
		}
		return list;
	}

}
