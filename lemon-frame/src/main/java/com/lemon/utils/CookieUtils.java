package com.lemon.utils;

import org.springframework.util.CollectionUtils;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CookieUtils
 *
 * @author sjp
 * @date 2019/5/3
 */
public class CookieUtils {

	/**
	 * 从cookie中获取名称的值
	 * 
	 * @param cookies cookies
	 * @param name cookie值名称
	 * @return value cookie值
	 */
	public static String getParamFromCookie(Cookie[] cookies, String name) {
		if (cookies == null || cookies.length == 0) {
			return "";
		}
		List<Cookie> cookieList = Arrays.stream(cookies).filter(item -> name.equals(item.getName()))
				.collect(Collectors.toList());
		if (CollectionUtils.isEmpty(cookieList)) {
			return "";
		}
		return cookieList.get(0).getValue();
	}

}
