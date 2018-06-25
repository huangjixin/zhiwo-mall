package com.fulan.application.util.str;

public class StringUtil {

	/* 常量和singleton。 */

	/** 空字符串。 */
	public static final String EMPTY_STRING = "";

	/**
	 * 如果字符串是null，则返回空字符串""，否则返回字符串本身。
	 */
	public static String defaultIfNull(String str) {
		return (str == null) ? EMPTY_STRING : str;
	}

	public static String defaultIfNull(String str, String defaultStr) {
		return (str == null) ? defaultStr : str;
	}

	/***
	 * 除去字符串头尾部的空白
	 */
	public static String trim(String str) {
		return trim(str, null, 0);
	}

	public static String[] trimArray(String[] array) {
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				if (array[i] != null) {
					array[i] = trim(array[i]);
				}
			}
		}
		return array;
	}
	/***
	 * 除去字符串头尾部的指定字符
	 */
	public static String trim(String str, String stripChars) {
		return trim(str, stripChars, 0);
	}

	/**
	 * 除去字符串头部的空白
	 */
	public static String trimStart(String str) {
		return trim(str, null, -1);
	}
	/**
	 * aviyy
	 * 字符串首字母大写
	 */
	public static String upperFirst(String str) {
		char[] cs = str.toCharArray();
		if(cs[0]>='a' && cs[0]<='z') {
			cs[0] -= 32;
		}
		return String.valueOf(cs);
	}
	/**
	 * aviyy
	 * 字符串首字母小写
	 */
	public static String lowerFirst(String str) {
		char[] cs = str.toCharArray();
		if(cs[0]>='A' && cs[0]<='Z') {
			cs[0] += 32;
		}
		return String.valueOf(cs);
	}
	/**
	 * 除去字符串头尾部的指定字符
	 */
	private static String trim(String str, String stripChars, int mode) {
		if (str == null) {
			return null;
		}

		int length = str.length();
		int start = 0;
		int end = length;

		// 扫描字符串头部
		if (mode <= 0) {
			if (stripChars == null) {
				while ((start < end) && (Character.isWhitespace(str.charAt(start)))) {
					start++;
				}
			} else if (stripChars.length() == 0) {
				return str;
			} else {
				while ((start < end) && (stripChars.indexOf(str.charAt(start)) != -1)) {
					start++;
				}
			}
		}

		// 扫描字符串尾部
		if (mode >= 0) {
			if (stripChars == null) {
				while ((start < end) && (Character.isWhitespace(str.charAt(end - 1)))) {
					end--;
				}
			} else if (stripChars.length() == 0) {
				return str;
			} else {
				while ((start < end) && (stripChars.indexOf(str.charAt(end - 1)) != -1)) {
					end--;
				}
			}
		}

		if ((start > 0) || (end < length)) {
			return str.substring(start, end);
		}
		return str;
	}
	public static boolean isEmpty(String str){
		if(null == str || "".equals(str)){
			return true;
		}
		return false;
	}
}
