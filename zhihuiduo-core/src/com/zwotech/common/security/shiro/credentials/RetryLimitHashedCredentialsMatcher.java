package com.zwotech.common.security.shiro.credentials;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import com.zwotech.common.utils.PasswordHelper;

public class RetryLimitHashedCredentialsMatcher extends
		HashedCredentialsMatcher {

	private Cache<String, AtomicInteger> passwordRetryCache;
	private CacheManager cacheManager;
	/*public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	}*/

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		String username = usernamePasswordToken.getUsername();
		// retry count + 1
		/*if(null != cacheManager){
			if(passwordRetryCache!=null){
				AtomicInteger retryCount = passwordRetryCache.get(username);
				if (retryCount == null) {
					retryCount = new AtomicInteger(0);
					passwordRetryCache.put(username, retryCount);
				}
				if (retryCount.incrementAndGet() > 5) {
					 throw new ExcessiveAttemptsException();
				}
			}
		}*/

//		boolean matches = super.doCredentialsMatch(token, info);
		boolean matches = false;
		String password = new String((char[]) token.getCredentials());
		String dbPassword = (String) info.getCredentials();
		if (password.equals(dbPassword)) {
			matches = true;
		}

		//matches = PasswordHelper.validatePassword(password, dbPassword);

		/*if (matches) {
			if(null != cacheManager){
				if(passwordRetryCache!=null)
					passwordRetryCache.remove(username);
			}
		}*/
		return matches;
	}

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	}
}
