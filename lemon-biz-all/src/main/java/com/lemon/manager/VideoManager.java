package com.lemon.manager;

import com.lemon.entity.PlayDetailEntity;
import com.lemon.repository.PlayDetailRepository;
import com.lemon.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * VideoManager
 *
 * @author sjp
 * @date 2019/12/8
 */
@Service
public class VideoManager {
	@Resource
	private PlayDetailRepository	playDetailRepository;

	/**
	 * 添加视频播放记录
	 *
	 * @param uid 当前用户Id
	 * @param sid 用户所在平台
	 * @param videoId 视频Id
	 * @param ip IP地址
	 */
	public void addVideoPlayRecord(Long uid, String sid, Long videoId, String ip) {
		// 未登陆下，检测当前IP地址是否已经播放过视频
		if (uid == -1L) {
			int count = playDetailRepository.countAllByVideoIdAndIpAndCreateId(videoId, ip, uid);
			if (count > 0) {
				return;
			}
			this.savePlayDetail(uid, sid, videoId, ip);
			return;
		}
		// 已登陆下，检测当前用户是否播放过视频
		int count = playDetailRepository.countAllByCreateIdAndVideoId(uid, videoId);
		if (count > 0) {
			return;
		}
		this.savePlayDetail(uid, sid, videoId, ip);
	}

	/**
	 * 添加视频播放记录
	 *
	 * @param uid 当前用户Id
	 * @param sid 用户所在平台
	 * @param videoId 视频Id
	 * @param ip IP地址
	 */
	private void savePlayDetail(Long uid, String sid, Long videoId, String ip) {
		PlayDetailEntity playDetailEntity = new PlayDetailEntity();
		playDetailEntity.setVideoId(videoId);
		playDetailEntity.setIp(ip);
		playDetailEntity.setSid(sid);
		playDetailEntity.setCreateId(uid);
		playDetailEntity.setCreateTime(DateUtils.getCurrentTime());
		playDetailRepository.save(playDetailEntity);
	}

}
