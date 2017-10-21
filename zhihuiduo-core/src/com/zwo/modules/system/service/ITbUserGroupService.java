/**
 * 
 */
package com.zwo.modules.system.service;

import java.util.List;

import com.zwo.modules.system.domain.TbRole;
import com.zwo.modules.system.domain.TbRoleResources;
import com.zwo.modules.system.domain.TbUserGroup;
import com.zwotech.modules.core.service.IBaseService;

/**
 * @author hjx
 *
 */
public interface ITbUserGroupService extends IBaseService<TbUserGroup> {
	/**
	 * 根据用户组Id查询角色。
	 * @param usergroupId
	 * @return
	 */
	List<TbRole> findByUserGroupId(String usergroupId);
	/**
	 * 批量关联用户组角色。
	 * @param userGroupId
	 * @param roleId
	 */
	void batchConnectUserGroupRole(List<String> roleIds, String usergroupId);
	/**
	 * 批量解除关联用户组角色。
	 * @param userGroupId
	 * @param roleId
	 */
	void batchUnconnectUserGroupRole(String usergroupId);
}
