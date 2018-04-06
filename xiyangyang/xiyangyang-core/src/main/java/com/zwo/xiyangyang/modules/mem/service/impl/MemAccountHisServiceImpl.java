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
import com.zwo.xiyangyang.modules.mem.dao.MemAccountHisMapper;
import com.zwo.xiyangyang.modules.mem.domain.MemAccountHis;
import com.zwo.xiyangyang.modules.mem.service.IMemAccountHisService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author 黃記新
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class MemAccountHisServiceImpl extends BaseServiceImpl<MemAccountHis> implements IMemAccountHisService {

	@Autowired
	private MemAccountHisMapper memAccountHisMapper ;

	@Override
	public Mapper<MemAccountHis> getBaseMapper() {
		return memAccountHisMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return MemAccountHisServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return MemAccountHis.class;
	}

	@Override
	protected String getBaseMessage() {
		return "会员账号历史基础操作";
	}

	private static Logger logger = LoggerFactory.getLogger(MemAccountHisServiceImpl.class);
	
	@Override
	public Logger getLogger() {
		return logger;
	}

}
