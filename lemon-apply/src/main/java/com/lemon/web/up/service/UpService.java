package com.lemon.web.up.service;

import com.lemon.entity.UpDetailEntity;
import com.lemon.repository.UpDetailRepository;
import com.lemon.tools.RedissonTools;
import com.lemon.utils.DateUtils;
import com.lemon.web.constant.ConstantUp;
import com.lemon.web.constant.base.ConstantApi;
import com.lemon.web.constant.base.ConstantBaseData;
import com.lemon.web.constant.base.ConstantLock;
import com.lemon.web.up.request.UpRequest;
import com.lemon.web.up.response.UpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * UpService
 *
 * @author sjp
 * @date 2019/5/6
 */
@Service
public class UpService {
	private Logger				logger	= LoggerFactory.getLogger(this.getClass());
	@Resource
	private UpDetailRepository	upDetailRepository;
	@Resource
	private RedissonTools		redissonTools;

	/**
	 * 用户点赞视频
	 * 
	 * @param request req
	 * @param response res
	 */
	public void upVideo(UpRequest request, UpResponse response) {
		Long uid = request.getUid();
		Long videoId = request.getVideoId();
		boolean isLock = redissonTools.tryLock(ConstantLock.KEY.UP_LOCK.key + videoId + ConstantBaseData.CN + uid,
				ConstantLock.KEY.UP_LOCK.timeOut, ConstantLock.KEY.UP_LOCK.expires);
		if (isLock) {
			try {
				// 判断该用户是否已经收藏过
				List<UpDetailEntity> upDetailEntityList = upDetailRepository
						.findAllByVideoIdAndCreateIdAndIsDel(videoId, uid, ConstantBaseData.IS_DELETE.FALSE.code);
				if (CollectionUtils.isEmpty(upDetailEntityList)) {
					// 没有记录，则是第一次点赞操作
					UpDetailEntity entity = new UpDetailEntity();
					entity.setVideoId(videoId);
					entity.setCreateId(uid);
					entity.setCreateTime(DateUtils.getCurrentTime());
					entity.setUpdateId(uid);
					entity.setUpdateTime(DateUtils.getCurrentTime());
					entity.setIsDel(ConstantBaseData.IS_DELETE.FALSE.code);
					upDetailRepository.save(entity);
				}
				// 存在记录，则提示重复点赞
				response.setCode(ConstantApi.CODE.FAIL.getCode());
				response.setMsg(ConstantUp.UP_MESSAGE.UP_REPEAT.getDesc());
			} catch (Exception e) {
				logger.error("upVideo error", e);
			} finally {
				redissonTools.unlockNoWait(ConstantLock.KEY.UP_LOCK.key + videoId + ConstantBaseData.CN + uid);
			}
		} else {
			// 获取锁超时
			response.setCode(ConstantApi.CODE.SYSTEM_ERROR.getCode());
			response.setMsg(ConstantApi.CODE.SYSTEM_ERROR.getDesc());
			logger.info("try lock error:upVideo()->videoId:{},uid:{}");
		}
	}

	/**
	 * 用户取消点赞
	 * 
	 * @param request req
	 * @param response res
	 */
	public void cancelUpVideo(UpRequest request, UpResponse response) {
		Long videoId = request.getVideoId();
		Long uid = request.getUid();
		boolean isLock = redissonTools.tryLock(ConstantLock.KEY.UP_LOCK.key + videoId + ConstantBaseData.CN + uid,
				ConstantLock.KEY.UP_LOCK.timeOut, ConstantLock.KEY.UP_LOCK.expires);
		if (isLock) {
			try {
				// 判断该用户是否已经收藏过
				List<UpDetailEntity> upDetailEntityList = upDetailRepository
						.findAllByVideoIdAndCreateIdAndIsDel(videoId, uid, ConstantBaseData.IS_DELETE.FALSE.code);
				if (CollectionUtils.isEmpty(upDetailEntityList)) {
					response.setCode(ConstantApi.CODE.FAIL.getCode());
					response.setMsg(ConstantUp.UP_MESSAGE.CANCEL_REPEAT.getDesc());
					return;
				}
				upDetailEntityList.parallelStream().forEach(entity -> {
					// 存在记录，则置为失效
					entity.setIsDel(ConstantBaseData.IS_DELETE.TRUE.code);
					entity.setUpdateId(uid);
					entity.setUpdateTime(DateUtils.getCurrentTime());
					upDetailRepository.save(entity);
				});
			} catch (Exception e) {
				logger.error("cancelUpVideo error", e);
			} finally {
				redissonTools.unlockNoWait(ConstantLock.KEY.UP_LOCK.key + videoId + ConstantBaseData.CN + uid);
			}
		} else {
			// 获取锁超时
			response.setCode(ConstantApi.CODE.SYSTEM_ERROR.getCode());
			response.setMsg(ConstantApi.CODE.SYSTEM_ERROR.getDesc());
			logger.info("try lock error:cancelUpVideo()->videoId:{},uid:{}");
		}
	}
}
