package com.lemon.shiro.token;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

import java.util.Map;

/**
 * 无状态token认证
 *
 * @author sjp
 * @date 2019/4/30
 **/
@Data
public class StatelessToken implements AuthenticationToken {
	private Long			uid;
	private String			token;
	private Map<String, ?>	params;

	public StatelessToken(Long uid, String token, Map<String, ?> params) {
		this.uid = uid;
		this.token = token;
		this.params = params;
	}

	/**
	 * 重写账号区分
	 * 
	 * @return Object
	 */
	@Override
	public Object getPrincipal() {
		return uid;
	}

	/**
	 * 重写凭证获取
	 * @return Object
	 */
	@Override
	public Object getCredentials() {
		return token;
	}
}