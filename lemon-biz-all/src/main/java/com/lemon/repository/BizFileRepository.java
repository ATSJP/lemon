package com.lemon.repository;

import com.lemon.entity.BizFileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sjp 2019/1/10
 */
@Repository
public interface BizFileRepository extends CrudRepository<BizFileEntity, Long> {

	/**
	 * 根据linkType和linkId获取文件记录
	 * @param linkType 关联类型
	 * @param linkId 关联id
	 * @return BizFileEntity
	 */
	BizFileEntity findAllByLinkTypeAndLinkIdAndIsDel(long linkType, long linkId, short isDel);
}
