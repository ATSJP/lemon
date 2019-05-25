package com.lemon.utils;

import java.util.Map;

/**
 * Map2ObjectUtils
 *
 * @author sjp
 * @date 2019/5/25
 */
public class Map2ObjectUtils {

	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
		if (map == null) {
			return null;
		}
		Object obj = beanClass.newInstance();
		org.apache.commons.beanutils.BeanUtils.populate(obj, map);
		return obj;
	}

	public static Map<?, ?> objectToMap(Object obj) {
		if (obj == null) {
			return null;
		}
		return new org.apache.commons.beanutils.BeanMap(obj);
	}
}
