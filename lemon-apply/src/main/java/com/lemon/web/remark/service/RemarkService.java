package com.lemon.web.remark.service;

import com.lemon.dto.UserDTO;
import com.lemon.entity.RemarkEntity;
import com.lemon.manager.UserManager;
import com.lemon.repository.RemarkRepository;
import com.lemon.utils.DateUtils;
import com.lemon.web.constant.ConstantApi;
import com.lemon.web.constant.ConstantBaseData;
import com.lemon.web.remark.request.RemarkRequest;
import com.lemon.web.remark.response.RemarkResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * RemarkService
 *
 * @author sjp
 * @date 2019/5/8
 */
@Service
public class RemarkService {

	@Resource
	private RemarkRepository	remarkRepository;
	@Resource
	private UserManager			userManager;

	public void remark(RemarkRequest request, RemarkResponse response) {
		String remarkContext = request.getRemarkContext();
		Long videoId = request.getVideoId();
		Long repeatId = request.getRepeatId();
		Long parentId = request.getParentId();
		Long loginId = request.getUid();
		// 目标回复用户名
		String repeatName = "";

		// check当前登陆用户是否存在
		// TODO 需迁移到拦截器确保loginId正确性
		UserDTO loginUser = userManager.getUser(loginId);
		if (loginUser == null) {
			response.setCode(ConstantApi.CODE.AUTH_EMPTY.getCode());
			response.setMsg(ConstantApi.CODE.AUTH_EMPTY.getDesc());
			return;
		}
		if (repeatId != -1L) {
			// 当前目标回复用户id不是默认为-1，则需要check目标回复用户是否存在
			UserDTO repeatUser = userManager.getUser(repeatId);
			if (repeatUser == null) {
				response.setCode(ConstantApi.CODE.ILLEGAL_REQUEST.getCode());
				response.setMsg(ConstantApi.CODE.ILLEGAL_REQUEST.getDesc());
				return;
			} else {
				repeatName = repeatUser.getLoginName();
			}
		}
		if (parentId != -1L) {
			// 当前父类id不是默认为-1，则需要check父类是否存在
			boolean isExistParentId = remarkRepository.existsById(parentId);
			if (!isExistParentId) {
				response.setCode(ConstantApi.CODE.ILLEGAL_REQUEST.getCode());
				response.setMsg(ConstantApi.CODE.ILLEGAL_REQUEST.getDesc());
				return;
			}
		}

		RemarkEntity entity = new RemarkEntity();
		entity.setRemarkContext(remarkContext);
		entity.setVideoId(videoId);
		entity.setRepeatId(repeatId);
		entity.setRepeatName(repeatName);
		entity.setLoginId(loginId);
		entity.setLoginName(loginUser.getLoginName());
		entity.setParentId(parentId);
		entity.setIsDel(ConstantBaseData.IS_DELETE.FALSE.code);
		entity.setCreateTime(DateUtils.getCurrentTime());
		entity.setCreateId(loginId);
		entity.setUpdateId(loginId);
		entity.setUpdateTime(DateUtils.getCurrentTime());
		remarkRepository.save(entity);
		response.setRemarkId(entity.getRemarkId());
	}

}
