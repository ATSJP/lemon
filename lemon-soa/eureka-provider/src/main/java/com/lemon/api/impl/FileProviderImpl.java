package com.lemon.api.impl;

import com.lemon.entity.BizFileEntity;
import com.lemon.repository.BizFileRepository;
import com.lemon.soa.api.dto.BizFileDTO;
import com.lemon.soa.api.provider.FileProvider;
import com.lemon.web.constant.base.ConstantBaseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * FileProviderImpl
 *
 * @author sjp
 * @date 2019/5/2
 */
@Service
public class FileProviderImpl implements FileProvider {

	private Logger				logger	= LoggerFactory.getLogger(this.getClass());

	@Resource
	private BizFileRepository	bizFileRepository;

	@Override
	public BizFileDTO getEffectFile(short linkType, long linkId) {
		List<BizFileEntity> bizFileEntityList = bizFileRepository.findAllByLinkTypeAndLinkIdAndIsDel(linkType, linkId,
				ConstantBaseData.IS_DELETE.FALSE.code);
		if (!CollectionUtils.isEmpty(bizFileEntityList)) {
			BizFileDTO bizFileDTO = new BizFileDTO();
			if (bizFileEntityList.size() > 1) {
				logger.info("more than one file effect->linkId:{}", linkId);
			}
			BeanUtils.copyProperties(bizFileEntityList.get(0), bizFileDTO);
			return bizFileDTO;
		}
		return null;
	}

}
