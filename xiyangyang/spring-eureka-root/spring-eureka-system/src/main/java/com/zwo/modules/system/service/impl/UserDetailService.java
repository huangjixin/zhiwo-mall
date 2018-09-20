/**
 * 
 */
package com.zwo.modules.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.zwo.modules.system.service.IUserService;

/**
 * @author 黄记新
 * 
 */
public class UserDetailService implements UserDetailsService {

	@Autowired
	private IUserService userService;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = userService.findByUsername(username);
		return userDetails;
	}

}
