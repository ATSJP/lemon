package com.lemon.utils;

import org.springframework.util.StringUtils;

/**
 * FileUtils
 *
 * @author sjp
 * @date 2019/5/19
 */
public class FileUtils {

	/**
	 * 获取文件名的后缀
	 * 
	 * @param fileName 文件名
	 * @return String 后缀
	 */
	public static String getFileSuffix(String fileName) {
		if (StringUtils.isEmpty(fileName)) {
			return "";
		}
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}
}
