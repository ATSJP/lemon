package com.lemon.web.user.service;

import com.lemon.entity.LoginInfoEntity;
import com.lemon.entity.UserInfoEntity;
import com.lemon.repository.LoginInfoRepository;
import com.lemon.repository.UserInfoRepository;
import com.lemon.utils.DateUtils;
import com.lemon.utils.MD5Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sjp
 * @date 2019/2/25
 **/
@Service
public class UserService {

	@Resource
	private LoginInfoRepository	loginInfoRepository;
	@Resource
	private UserInfoRepository	userInfoRepository;
	@Resource
	private MD5Utils			md5Utils;

	public LoginInfoEntity getByLoginName(String loginName) {
		return loginInfoRepository.getByLoginName(loginName);
	}

	/**
	 * 注册用户
	 * 
	 * @param loginName 登陆名
	 * @param password 密码
	 * @param userName 真实姓名
	 * @return LoginInfoEntity
	 */
//    @Transactional(rollbackOn = Exception.class)
	public LoginInfoEntity register(String loginName, String password, String userName) {
		LoginInfoEntity loginInfoEntity = new LoginInfoEntity();
		loginInfoEntity.setLoginName(loginName);
		loginInfoEntity.setLoginPwd(md5Utils.md5(password, loginName));
		loginInfoEntity.setCreateId(-1);
		loginInfoEntity.setCreateTime(DateUtils.getCurrentTime());
		loginInfoEntity.setUpdateId(-1);
		loginInfoEntity.setUpdateTime(DateUtils.getCurrentTime());
		loginInfoRepository.save(loginInfoEntity);

		UserInfoEntity userInfoEntity = new UserInfoEntity();
		userInfoEntity.setLoginId(loginInfoEntity.getLoginId());
		userInfoEntity.setUserName(userName);
		userInfoEntity.setCreateId(loginInfoEntity.getLoginId());
		userInfoEntity.setCreateTime(DateUtils.getCurrentTime());
		userInfoEntity.setUpdateId(loginInfoEntity.getLoginId());
		userInfoEntity.setUpdateTime(DateUtils.getCurrentTime());
		userInfoRepository.save(userInfoEntity);

		return loginInfoEntity;
	}
}
