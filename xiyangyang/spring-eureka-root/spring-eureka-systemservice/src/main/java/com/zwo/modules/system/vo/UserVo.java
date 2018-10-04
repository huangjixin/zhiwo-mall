/**
 * 
 */
package com.zwo.modules.system.vo;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.zwo.modules.system.domain.Resource;
import com.zwo.modules.system.domain.Role;
import com.zwo.modules.system.domain.User;

/**
 * @author 黄记新
 *
 */
public class UserVo extends User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<RoleVo> roles;
	private List<ResourceVo> resources;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		if (this.getResources() != null) {
			List<ResourceVo> list = this.getResources();
			for (Resource resource : list) {
				authSet.add(new SimpleGrantedAuthority(resource.getName()));
			}
		}

		return authSet;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return "1".equals(this.getIsValid());
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return this.getEnabled() == 1;
	}

	public List<RoleVo> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleVo> roles) {
		this.roles = roles;
	}

	public List<ResourceVo> getResources() {
		return resources;
	}

	public void setResources(List<ResourceVo> resources) {
		this.resources = resources;
	}
}
