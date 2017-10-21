/**
 * 
 */
package com.zwo.modules.system.service;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.zwo.modules.system.domain.TbUser;
import com.zwo.modules.system.domain.TbUserCriteria;
import com.zwo.modules.system.domain.TbUserUserGroup;
import com.zwotech.modules.core.service.IBaseService;

/**
 * @author hjx
 *
 */
public interface ITbUserService extends IBaseService<TbUser> {
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	TbUser findByUsername(String username);
	
	/**
	 * 查找角色
	 * @param username
	 * @return
	 */
	Set<String> findRoles(String username);
	
	/**
	 * 查找资源权限。
	 * @param username
	 * @return
	 */
	Set<String> findPermissions(String username);
	/**
	 * 判断登录情况。
	 * @param user
	 * @return
	 */
	TbUser login(TbUser user);
	

	/**
	 * 关联用户组角色。
	 * @param userGroupId
	 * @param roleId
	 */
	void connectUserGroupRole(String userGroupId, String roleId);
	
	/**
	 * 解除用户组角色的关联关系。
	 * @param userGroupId
	 * @param roleId
	 */
	void unconnectUserGroupRole(String userGroupId, String roleId);
	
	
	/**
	 * 根据查询条件进行查询以及组名进行查询
	 * @param example
	 * @param groupName
	 * @return
	 */
	List<TbUserUserGroup> selectByExampleAndGroupName(TbUserCriteria example,
			String groupName);
	
}
