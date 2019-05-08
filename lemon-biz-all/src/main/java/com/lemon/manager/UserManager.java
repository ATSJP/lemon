package com.lemon.manager;

import com.lemon.entity.LoginInfoEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.lemon.dto.UserDTO;
import com.lemon.repository.LoginInfoRepository;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * UserManager
 *
 * @author sjp
 * @date 2019/5/8
 */
@Service
public class UserManager {

	@Resource
	private LoginInfoRepository loginInfoRepository;

	/**
	 * 根据loginId获取用户的信息
	 *
	 * @param loginId 用户id
	 * @return UserDTO 用户的信息
	 */
	public UserDTO getUser(Long loginId) {
		Optional<LoginInfoEntity> loginInfoEntityOptional = loginInfoRepository.findById(loginId);
		if (loginInfoEntityOptional.isPresent()) {
			UserDTO userDTO = new UserDTO();
			LoginInfoEntity loginInfoEntity = loginInfoEntityOptional.get();
			BeanUtils.copyProperties(loginInfoEntity, userDTO);
			return userDTO;
		}
		return null;
	}
}
