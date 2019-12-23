package com.lemon.relam;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * 登陆和授权
 *
 * @author sjp 2018/12/9
 */
public class LoginRelam extends AuthorizingRealm {

	private static final String MONSTER = "monster";

	/**
	 * 登陆
	 *
	 * @param authenticationToken token
	 * @return info
	 * @throws AuthenticationException exception
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;

		return null;
	}

	/**
	 * 授权
	 *
	 * @param principalCollection p
	 * @return a
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		Set<String> roles = new HashSet<>();
		return new SimpleAuthorizationInfo(roles);
	}

}
