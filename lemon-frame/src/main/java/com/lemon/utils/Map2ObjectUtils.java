package com.lemon.utils;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * Map2ObjectUtils
 *
 * @author sjp
 * @date 2019/5/25
 */
public class Map2ObjectUtils {

	/**
	 * 将map转化为Bean
	 * 
	 * @param map map
	 * @param beanClass beanClass
	 * @return Object Bean
	 * @throws Exception Exception
	 */
	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
		if (map == null) {
			return null;
		}
		Object obj = beanClass.newInstance();
		BeanUtils.populate(obj, map);
		return obj;
	}

	/**
	 * 将Obeject转为Map
	 * 
	 * @param obj obj
	 * @return map
	 */
	public static Map<?, ?> objectToMap(Object obj) {
		if (obj == null) {
			return null;
		}
		return new BeanMap(obj);
	}
}
