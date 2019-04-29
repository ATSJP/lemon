package com.lemon.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lemon.entity.UserInfoEntity;

/**
 * @author sjp 2019/1/10
 */
@Repository
public interface UserInfoRepository extends CrudRepository<UserInfoEntity, Long> {

}
