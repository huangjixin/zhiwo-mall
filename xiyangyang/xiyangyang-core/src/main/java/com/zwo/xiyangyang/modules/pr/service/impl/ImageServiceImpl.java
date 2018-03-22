/**
 * 
 */
package com.zwo.xiyangyang.modules.pr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl;
import com.zwo.xiyangyang.modules.pr.dao.PrImageMapper;
import com.zwo.xiyangyang.modules.pr.domain.PrImage;
import com.zwo.xiyangyang.modules.pr.service.IImageService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author 黃記新
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class ImageServiceImpl extends BaseServiceImpl<PrImage> implements IImageService {

	@Autowired
	private PrImageMapper categoryMapper ;

	@Override
	public Mapper<PrImage> getBaseMapper() {
		return categoryMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return ImageServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return PrImage.class;
	}

	@Override
	protected String getBaseMessage() {
		return "商品图片基础操作";
	}

}
