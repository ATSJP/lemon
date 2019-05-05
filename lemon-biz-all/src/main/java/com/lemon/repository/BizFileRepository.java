package com.lemon.repository;

import com.lemon.entity.BizFileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author sjp 2019/1/10
 */
@Repository
public interface BizFileRepository extends CrudRepository<BizFileEntity, Long> {

	/**
	 * 根据linkType、linkId、isDel获取文件记录
	 * 
	 * @param linkType 关联类型
	 * @param linkId 关联id
	 * @param isDel 0 未删除 1 已删除
	 * @return BizFileEntity
	 */
	List<BizFileEntity> findAllByLinkTypeAndLinkIdAndIsDel(short linkType, long linkId, short isDel);

    /**
     * 根据linkId、isDel获取文件记录
     *
     * @param linkId 关联id
     * @param isDel 0 未删除 1 已删除
     * @return BizFileEntity
     */
    List<BizFileEntity> findAllByLinkIdAndIsDel(long linkId, short isDel);

}
