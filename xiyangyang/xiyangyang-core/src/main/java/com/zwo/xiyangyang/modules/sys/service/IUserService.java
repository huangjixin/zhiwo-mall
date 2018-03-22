package com.zwo.xiyangyang.modules.sys.service;

import java.util.Set;

import com.zwo.xiyangyang.modules.core.service.IBaseService;
import com.zwo.xiyangyang.modules.sys.domain.User;

public interface IUserService extends IBaseService<User>{
	/**
	 * 根据用户名查询权限。
	 * @param username
	 * @return
	 */
	Set<String> findPermissions(String username);
	
	/**
	 * 根据用户名查找角色。
	 * @param username
	 * @return
	 */
	Set<String> findRoles(String username);
	/**
	 * 根据用户名查找。
	 * @param username
	 * @return
	 */
	User findByUsername(String username);
}
