/**
 * 
 */
package com.zwo.modules.system.service;

import java.util.Set;

import com.zwo.modules.core.service.IBaseService;
import com.zwo.modules.system.domain.User;

/**
 * @author FWDuser
 *
 */
public interface IUserService extends IBaseService<User> {

	Set<String> findRoles(String username);

	Set<String> findPermissions(String username);

	User findByUsername(String username);

}
