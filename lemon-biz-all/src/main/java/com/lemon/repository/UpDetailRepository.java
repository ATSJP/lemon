package com.lemon.repository;

import com.lemon.entity.UpDetailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author sjp 2019/1/10
 */
@Repository
public interface UpDetailRepository extends CrudRepository<UpDetailEntity, Long> {

	/**
	 * 
	 * @param videoId
	 * @param createId
	 * @return List<UpDetailEntity>
	 */
	List<UpDetailEntity> findAllByVideoIdAndCreateId(long videoId, long createId);

	/**
	 *
	 * @param videoId
	 * @param createId
	 * @return List<UpDetailEntity>
	 */
	int countAllByVideoIdAndCreateId(long videoId, long createId);
}
