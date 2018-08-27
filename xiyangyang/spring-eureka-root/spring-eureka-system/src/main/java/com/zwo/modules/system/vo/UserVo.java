/**
 * 
 */
package com.zwo.modules.system.vo;

import java.util.List;

import com.zwo.modules.system.domain.Resources;
import com.zwo.modules.system.domain.Role;
import com.zwo.modules.system.domain.User;

/**
 * @author 黄记新
 *
 */
public class UserVo extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Role> roles;
	private List<Resources> resources;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Resources> getResources() {
		return resources;
	}

	public void setResources(List<Resources> resources) {
		this.resources = resources;
	}
}
