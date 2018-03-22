/**
 * 
 */
package com.zwo.xiyangyang.modules.mem.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl;
import com.zwo.xiyangyang.modules.mem.dao.MemMemberMapper;
import com.zwo.xiyangyang.modules.mem.domain.MemMember;
import com.zwo.xiyangyang.modules.mem.service.IMememberService;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

/**
 * @author 黃記新
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class MemMemberServiceImpl extends BaseServiceImpl<MemMember> implements IMememberService {
	private static Logger logger = LoggerFactory.getLogger(MemMemberServiceImpl.class);;
	private static final String MESSAGE="会员基础操作";
	
	@Autowired
	private MemMemberMapper memberMapper ;

	@Override
	public Mapper<MemMember> getBaseMapper() {
		return memberMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return MemMemberServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return MemMember.class;
	}

	@Override
	protected String getBaseMessage() {
		return "会员基础操作";
	}

	@Override
	public MemMember findByUsername(String username) {
		if (logger.isInfoEnabled())
			logger.info(MESSAGE+"根据用户名查询用户开始，参数username是："+username);
		Example example = new Example(MemMember.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
		List<MemMember> list = memberMapper.selectByExample(example);
		if (logger.isInfoEnabled())
			logger.info(MESSAGE+"根据用户名查询用户结束，结果："+list.size());
		return list.size()>0?list.get(0):null;
	}

}
