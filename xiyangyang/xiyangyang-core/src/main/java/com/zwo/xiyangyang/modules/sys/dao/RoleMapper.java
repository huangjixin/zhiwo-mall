package com.zwo.xiyangyang.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zwo.xiyangyang.modules.sys.domain.Role;
import tk.mybatis.mapper.common.Mapper;

public interface RoleMapper extends Mapper<Role> {
	/**
	 * 根据用户名查询权限名.
	 * @param username
	 * @return
	 */
	List<Role> selectRoleByUsername(@Param(value = "username") String username);
}