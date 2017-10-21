/**
 * 
 */
package com.zwo.modules.system.service.impl;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.zwo.modules.system.domain.TbUser;
import com.zwo.modules.system.service.ITbUserService;
import com.zwotech.common.utils.ShiroSimpleByteSource;

/**
 * @author Administrator
 *
 */
public class UserRealm extends AuthorizingRealm {
	private static Logger logger = LoggerFactory.getLogger(UserRealm.class);

	private static final String BASE_MESSAGE = "【UserRealm服务类提供的认证和授权等】";
	
	@Autowired
	@Lazy(true)
	private ITbUserService userService;
	
	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(userService.findRoles(username));
		Set<String> set = userService.findPermissions(username);
		authorizationInfo.setStringPermissions(set);
		return authorizationInfo;
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		TbUser user = userService.findByUsername(username);
		if (user == null) {
			throw new UnknownAccountException();// 没找到帐号
		}

		if (Boolean.TRUE.equals(user.getDisable())) {
			throw new LockedAccountException(); // 帐号锁定
		}

		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				user.getUsername(), // 用户名
				user.getPassword(), // 密码
				new ShiroSimpleByteSource(user.getPassword().getBytes()),
				//ByteSource.Util.bytes(user.getPassword()),//salt=username+salt
				getName() // realm name
		);
		return authenticationInfo;
	}

}
