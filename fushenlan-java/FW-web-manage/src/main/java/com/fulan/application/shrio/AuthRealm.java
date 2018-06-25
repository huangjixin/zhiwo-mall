package com.fulan.application.shrio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.fulan.api.security.domain.Account;
import com.fulan.api.security.service.AccountRoleService;
import com.fulan.api.security.service.AccountService;

public class AuthRealm extends AuthorizingRealm {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountRoleService accountRoleService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		Account user = (Account) super.getAvailablePrincipal(principal);
		// 获取用户权限和资源
		if(null !=user){
			SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();  
			//用户所拥有的角色
			List<String> accountRoleManageVoList =  accountRoleService.selectAccountRoleManageVo(user.getId().toString());
		    info.addStringPermissions(accountRoleManageVoList);// 将权限放入shiro中
		    List<String> accountIdList =  accountRoleService.selectByAccountId(user.getId().toString());
		    info.addStringPermissions(accountIdList);// 将权限放入shiro中
		    return info;
		}else{
			return null;
		}
		
		
		
		
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken utoken = (UsernamePasswordToken) token;// 获取用户输入的token
		String accountName = utoken.getUsername();
		Account user = accountService.findByName(accountName);
		if (user != null) {
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
			return authcInfo;
		}
		return null;// 放入shiro.调用CredentialsMatcher检验密码
	}

	private void setSession(Object key, Object value) {
		Session session = getSession();
		System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
		if (null != session) {
			session.setAttribute(key, value);
		}
	}

	private Session getSession() {
		try {
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null) {
				session = subject.getSession();
			}
			if (session != null) {
				return session;
			}
		} catch (InvalidSessionException e) {

		}
		return null;
	}
}
