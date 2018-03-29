/**
 * 
 */
package com.zwo.xiyangyang.modules.pr.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl;
import com.zwo.xiyangyang.modules.pr.dao.ShopMapper;
import com.zwo.xiyangyang.modules.pr.domain.Shop;
import com.zwo.xiyangyang.modules.pr.service.IShopService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author 黃記新
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class ShopServiceImpl extends BaseServiceImpl<Shop> implements IShopService {
	private static Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);
	
	@Override
	public Logger getLogger() {
		return logger;
	}
	@Autowired
	private ShopMapper shopMapper ;

	@Override
	public Mapper<Shop> getBaseMapper() {
		return shopMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return ShopServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return Shop.class;
	}

	@Override
	protected String getBaseMessage() {
		return "商店基础操作";
	}

}
