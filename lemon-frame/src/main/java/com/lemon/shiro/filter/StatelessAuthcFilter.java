package com.lemon.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import com.lemon.shiro.token.StatelessToken;
import com.lemon.utils.CookieUtils;
import com.lemon.web.base.response.BaseResponse;
import com.lemon.web.constant.base.ConstantApi;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 无状态拦截器
 * 
 * @author sjp
 * @date 2019/4/30
 **/
public class StatelessAuthcFilter extends AccessControlFilter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 判断请求是否需要被拦截，拦截后执行onAccessDenied方法
     *
     * @param request req
     * @param response res
     * @param mappedValue map
     * @return true 不拦截 false 拦截
     */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		Cookie[] cookies = ((HttpServletRequest) request).getCookies();
		// 用户唯一id
		String uidStr = CookieUtils.getParamFromCookie(cookies, ConstantApi.UID);
		if (StringUtils.isEmpty(uidStr)) {
			onLoginFail(response);
			return false;
		}
		Long uid = Long.parseLong(uidStr);
		// 用户token
		String token = CookieUtils.getParamFromCookie(cookies, ConstantApi.TOKEN);
		if (StringUtils.isEmpty(token)) {
			onLoginFail(response);
			return false;
		}
		// 用户所在平台
		String sid = CookieUtils.getParamFromCookie(cookies, ConstantApi.SID);
		if (StringUtils.isEmpty(sid)) {
			onLoginFail(response);
			return false;
		}
		// 客户端请求的参数列表
		Map<String, String[]> params = new HashMap<>(request.getParameterMap());
		StatelessToken statelessToken = new StatelessToken(uid, token, params);
		try {
			// 委托给Realm进行登录
			getSubject(request, response).login(statelessToken);
		} catch (Exception e) {
			logger.info("auth error->uid:{},sid:{},token:{},e:{}", uid, sid, token, e.getMessage());
			// 登录失败
			onLoginFail(response);
			return false;
		}
		return true;
	}

	/**
	 * 登录失败时默认返回401状态码
	 * 
	 * @param response response
	 * @throws IOException IOException
	 */
	private void onLoginFail(ServletResponse response) throws IOException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setStatus(HttpServletResponse.SC_OK);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setCode(ConstantApi.CODE.TOKEN_INVALID.getCode());
		baseResponse.setMsg(ConstantApi.CODE.TOKEN_INVALID.getDesc());
		httpResponse.setContentType("application/json;charset=UTF-8");
		httpResponse.getWriter().write(JSONObject.toJSONString(baseResponse));
	}

}