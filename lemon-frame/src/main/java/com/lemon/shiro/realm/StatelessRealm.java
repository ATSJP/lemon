package com.lemon.shiro.realm;

import com.lemon.shiro.token.StatelessToken;
import com.lemon.tools.RedissonTools;
import com.lemon.web.constant.ConstantCache;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * 无状态realm
 * 
 * @author sjp
 * @date 2019/4/30
 **/
public class StatelessRealm extends AuthorizingRealm {

	@Resource
	private RedissonTools redissonTools;

	@Override
	public boolean supports(AuthenticationToken token) {
		// 仅支持StatelessToken类型的Token
		return token instanceof StatelessToken;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Set<String> roles = new HashSet<>();
		// 获取权限

		return new SimpleAuthorizationInfo(roles);
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		StatelessToken statelessToken = (StatelessToken) authenticationToken;
		Long uid = statelessToken.getUid();
		String token = redissonTools.get(ConstantCache.KEY.LOGIN_TOKEN.key + uid);
		token = StringUtils.isEmpty(token) ? "" : token;
		return new SimpleAuthenticationInfo(uid, token, getName());
	}

}