package com.lemon.repository;

import com.lemon.entity.UpDetailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UpDetailRepository
 * 
 * @author sjp
 * @date 2019/1/10
 */
@Repository
public interface UpDetailRepository extends CrudRepository<UpDetailEntity, Long> {

	/**
	 * 根据用户id，获取指定视频id，点赞数list
	 * 
	 * @param videoId 视频id
	 * @param createId 用户id
	 * @param isDel 是否删除
	 * @return List<UpDetailEntity>
	 */
	List<UpDetailEntity> findAllByVideoIdAndCreateIdAndIsDel(long videoId, long createId, short isDel);

	/**
	 * 根据用户id，统计指定视频id，点赞数
	 * 
	 * @param videoId 视频id
	 * @param createId 用户id
	 * @param isDel 是否删除
	 * @return List<UpDetailEntity>
	 */
	int countAllByVideoIdAndCreateIdAndIsDel(long videoId, long createId, short isDel);
}
