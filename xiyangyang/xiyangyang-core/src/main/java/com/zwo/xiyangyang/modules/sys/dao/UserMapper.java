package com.zwo.xiyangyang.modules.sys.dao;

import org.apache.ibatis.annotations.Param;

import com.zwo.xiyangyang.modules.sys.domain.User;

import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
	/**
	 * @param id
	 * @return
	 */
	User selectById(@Param("id") String id);
}