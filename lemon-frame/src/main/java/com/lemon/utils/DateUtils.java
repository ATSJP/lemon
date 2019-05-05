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

	public static Timestamp getCurrentTime() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static String formatTimestamp(long epochMilli) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
				.withZone(ZoneId.systemDefault());
		return formatter.format(Instant.ofEpochMilli(epochMilli));
	}
}
