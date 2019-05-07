package com.lemon.repository;

import com.lemon.entity.CollectionDetailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author sjp 2019/1/10
 */
@Repository
public interface CollectionDetailRepository extends CrudRepository<CollectionDetailEntity, Long> {

	/**
	 * 
	 * @param videoId
	 * @param createId
	 * @param isDel
	 * @return
	 */
	List<CollectionDetailEntity> findAllByVideoIdAndCreateIdAndIsDel(long videoId, long createId, short isDel);

	/**
	 *
	 * @param videoId
	 * @param createId
	 * @param isDel
	 * @return
	 */
	int countAllByVideoIdAndCreateIdAndIsDel(long videoId, long createId, short isDel);

}
