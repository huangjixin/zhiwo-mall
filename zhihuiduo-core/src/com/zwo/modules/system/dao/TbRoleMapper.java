package com.zwo.modules.system.dao;

import java.util.List;

import com.zwo.modules.system.domain.TbRole;

import tk.mybatis.mapper.common.Mapper;

public interface TbRoleMapper extends Mapper<TbRole> {
	/**
	 * 用户名或者电话查询角色。
	 * @param usernameOrPhone
	 * @return
	 */
	List<TbRole> selectByUsernameOrPhone(String usernameOrPhone);
	
	/**
	 * 根据用户组Id查询角色。
	 * @param usergroupId
	 * @return
	 */
	List<TbRole> findByUserGroupId(String usergroupId);
}