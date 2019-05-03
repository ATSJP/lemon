package com.lemon.utils;

import java.sql.Timestamp;

/**
 * DateUtils
 *
 * @author sjp
 * @date 2019/5/3
 */
public class DateUtils {

	public static Timestamp getCurrentTime() {
		return new Timestamp(System.currentTimeMillis());
	}
}
