package com.lemon.web.user.rest;

import com.lemon.entity.LoginInfoEntity;
import com.lemon.tools.RedissonTools;
import com.lemon.tools.TokenGenerate;
import com.lemon.web.constant.ConstantApiMsg;
import com.lemon.web.constant.ConstantCache;
import com.lemon.web.user.request.LoginRequest;
import com.lemon.web.user.response.LoginResponse;
import com.lemon.web.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author sjp 2019/1/10
 */
@Controller
@RequestMapping("/user")
public class LoginController {

	private Logger			logger	= LoggerFactory.getLogger(LoginController.class);

	@Resource
	private UserService		userService;
	@Resource
	private RedissonTools	redissonTools;

	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public LoginResponse login(@Valid LoginRequest request) {
		LoginResponse response = new LoginResponse();
		String loginName = request.getLoginName();
		String password = request.getPassword();
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			response.setMsg("您已登录，无需重复登录！");
			response.setCode(ConstantApiMsg.CODE.FAIL.getCode());
			return response;
		} else {
			UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginName, password);
			try {
				currentUser.login(usernamePasswordToken);
			} catch (UnknownAccountException uae) {
				logger.error("do not found userName:{}", usernamePasswordToken.getPrincipal());
				response.setMsg("用户名或密码不正确！");
				response.setCode(ConstantApiMsg.CODE.FAIL.getCode());
				return response;
			} catch (IncorrectCredentialsException ice) {
				logger.error("userName and password error:{}", usernamePasswordToken.getPrincipal());
				response.setMsg("用户名或密码不正确！");
				response.setCode(ConstantApiMsg.CODE.FAIL.getCode());
				return response;
			} catch (LockedAccountException lae) {
				logger.error("userName is locked:{}", usernamePasswordToken.getPrincipal());
				response.setMsg("您的账号被锁定，请联系客服！");
				response.setCode(ConstantApiMsg.CODE.FAIL.getCode());
				return response;
			} catch (AuthenticationException a) {
				logger.error("userName is error:{}", usernamePasswordToken.getPrincipal());
				response.setMsg("用户名或密码不正确！");
				response.setCode(ConstantApiMsg.CODE.FAIL.getCode());
				return response;
			}
			if (!currentUser.isAuthenticated()) {
				response.setMsg("用户名或密码不正确！");
				response.setCode(ConstantApiMsg.CODE.FAIL.getCode());
				return response;
			}
		}
		LoginInfoEntity loginInfoEntity = userService.getByLoginName(loginName);
		String token = TokenGenerate.getToken(loginInfoEntity.getLoginId(), request.getSid());
		redissonTools.set(ConstantCache.KEY.LOGIN_TOKEN.key + loginInfoEntity.getLoginId(), token,
				ConstantCache.KEY.LOGIN_TOKEN.timeOut);
		response.setCode(ConstantApiMsg.CODE.SUCCESS.getCode());
		response.setToken(token);
		response.setUid(loginInfoEntity.getLoginId());
		if (logger.isDebugEnabled()) {
			logger.info("用户 " + currentUser.getPrincipal() + " 登陆成功！");
			logger.info("是否拥有 admin 角色：" + currentUser.hasRole("admin"));
			logger.info("是否拥有 user:create 权限" + currentUser.isPermitted("user:create"));
		}
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "/logout", method = RequestMethod.DELETE)
	public LoginResponse logout(LoginRequest request) {
		LoginResponse response = new LoginResponse();
		redissonTools.delete(ConstantCache.KEY.LOGIN_TOKEN.key + request.getUid());
		response.setCode(ConstantApiMsg.CODE.SUCCESS.getCode());
		response.setUid(request.getUid());
		if (logger.isDebugEnabled()) {
			logger.info("用户 " + request.getUid() + " 退出成功！");
		}
		return response;
	}
}
