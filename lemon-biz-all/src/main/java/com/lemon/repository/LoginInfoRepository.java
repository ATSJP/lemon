package com.lemon.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lemon.entity.LoginInfoEntity;

/**
 * @author shijianpeng 2019/1/10
 */
@Repository
public interface LoginInfoRepository extends CrudRepository<LoginInfoEntity, Long> {

	/**
	 * 根据用户名获取登录信息
	 * 
	 * @param loginName 用户名
	 * @return 登录信息
	 */
	LoginInfoEntity getByLoginName(String loginName);
}
