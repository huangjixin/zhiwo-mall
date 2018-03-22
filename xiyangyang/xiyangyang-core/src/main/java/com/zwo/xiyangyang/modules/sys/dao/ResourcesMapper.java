package com.zwo.xiyangyang.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zwo.xiyangyang.modules.sys.domain.Resources;
import tk.mybatis.mapper.common.Mapper;

public interface ResourcesMapper extends Mapper<Resources> {
	
	/**
	 * 根据用户名查询权限名.
	 * @param username
	 * @return
	 */
	List<String> selectResoucesByUsername(@Param(value = "username") String username);
}