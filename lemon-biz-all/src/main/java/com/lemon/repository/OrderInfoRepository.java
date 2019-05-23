package com.lemon.repository;

import com.lemon.entity.OrderInfoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * @author sjp 2019/1/10
 */
@Repository
public interface OrderInfoRepository extends CrudRepository<OrderInfoEntity, Long> {

	/**
	 * 查询出昨日所有未有支付成功日志的订单记录，限制100条
	 *
	 * @return
	 */
	@Query(value = "SELECT order_id FROM order_info oi "
			+ "WHERE DATE_FORMAT( create_time,'%Y-%m-%d') = DATE_FORMAT(CURDATE()-1,'%Y-%m-%d') "
			+ "AND oi.order_status = 0 "
			+ "AND oi.order_id NOT IN ( SELECT DISTINCT (order_id) FROM pay_log) LIMIT 0,100", nativeQuery = true)
	List<BigInteger> getAllIdByNoPayCallBack();
}
