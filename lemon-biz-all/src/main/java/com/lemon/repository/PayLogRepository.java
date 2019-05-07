package com.lemon.repository;

import com.lemon.entity.PayLogEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sjp 2019/1/10
 */
@Repository
public interface PayLogRepository extends CrudRepository<PayLogEntity, Long> {

}
