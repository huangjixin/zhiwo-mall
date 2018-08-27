package com.zwo.modules.shiro;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 密码辅助类
 * @author 黄记新
 *
 */
public class PasswordHelper {
	/**
	 * 加密密码。
	 * @param password
	 * @return
	 */
	public static String encryptPassword(String password) {
		int hashIterations = 1;
		String algorithmName = "md5";   
    	String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
    	SimpleHash hash = new SimpleHash(algorithmName, password, salt2, hashIterations);  
    	String encodedPassword = hash.toHex();
    	return encodedPassword;
	}
	
	/**
	 * 验证加密密码是否正确。
	 * @param password
	 * @param encryptPassword
	 * @return
	 */
	public static boolean validatePassword(String password,String encryptPassword) {
		return encryptPassword.equals(PasswordHelper.encryptPassword(password));
	}
}
