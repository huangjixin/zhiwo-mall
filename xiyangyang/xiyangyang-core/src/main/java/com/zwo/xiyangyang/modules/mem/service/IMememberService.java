package com.zwo.xiyangyang.modules.mem.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zwo.xiyangyang.modules.core.service.IBaseService;
import com.zwo.xiyangyang.modules.mem.domain.MemGuessRecord;
import com.zwo.xiyangyang.modules.mem.domain.MemMember;

public interface IMememberService extends IBaseService<MemMember> {
	/**
	 * 根据用户名查找。
	 * @param username
	 * @return
	 */
	MemMember findByUsername(String username);
	/**
	 * 根据会员Id查找竞猜记录。
	 * @param memId
	 * @return
	 */
	List<MemGuessRecord> selectByMemId(String memId, PageInfo<MemGuessRecord> pageInfo);
}
