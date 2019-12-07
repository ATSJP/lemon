package com.lemon.tools;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

/**
 * Token生成器
 * 
 * @author sjp
 * @date 2019/2/25
 **/
public class TokenGenerate {

	/**
	 * 统一加密Key
	 */
	private static final String KEY = "LEMON_API";

	/**
	 * 生成token
     *
	 * @param uid 用户的id
	 * @param sid 用户所在平台
	 * @return String token
	 */
	public static String getToken(Long uid, String sid) {
		String sb = uid + sid + UUID.randomUUID() + KEY;
		return DigestUtils.md5Hex(sb).toUpperCase();
	}

}
