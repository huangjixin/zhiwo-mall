package com.zwo.xiyangyang.modules.mem.service;

import com.zwo.xiyangyang.modules.core.service.IBaseService;
import com.zwo.xiyangyang.modules.mem.domain.MemMember;

public interface IMememberService extends IBaseService<MemMember> {
	/**
	 * 根据用户名查找。
	 * @param username
	 * @return
	 */
	MemMember findByUsername(String username);
}
