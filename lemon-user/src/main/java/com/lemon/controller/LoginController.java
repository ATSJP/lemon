package com.lemon.controller;

import com.lemon.entity.LoginInfoEntity;
import com.lemon.service.UserService;
import com.lemon.tools.TokenGenerate;
import com.lemon.vo.ResultBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author sjp 2019/1/10
 */
@Controller
@RequestMapping("/user")
public class LoginController {

	private Logger		logger	= LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService	userService;

	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResultBean login(@RequestParam(value = "uid") String uid, @RequestParam(value = "sid") String sid,
			@RequestParam(value = "loginName") String loginName, @RequestParam(value = "password") String password) {
		ResultBean resultBean = new ResultBean();
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			resultBean.setMsg("您已登录，无需重复登录！");
		} else {
			UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginName, password);
			try {
				currentUser.login(usernamePasswordToken);
			} catch (UnknownAccountException uae) {
				logger.error("do not found userName:{}", usernamePasswordToken.getPrincipal());
				resultBean.setMsg("用户名或密码不正确！");
			} catch (IncorrectCredentialsException ice) {
				logger.error("userName and password error:{}", usernamePasswordToken.getPrincipal());
				resultBean.setMsg("用户名或密码不正确！");
			} catch (LockedAccountException lae) {
				logger.error("userName is locked:{}", usernamePasswordToken.getPrincipal());
				resultBean.setMsg("您的账号被锁定，请联系客服！");
			} catch (AuthenticationException a) {
				logger.error("userName is error:{}", usernamePasswordToken.getPrincipal());
				resultBean.setMsg("用户名或密码不正确！");
			}
			if (!currentUser.isAuthenticated()) {
				resultBean.setMsg("用户名或密码不正确！");
			}
			Session session = currentUser.getSession();
			session.setAttribute("loginName", loginName);

			LoginInfoEntity loginInfoEntity = userService.getByLoginName(loginName);
			String token = TokenGenerate.getToken(uid, sid, System.currentTimeMillis(), loginInfoEntity.getLoginId());
			resultBean.setData(token);
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
