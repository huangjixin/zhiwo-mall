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
import com.zwo.xiyangyang.modules.mem.dao.MemAccountMapper;
import com.zwo.xiyangyang.modules.mem.domain.MemAccount;
import com.zwo.xiyangyang.modules.mem.service.IMemAccountService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author 黃記新
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class MemAccountServiceImpl extends BaseServiceImpl<MemAccount> implements IMemAccountService {

	@Autowired
	private MemAccountMapper accountMapper ;
	private static Logger logger = LoggerFactory.getLogger(MemAccountServiceImpl.class);
	
	@Override
	public Logger getLogger() {
		return logger;
	}
	@Override
	public Mapper<MemAccount> getBaseMapper() {
		return accountMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return MemAccountServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return MemAccount.class;
	}

	@Override
	protected String getBaseMessage() {
		return "会员账号基础操作";
	}

}
