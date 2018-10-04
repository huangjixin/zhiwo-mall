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

	private List<Role> roles;
	private List<Resource> resource;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Resource> getResource() {
		return resource;
	}

	public void setResource(List<Resource> Resource) {
		this.resource = Resource;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		if(this.getResource() !=null) {
			List<Resource> list = this.getResource();
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
		return this.getEnabled()==1;
	}
}
