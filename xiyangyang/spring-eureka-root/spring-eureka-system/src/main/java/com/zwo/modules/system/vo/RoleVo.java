/**
 * 
 */
package com.zwo.modules.system.vo;

import java.util.List;

import com.zwo.modules.system.domain.Resources;
import com.zwo.modules.system.domain.Role;
import com.zwo.modules.system.domain.User;

/**
 * @author 黃記新
 *
 */
public class RoleVo extends Role {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<User> users;
	private List<Resources> resources;
	
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<Resources> getResources() {
		return resources;
	}
	public void setResources(List<Resources> resources) {
		this.resources = resources;
	}
}
