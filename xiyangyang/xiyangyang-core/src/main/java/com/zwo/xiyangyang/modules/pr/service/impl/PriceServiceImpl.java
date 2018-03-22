/**
 * 
 */
package com.zwo.xiyangyang.modules.pr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl;
import com.zwo.xiyangyang.modules.pr.dao.PrPriceMapper;
import com.zwo.xiyangyang.modules.pr.domain.PrPrice;
import com.zwo.xiyangyang.modules.pr.service.IPriceService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author 黃記新
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class PriceServiceImpl extends BaseServiceImpl<PrPrice> implements IPriceService {

	@Autowired
	private PrPriceMapper priceMapper ;

	@Override
	public Mapper<PrPrice> getBaseMapper() {
		return priceMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return PriceServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return PrPrice.class;
	}

	@Override
	protected String getBaseMessage() {
		return "商品价格基础操作";
	}

}
