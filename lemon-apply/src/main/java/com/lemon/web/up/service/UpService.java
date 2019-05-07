package com.lemon.web.up.service;

import com.lemon.entity.UpDetailEntity;
import com.lemon.repository.UpDetailRepository;
import com.lemon.utils.DateUtils;
import com.lemon.web.constant.ConstantApi;
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

	/// @Transactional(rollbackOn = Exception.class)

	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void add(UpRequest request, UpResponse response) {
		Long videoId = request.getVideoId();
		Long uid = request.getUid();
		// TODO 此处代码太糙，为了赶毕设 后续修改
		List<UpDetailEntity> upDetailEntityList = upDetailRepository.findAllByVideoIdAndCreateId(videoId, uid);
		if (CollectionUtils.isEmpty(upDetailEntityList)) {
			// 没有记录，则默认是点赞操作
			UpDetailEntity entity = new UpDetailEntity();
			entity.setVideoId(videoId);
			entity.setCreateId(uid);
			entity.setCreateTime(DateUtils.getCurrentTime());
			entity.setUpdateId(uid);
			entity.setUpdateTime(DateUtils.getCurrentTime());
			upDetailRepository.save(entity);
			response.setCode(ConstantApi.CODE.SUCCESS.getCode());
			response.setMsg(ConstantApi.CODE.SUCCESS.getDesc());
			return;
		}
		if (upDetailEntityList.size() == 1) {
			// 存在记录，则是删除收藏操作
			UpDetailEntity entity = upDetailEntityList.get(0);
			upDetailRepository.delete(entity);
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
