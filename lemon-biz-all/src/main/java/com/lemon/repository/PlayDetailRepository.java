package com.lemon.repository;

import com.lemon.entity.PlayDetailEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author sjp 2019/1/10
 */
@Repository
public interface PlayDetailRepository extends CrudRepository<PlayDetailEntity, Long> {

	/**
	 * 统计每个视频的播放量
	 *
	 * @param sortValue 排序value: 0 升序 1 降序
	 * @return List<Map<String, Object>>
	 */
	@Query(value = "SELECT * FROM (SELECT video_id AS video_id, COUNT(1) AS total_play_num FROM play_detail GROUP BY video_id) play_count ORDER BY total_play_num DESC", nativeQuery = true)
	List<Map<String, Object>> countPlayNumByVideoId(@Param(value = "sortValue") String sortValue);

	/**
	 * 根据视频id，统计每个视频的播放量
	 * 
	 * @param videoId 视频id
	 * @return int 播放量
	 */
	int countAllByVideoId(long videoId);

	/**
	 * 统计当前视频该用户是否已经看过
	 * 
	 * @param createId 创建人id
	 * @param videoId 视频Id
	 * @return int 播放量
	 */
	int countAllByCreateIdAndVideoId(long createId, long videoId);

	/**
	 * 根据IP和创建人Id统计播放次数
	 * 
	 * @param videoId 视频Id
	 * @param ip ip
	 * @param createId 创建人ID
	 * @return int 播放量
	 */
	int countAllByVideoIdAndIpAndCreateId(long videoId, String ip, long createId);
}
