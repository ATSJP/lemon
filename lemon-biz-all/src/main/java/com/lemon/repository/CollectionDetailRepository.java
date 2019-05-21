package com.lemon.repository;

import com.lemon.entity.CollectionDetailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CollectionDetailRepository
 * 
 * @author sjp 2019/1/10
 */
@Repository
public interface CollectionDetailRepository extends CrudRepository<CollectionDetailEntity, Long> {

	/**
	 * 获取当前指定视频，该用户收藏list
	 *
	 * @param videoId 视频id
	 * @param createId 创建人id
	 * @param isDel 是否删除
	 * @return List<CollectionDetailEntity>
	 */
	List<CollectionDetailEntity> findAllByVideoIdAndCreateIdAndIsDel(long videoId, long createId, short isDel);

	/**
	 * 根据创建人获取收藏list
	 * 
	 * @param createId 创建人id
	 * @param isDel 是否删除
	 * @return List<CollectionDetailEntity>
	 */
	List<CollectionDetailEntity> findAllByCreateIdAndIsDel(long createId, short isDel);

	/**
	 * 统计当前指定视频，该用户收藏量
	 * 
	 * @param videoId 视频id
	 * @param createId 创建人id
	 * @param isDel 是否删除
	 * @return int
	 */
	int countAllByVideoIdAndCreateIdAndIsDel(long videoId, long createId, short isDel);

}
