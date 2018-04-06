/**
 * 
 */
package com.zwo.xiyangyang.modules.guess.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl;
import com.zwo.xiyangyang.modules.guess.dao.GuessAccountMapper;
import com.zwo.xiyangyang.modules.guess.domain.GuessAccount;
import com.zwo.xiyangyang.modules.guess.service.IGuessAccountService;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

/**
 * @author 黃記新
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class GuessAccountServiceImpl extends BaseServiceImpl<GuessAccount> implements IGuessAccountService {
	private static Logger logger = LoggerFactory.getLogger(GuessAccountServiceImpl.class);
	
	@Override
	public Logger getLogger() {
		return logger;
	}
	@Autowired
	private GuessAccountMapper accountMapper ;

	@Override
	public Mapper<GuessAccount> getBaseMapper() {
		return accountMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return GuessAccountServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return GuessAccount.class;
	}
	@Override
	protected String getBaseMessage() {
		return "竞猜账号基础操作";
	}

	@Transactional(readOnly = true)
	@Override
	public GuessAccount selectByMid(String mid) {
		if (getLogger().isInfoEnabled()) {
			getLogger().info(getBaseMessage() + "根据会员ID查询开始，参数mid的值是：" + mid);
		}
		Example example = new Example(GuessAccount.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("memberId", mid);
		List<GuessAccount> list = getBaseMapper().selectByExample(example);
		if (getLogger().isInfoEnabled())
			getLogger().info(getBaseMessage() + "根据会员ID结果：" + (list.size()));
		
		return list.size()>0?list.get(0):null;
	}

}
