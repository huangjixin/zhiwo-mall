/**
 * 
 */
package com.zwo.modules.shiro.service.impl;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author FWDuser
 *
 */
public class UserShiroRealm extends AuthorizingRealm {
	
	private static Logger logger = LoggerFactory.getLogger(UserShiroRealm.class);;
	private static final String MESSAGE="UserShiroRealm认证授权";
//	
//	@Autowired
//	private IUserService userService;
	
	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		if (logger.isInfoEnabled())
//			logger.info(MESSAGE+"权限配置-->UserShiroRealm.doGetAuthorizationInfo()开始");
//		String username = (String) principals.getPrimaryPrincipal();
//		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//		authorizationInfo.setRoles(userService.findRoles(username));
//		Set<String> set = userService.findPermissions(username);
//		authorizationInfo.setStringPermissions(set);
//		if (logger.isInfoEnabled())
//			logger.info(MESSAGE+"权限配置结束");
//		return authorizationInfo;
		return null;
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
//		//获取用户的输入的账号.  
//        String username = usernamePasswordToken.getUsername();  
//        //通过username从数据库中查找 User对象，如果找到，没找到.  
//        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法  
//        User user = userService.findByUsername(username);  
//        if (user == null) {  
//            return null;  
//        }  
//        if (!user.getEnabled()) { //账户冻结  
//            throw new LockedAccountException();  
//        }  
//        
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(  
//        		usernamePasswordToken.getPrincipal(), //用户名  
//                user.getPassword(), //密码  
////                new ShiroSimpleByteSource(user.getSalt().getBytes()),
////                ByteSource.Util.bytes(user.getSalt()),
//                new MySimpleByteSource(user.getSalt().getBytes()),
//                getName() 
//        );  
//        return authenticationInfo; 
		return null;
	}

    /*@Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        super.setCredentialsMatcher(credentialsMatcher);
    }*/

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}
