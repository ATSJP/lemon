package com.lemon.tools;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Token生成器
 * @author sjp
 * @date 2019/2/25
 **/
public class TokenGenerate {

	private static String KEY = "LEMON_API";

	public static String getToken(String uid, String sid, long ts, long loginId) {
		StringBuffer sb = new StringBuffer();
		sb.append(uid);
		sb.append(sid);
		sb.append(ts);
		sb.append(loginId);
		sb.append(KEY);
		return DigestUtils.md5Hex(sb.toString()).toUpperCase();
	}

}
