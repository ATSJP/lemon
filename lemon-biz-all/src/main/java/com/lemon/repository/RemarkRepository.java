package com.lemon.repository;

import com.lemon.entity.RemarkEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
	List<RemarkEntity> findAllByVideoIdAndParentIdAndIsDel(long videoId, long parentId, short delFlag);

    /**
     * 统计每个视频的播放量
     *
     * @param sortValue sortValue
     * @return List<Map<String, Object>>
     */
    @Query(value = "SELECT * FROM (SELECT video_id AS video_id, COUNT(1) AS total_remark_num FROM remark GROUP BY video_id ) remark_count ORDER BY total_remark_num DESC", nativeQuery = true)
    List<Map<String, Object>> countRemarkNumByVideoId(String sortValue);
}
