package com.zwo.modules.system.security;

import org.apache.shiro.crypto.hash.SimpleHash;

public class PasswordHelper {

	public static Object ecrypt(Object crdentials,Object salt) {
		String hashAlgorithmName = "MD5";//加密方式
	    int hashIterations = 1;//加密1024次
	    Object result = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);
	    return result;
	}
	
	public static boolean isMatch(Object crdentials,Object salt,Object ecryptCrdentials) {
		crdentials = ecrypt(crdentials, salt);
		return crdentials.equals(ecryptCrdentials);
	}
	
	public static void main(String[] args) {
		Object o = PasswordHelper.ecrypt("123456", null);
		System.out.println(o);
		boolean isMatch = isMatch("123456", null, o);
		System.out.println(isMatch);
	}

}
