package com.lemon.repository;

import com.lemon.entity.RemarkEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author sjp 2019/1/10
 */
@Repository
public interface RemarkRepository extends CrudRepository<RemarkEntity, Long> {

	/**
	 * 根据视频id和逻辑删除标识查询评价
	 *
	 * @param videoId 视频id
	 * @param parentId 父类id
	 * @param delFlag 是否删除
	 * @return List<RemarkEntity>
	 */
	List<RemarkEntity> findAllByVideoIdAndParentIdAndDelFlag(long videoId, long parentId, short delFlag);

}
