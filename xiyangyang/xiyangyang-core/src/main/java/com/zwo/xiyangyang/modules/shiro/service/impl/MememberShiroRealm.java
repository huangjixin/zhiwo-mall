/**
 * 
 */
package com.zwo.xiyangyang.modules.shiro.service.impl;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zwo.xiyangyang.modules.mem.domain.MemMember;
import com.zwo.xiyangyang.modules.mem.service.IMememberService;
import com.zwo.xiyangyang.modules.shiro.ShiroSimpleByteSource;

/**
 * @author FWDuser
 *
 */
public class MememberShiroRealm extends AuthorizingRealm {

	private static Logger logger = LoggerFactory.getLogger(MememberShiroRealm.class);;
	private static final String MESSAGE = "MememberShiroRealm认证授权";
	@Autowired
	private IMememberService mememberService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.
	 * shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		if (logger.isInfoEnabled())
			logger.info(MESSAGE + "权限配置-->UserShiroRealm.doGetAuthorizationInfo()开始");
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache
	 * .shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 获取用户的输入的账号.
		String username = (String) token.getPrincipal();
		// System.out.println(token.getCredentials());
		// 通过username从数据库中查找 User对象，如果找到，没找到.
		// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		MemMember member = mememberService.findByUsername(username);
		if (member == null) {
			return null;
		}
		if (!member.getEnabled()) { // 账户冻结
			throw new LockedAccountException();
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(member.getUsername(), // 用户名
				member.getPassword(), // 密码
				new ShiroSimpleByteSource(member.getPassword().getBytes()),
				// ByteSource.Util.bytes(member.getUsername()),//salt=username+salt
				getName());
		return authenticationInfo;
	}

}
