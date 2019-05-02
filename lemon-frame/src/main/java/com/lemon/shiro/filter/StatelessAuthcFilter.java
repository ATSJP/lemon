package com.lemon.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import com.lemon.shiro.token.StatelessToken;
import com.lemon.web.base.response.BaseResponse;
import com.lemon.web.constant.ConstantApiMsg;
import com.lemon.web.constant.ConstantBizFile;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// 用户token
		String token = request.getParameter(ConstantBizFile.TOKEN);
		if (StringUtils.isEmpty(token) || StringUtils.isEmpty(request.getParameter(ConstantBizFile.UID))) {
			onLoginFail(response);
			return false;
		}
		// 用户唯一id
		Long uid = Long.parseLong(request.getParameter(ConstantBizFile.UID));
		// 用户所在平台
		String sid = request.getParameter(ConstantBizFile.SID);
		if (StringUtils.isEmpty(request.getParameter(ConstantBizFile.SID))) {
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
			logger.error("getSubJect error->uid:{},sid:{},token:{},e:{}", uid, sid, token, e);
			// 登录失败
			onLoginFail(response);
			return false;
		}
		return true;
	}

	/**
	 * 登录失败时默认返回401状态码
	 */
	private void onLoginFail(ServletResponse response) throws IOException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setStatus(HttpServletResponse.SC_OK);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setCode(ConstantApiMsg.CODE.TOKEN_INVALID.getCode());
		baseResponse.setMsg(ConstantApiMsg.CODE.TOKEN_INVALID.getDesc());
		httpResponse.setContentType("application/json;charset=UTF-8");
		httpResponse.getWriter().write(JSONObject.toJSONString(baseResponse));
	}

}