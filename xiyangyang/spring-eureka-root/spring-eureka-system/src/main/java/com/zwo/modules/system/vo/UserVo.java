/**
 * 
 */
package com.zwo.modules.system.vo;

import java.util.List;

import com.zwo.modules.system.domain.Resource;
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
	private List<Resource> Resource;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Resource> getResource() {
		return Resource;
	}

	public void setResource(List<Resource> Resource) {
		this.Resource = Resource;
	}
}
