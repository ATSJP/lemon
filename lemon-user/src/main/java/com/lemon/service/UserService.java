package com.lemon.service;

import com.lemon.entity.LoginInfoEntity;
import com.lemon.repository.LoginInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sjp
 * @date 2019/2/25
 **/
@Service
public class UserService {

	@Autowired
	private LoginInfoRepository loginInfoRepository;

	public LoginInfoEntity getByLoginName(String loginName) {
		return loginInfoRepository.getByLoginName(loginName);
	}
}
