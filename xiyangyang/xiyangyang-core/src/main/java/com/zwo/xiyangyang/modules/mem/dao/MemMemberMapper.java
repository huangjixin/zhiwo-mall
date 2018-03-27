package com.zwo.xiyangyang.modules.mem.dao;

import org.apache.ibatis.annotations.Param;

import com.zwo.xiyangyang.modules.mem.domain.MemMember;
import tk.mybatis.mapper.common.Mapper;

public interface MemMemberMapper extends Mapper<MemMember> {
	/**
	 * 根据ID进行查询。
	 * @param id
	 * @return
	 */
	MemMember selectById(@Param("id") String id);
}