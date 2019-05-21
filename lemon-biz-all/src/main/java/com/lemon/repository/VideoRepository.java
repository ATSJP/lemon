package com.lemon.repository;

import com.lemon.entity.VideoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author sjp 2019/1/10
 */
@Repository
public interface VideoRepository extends CrudRepository<VideoEntity, Long> {

	/**
	 * 根据分类id查询视频list
	 * 
	 * @param categoryId 分类id
	 * @return List<VideoEntity>
	 */
	List<VideoEntity> findAllByCategoryId(long categoryId);

	/**
	 * 根据用户id获取视频list
	 *
	 * @param userId 用户id
	 * @return
	 */
	List<VideoEntity> findAllByUserId(long userId);
}
