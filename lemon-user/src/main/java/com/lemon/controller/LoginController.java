package com.lemon.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lemon.vo.ResultBean;

/**
 * @author shijianpeng 2019/1/10
 */
@Controller
@RequestMapping("/user")
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(LoginController.class);

	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResultBean login(@RequestParam(value = "loginName") String loginName,
			@RequestParam(value = "password") String password) {
		ResultBean resultBean = new ResultBean();
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			resultBean.setMsg("您已登录，无需重复登录！");
		} else {
			UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
			try {
				currentUser.login(token);
			} catch (UnknownAccountException uae) {
				logger.error("do not found userName:{}", token.getPrincipal());
				resultBean.setMsg("用户名或密码不正确！");
			} catch (IncorrectCredentialsException ice) {
				logger.error("userName and password error:{}", token.getPrincipal());
				resultBean.setMsg("用户名或密码不正确！");
			} catch (LockedAccountException lae) {
				logger.error("userName is locked:{}", token.getPrincipal());
				resultBean.setMsg("您的账号被锁定，请联系客服！");
			} catch (AuthenticationException a) {
				logger.error("userName is error:{}", token.getPrincipal());
				resultBean.setMsg("用户名或密码不正确！");
			}
			if (!currentUser.isAuthenticated()) {
				resultBean.setMsg("用户名或密码不正确！");
			}
			Session session = currentUser.getSession();
			session.setAttribute("loginName", loginName);
			if (logger.isDebugEnabled()) {
				logger.info("用户 " + currentUser.getPrincipal() + " 登陆成功！");
				logger.info("是否拥有 admin 角色：" + currentUser.hasRole("admin"));
				logger.info("是否拥有 user:create 权限" + currentUser.isPermitted("user:create"));
			}
		}
		if (StringUtils.isEmpty(resultBean.getMsg())) {
			resultBean.setCode("0");
		} else {
			resultBean.setCode("1");
		}
		return resultBean;
	}

}
