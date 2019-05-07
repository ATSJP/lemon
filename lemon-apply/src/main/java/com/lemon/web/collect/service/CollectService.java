package com.lemon.web.collect.service;

import com.lemon.entity.CollectionDetailEntity;
import com.lemon.repository.CollectionDetailRepository;
import com.lemon.utils.DateUtils;
import com.lemon.web.collect.request.CollectRequest;
import com.lemon.web.collect.response.CollectResponse;
import com.lemon.web.constant.ConstantApi;
import com.lemon.web.constant.ConstantBaseData;
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

	public void collect(CollectRequest request, CollectResponse response) {
		Long videoId = request.getVideoId();
		Long uid = request.getUid();
		// TODO 此处代码太糙，为了赶毕设 后续修改
		List<CollectionDetailEntity> collectionDetailEntityList = collectionDetailRepository
				.findAllByVideoIdAndCreateIdAndIsDel(videoId, uid, ConstantBaseData.IS_DELETE.FALSE.code);
		if (CollectionUtils.isEmpty(collectionDetailEntityList)) {
			// 没有记录，则默认是收藏操作
			CollectionDetailEntity entity = new CollectionDetailEntity();
			entity.setCreateId(uid);
			entity.setCreateTime(DateUtils.getCurrentTime());
			entity.setUpdateId(uid);
			entity.setUpdateTime(DateUtils.getCurrentTime());
			entity.setVideoId(videoId);
			entity.setIsDel(ConstantBaseData.IS_DELETE.FALSE.code);
			collectionDetailRepository.save(entity);
			response.setCode(ConstantApi.CODE.SUCCESS.getCode());
			response.setMsg(ConstantApi.CODE.SUCCESS.getDesc());
			return;
		}
		if (collectionDetailEntityList.size() == 1) {
			// 存在记录，则是删除收藏操作
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
