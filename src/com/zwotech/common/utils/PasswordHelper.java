package com.zwotech.common.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 14-1-28
 * <p>
 * Version: 1.0
 */
public class PasswordHelper {

	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

	private static String algorithmName = "md5";
	private static int hashIterations = 1;

	public void setRandomNumberGenerator(
			RandomNumberGenerator randomNumberGenerator) {
		this.randomNumberGenerator = randomNumberGenerator;
	}

	public static String encryptPassword(String password) {

		String newPassword = new SimpleHash(algorithmName, password,
				ByteSource.Util.bytes(password), hashIterations).toHex();
		return newPassword;
	}

	public static boolean validatePassword(String plainPassword, String password) {
		String newPassword = new SimpleHash(algorithmName, plainPassword,
				ByteSource.Util.bytes(plainPassword), hashIterations).toHex();
		if (newPassword.equals(password)) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		String _123456Password = PasswordHelper.encryptPassword("7cdeeb");
		System.out.println(_123456Password);
	}
}
