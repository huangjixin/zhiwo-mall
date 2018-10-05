/**
 * 
 */
package com.zwo.modules.system.vo;

import org.springframework.security.core.GrantedAuthority;

import com.zwo.modules.system.domain.Resource;

/**
 * @author lenovo
 *
 */
public class ResourceVo extends Resource implements GrantedAuthority{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getAuthority() {
		return super.getName();
	}

}
