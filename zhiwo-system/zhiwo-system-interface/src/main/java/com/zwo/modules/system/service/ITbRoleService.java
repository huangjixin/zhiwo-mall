/**
 * 
 */
package com.zwo.modules.system.service;

import java.util.List;

import com.zwo.modules.system.domain.TbRole;
import com.zwo.modules.system.domain.TbRoleResources;
import com.zwotech.modules.core.service.IBaseService;

/**
 * @author hjx
 *
 */
public interface ITbRoleService extends IBaseService<TbRole> {
	/**
	 * 关联角色资源。
	 * @param userGroupId
	 * @param roleId
	 */
	void connectRoleResources(String resourcesId, String roleId);
	/**
	 * 批量关联角色资源。
	 * @param userGroupId
	 * @param roleId
	 */
	void batchConnectRoleResources(List<TbRoleResources> roleResources, String roleId);
	/**
	 * 批量解除关联角色资源。
	 * @param userGroupId
	 * @param roleId
	 */
	void batchUnconnectRoleResources(String roleId);
	
	/**
	 * 解除用户组角色的关联关系。
	 * @param userGroupId
	 * @param roleId
	 */
	void unconnectRoleResources(String resourcesId, String roleId);
}
