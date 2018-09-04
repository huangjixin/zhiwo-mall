package com.zwo.modules.system.security;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zwo.modules.system.service.IUserService;
import com.zwo.modules.system.utils.JWTUtil1;
import com.zwo.modules.system.vo.JWTToken;
import com.zwo.modules.system.vo.UserVo;

public class ShiroSecurityRealm extends AuthorizingRealm {

	private static Logger logger = LoggerFactory.getLogger(ShiroSecurityRealm.class);;
	private static final String MESSAGE = "ShiroSecurityRealm认证授权";

	@Autowired
	private IUserService userService;

	/**
	 * 大坑!,必须重写此方法,不然Shiro会报错
	 */
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JWTToken;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		if (logger.isInfoEnabled())
			logger.info(MESSAGE + "权限配置-->SystemUserRealm.doGetAuthorizationInfo()开始");
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(userService.findRoles(username));
		Set<String> set = userService.findPermissions(username);
		authorizationInfo.setStringPermissions(set);
		if (logger.isInfoEnabled())
			logger.info(MESSAGE + "权限配置结束");
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		//获取用户的输入的账号.  
        String username = usernamePasswordToken.getUsername();  
        //通过username从数据库中查找 User对象，如果找到，没找到.  
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法  
        UserVo user = userService.findByUsername(username);  
        if (user == null) {  
            return null;  
        }  
        if (!user.getEnabled()) { //账户冻结  
            throw new LockedAccountException();  
        }  
        
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(  
        		usernamePasswordToken.getPrincipal(), //用户名  
        		JWTUtil1.sign(username, user.getPassword()),
                getName() 
        );  
        return authenticationInfo; 
	}

}
