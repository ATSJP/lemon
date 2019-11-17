package com.lemon.utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * DateUtils
 *
 * @author sjp
 * @date 2019/5/3
 */
public class DateUtils {
	private static final String COMMON_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 获取当前时间
	 * 
	 * @return Timestamp
	 */
	public static Timestamp getCurrentTime() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 格式化时间戳
	 * 
	 * @param epochMilli 时间戳
	 * @return String 时间（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatTimestamp(long epochMilli) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(COMMON_FORMAT_PATTERN)
				.withZone(ZoneId.systemDefault());
		return formatter.format(Instant.ofEpochMilli(epochMilli));
	}
}
