package com.lemon.web.collect.service;

import com.lemon.entity.CollectionDetailEntity;
import com.lemon.repository.CollectionDetailRepository;
import com.lemon.tools.RedissonTools;
import com.lemon.utils.DateUtils;
import com.lemon.web.collect.request.CollectRequest;
import com.lemon.web.collect.response.CollectResponse;
import com.lemon.web.constant.ConstantCollect;
import com.lemon.web.constant.base.ConstantApi;
import com.lemon.web.constant.base.ConstantBaseData;
import com.lemon.web.constant.base.ConstantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * CollectService
 *
 * @author sjp
 * @date 2019/5/6
 */
@Service
public class CollectService {
	private Logger						logger	= LoggerFactory.getLogger(this.getClass());
	@Resource
	private CollectionDetailRepository	collectionDetailRepository;
	@Resource
	private RedissonTools				redissonTools;

	/**
	 * 用户收藏视频
	 *
	 * @param request req
	 * @param response res
	 */
	public void collect(CollectRequest request, CollectResponse response) {
		Long videoId = request.getVideoId();
		Long uid = request.getUid();
		if (redissonTools.tryLock(ConstantLock.KEY.COLLECT_LOCK.key + videoId + ConstantBaseData.CN + uid,
				ConstantLock.KEY.COLLECT_LOCK.timeOut, ConstantLock.KEY.COLLECT_LOCK.expires)) {
			this.addNewCollect(response, videoId, uid);
			redissonTools.unlockNoWait(ConstantLock.KEY.COLLECT_LOCK.key + videoId + ConstantBaseData.CN + uid);
		} else {
			response.setCode(ConstantApi.CODE.SYSTEM_ERROR.getCode());
			response.setMsg(ConstantApi.CODE.SYSTEM_ERROR.getDesc());
			logger.info("try lock error:collect()->videoId:{},uid:{}");
		}
	}

	/**
	 * 用户取消收藏视频
	 *
	 * @param request req
	 * @param response res
	 */
	public void collectCancel(CollectRequest request, CollectResponse response) {
		Long videoId = request.getVideoId();
		Long uid = request.getUid();
		if (redissonTools.tryLock(ConstantLock.KEY.COLLECT_LOCK.key + videoId + ConstantBaseData.CN + uid,
				ConstantLock.KEY.COLLECT_LOCK.timeOut, ConstantLock.KEY.COLLECT_LOCK.expires)) {
			this.deleteCollect(response, videoId, uid);
		} else {
			response.setCode(ConstantApi.CODE.SYSTEM_ERROR.getCode());
			response.setMsg(ConstantApi.CODE.SYSTEM_ERROR.getDesc());
			logger.info("try lock error:collectCancel()->videoId:{},uid:{}");
		}
	}

	private void addNewCollect(CollectResponse response, Long videoId, Long uid) {
		// 判断用户是否已经收藏过
		List<CollectionDetailEntity> collectionDetailEntityList = collectionDetailRepository
				.findAllByVideoIdAndCreateIdAndIsDel(videoId, uid, ConstantBaseData.IS_DELETE.FALSE.code);
		if (CollectionUtils.isEmpty(collectionDetailEntityList)) {
			// 没有记录，则默认是第一次进行收藏操作
			CollectionDetailEntity entity = new CollectionDetailEntity();
			entity.setVideoId(videoId);
			entity.setCreateId(uid);
			entity.setCreateTime(DateUtils.getCurrentTime());
			entity.setUpdateId(uid);
			entity.setUpdateTime(DateUtils.getCurrentTime());
			entity.setIsDel(ConstantBaseData.IS_DELETE.FALSE.code);
			collectionDetailRepository.save(entity);
			response.setCode(ConstantApi.CODE.SUCCESS.getCode());
			response.setMsg(ConstantApi.CODE.SUCCESS.getDesc());
			return;
		}
		if (collectionDetailEntityList.size() == 1) {
			// 存在记录，则提示已经收藏过
			response.setCode(ConstantApi.CODE.FAIL.getCode());
			response.setMsg(ConstantCollect.COLLECT_MESSAGE.COLLECT_REPEAT.getDesc());
			return;
		}
		// 存在2条同样的收藏为异常情况
		response.setCode(ConstantApi.CODE.SYSTEM_ERROR.getCode());
		response.setMsg(ConstantApi.CODE.SYSTEM_ERROR.getDesc());
		logger.info("there are more collects for a video->videoId:{},uid:{}");
	}

	private void deleteCollect(CollectResponse response, Long videoId, Long uid) {
		// 判断用户是否已经收藏过
		List<CollectionDetailEntity> collectionDetailEntityList = collectionDetailRepository
				.findAllByVideoIdAndCreateIdAndIsDel(videoId, uid, ConstantBaseData.IS_DELETE.FALSE.code);
		if (CollectionUtils.isEmpty(collectionDetailEntityList)) {
			// 没有记录，则已经成功取消，提示重复取消
			response.setCode(ConstantApi.CODE.FAIL.getCode());
			response.setMsg(ConstantCollect.COLLECT_MESSAGE.COLLECT_CANCEL_REPEAT.getDesc());
			return;
		}
		if (collectionDetailEntityList.size() == 1) {
			// 存在记录，则置为失效
			CollectionDetailEntity entity = collectionDetailEntityList.get(0);
			entity.setIsDel(ConstantBaseData.IS_DELETE.TRUE.code);
			collectionDetailRepository.save(entity);
			response.setCode(ConstantApi.CODE.SUCCESS.getCode());
			response.setMsg(ConstantApi.CODE.SUCCESS.getDesc());
			return;
		}
		// 存在2条同样的收藏为异常情况
		response.setCode(ConstantApi.CODE.SYSTEM_ERROR.getCode());
		response.setMsg(ConstantApi.CODE.SYSTEM_ERROR.getDesc());
		logger.info("there are more collects for a video->videoId:{},uid:{}");
	}
}
