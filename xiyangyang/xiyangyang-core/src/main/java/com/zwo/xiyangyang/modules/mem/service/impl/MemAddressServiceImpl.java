/**
 * 
 */
package com.zwo.xiyangyang.modules.mem.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl;
import com.zwo.xiyangyang.modules.mem.dao.MemAddressMapper;
import com.zwo.xiyangyang.modules.mem.domain.MemAddress;
import com.zwo.xiyangyang.modules.mem.service.IMemAddressService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author 黃記新
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class MemAddressServiceImpl extends BaseServiceImpl<MemAddress> implements IMemAddressService {

	@Autowired
	private MemAddressMapper addressMapper ;
	
	private static Logger logger = LoggerFactory.getLogger(MemAddressServiceImpl.class);
	
	@Override
	public Logger getLogger() {
		return logger;
	}
	@Override
	public Mapper<MemAddress> getBaseMapper() {
		return addressMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return MemAddressServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return MemAddress.class;
	}

	@Override
	protected String getBaseMessage() {
		return "会员地址基础操作";
	}

}
