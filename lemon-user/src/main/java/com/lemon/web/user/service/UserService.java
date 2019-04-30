package com.lemon.web.user.service;

import com.lemon.entity.LoginInfoEntity;
import com.lemon.repository.LoginInfoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sjp
 * @date 2019/2/25
 **/
@Service
public class UserService {

	@Resource
	private LoginInfoRepository loginInfoRepository;

	public LoginInfoEntity getByLoginName(String loginName) {
		return loginInfoRepository.getByLoginName(loginName);
	}
}
