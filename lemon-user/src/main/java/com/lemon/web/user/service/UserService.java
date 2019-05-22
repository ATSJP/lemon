package com.lemon.web.user.service;

import com.lemon.dto.UserInfoDTO;
import com.lemon.entity.LoginInfoEntity;
import com.lemon.entity.UserInfoEntity;
import com.lemon.repository.LoginInfoRepository;
import com.lemon.repository.UserInfoRepository;
import com.lemon.utils.DateUtils;
import com.lemon.utils.MD5Utils;
import com.lemon.web.constant.base.ConstantApi;
import com.lemon.web.user.request.UserInfoRequest;
import com.lemon.web.user.response.UserInfoResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Optional;

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

	/**
	 * 根据登陆用户名获取用户信息
	 * 
	 * @param loginName 用户名
	 * @return LoginInfoEntity
	 */
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
	@Transactional(rollbackOn = Exception.class)
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

	public void getUserInfo(UserInfoRequest request, UserInfoResponse response) {
		Optional<UserInfoEntity> userInfoEntityOptional = userInfoRepository.findById(request.getUid());
		if (userInfoEntityOptional.isPresent()) {
			UserInfoEntity userInfoEntity = userInfoEntityOptional.get();
			UserInfoDTO userInfoDTO = new UserInfoDTO();
			BeanUtils.copyProperties(userInfoEntity, userInfoDTO);
			response.setUserInfoDTO(userInfoDTO);
		} else {
			response.setCode(ConstantApi.CODE.ILLEGAL_REQUEST.getCode());
			response.setMsg(ConstantApi.CODE.ILLEGAL_REQUEST.getDesc());
		}
	}

	/**
	 * 修改资料
	 * 
	 * @param request req
	 * @param response res
	 */
	public void update(UserInfoRequest request, UserInfoResponse response) {
		UserInfoEntity userInfoEntity = new UserInfoEntity();
		userInfoEntity.setLoginId(request.getUid());
		userInfoEntity.setUserName(request.getUserName());
		userInfoEntity.setEngName(request.getEngName());
		userInfoEntity.setGender(request.getGender());
		userInfoEntity.setBirthday(request.getBirthday());
		userInfoEntity.setIdCard(request.getIdCard());
		userInfoEntity.setQqAccount(request.getQqAccount());
		userInfoEntity.setWeChatAccount(request.getWeChatAccount());
		userInfoEntity.setUpdateId(request.getUid());
		userInfoEntity.setUpdateTime(DateUtils.getCurrentTime());
		userInfoRepository.save(userInfoEntity);
	}

}
