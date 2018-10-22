package com.zwo.modules.system.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BPwdEncoderUtil {
	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	public static String BCryptPassword(String password) {
		return encoder.encode(password);
	}

	public static boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encoder.matches(rawPassword, encodedPassword);
	}

//	public static void main(String[] args) {
//		System.out.println(BPwdEncoderUtil.BCryptPassword("123456"));
//
//	}

}
