package com.lemon.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * MD5Utils
 *
 * @author sjp
 * @date 2019/5/3
 */
public class MD5Utils {

	/**
	 * 加密次数
	 */
	private Integer	hashIterations;
	/**
	 * 加密方式
	 */
	private String	algorithmName;

	public MD5Utils(Integer hashIterations, String algorithmName) {
		this.hashIterations = hashIterations;
		this.algorithmName = algorithmName;
	}

	public String md5(String key, String salt) {
		Object obj = new SimpleHash(algorithmName, key, ByteSource.Util.bytes(salt), hashIterations);
		return obj.toString();
	}

}
