package com.fulan.application.util.util;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.collections.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 字符串工具类
 * @author lkj
 *
 */
public class StringUtils {
	
	/**
	 * 获取request里的值
	 * @param request
	 * @param name 要获取的参数的key
	 * @return String
	 */
	public static String getParameter(HttpServletRequest request, String name) {
		return getParameter(request, name, "");
	}
	
	/**
	 * 获取request里的值
	 * @param request
	 * @param name 要获取的参数的key
	 * @param defaultValue 当值为空的时候返回该默认值
	 * @return String
	 */
	public static String getParameter(HttpServletRequest request, String name, String defaultValue) {
		String value = request.getParameter(name);
		return value == null ? defaultValue : value;
	}
	
	/**
	 * 将一个字符串转换成整型，当转换出错时返回0
	 * @param str
	 * @return int
	 * 2015年5月28日 下午3:18:41
	 * @author lkj
	 */
	public static int parseInt(String str) {
		return parseInt(str, 0);
	}
	
	/**
	 * 将一个字符串转换成整型，当转换出错时返回默认值
	 * @param str 要转换的参数
	 * @param i 当str转换失败的时候返回该值
	 * @return int
	 * 2015年5月28日 下午3:14:20
	 * @author lkj
	 */
	public static int parseInt(String str, int i) {
		try {
			return Integer.parseInt(str);
		} catch(Exception e) {
			return i;
		}
	}
	
	/**
	 * 判断一个字符串是否为空，当为空是返回""
	 * @param str 需要判断的字符串
	 * @return
	 * 2015年6月1日 下午8:45:59
	 * @author lkj
	 */
	public static String isNull(String str) {
		return isNull(str, "");
	}
	
	/**
	 * 判断一个字符串是否为空，为空时则返回默认参数
	 * @param str 需要判断的字符串
	 * @param defaultStr 当判断的字符串为空是返回该值
	 * @return
	 * 2015年6月1日 下午8:44:19
	 * @author lkj
	 */
	public static String isNull(String str, String defaultStr) {
		return str == null ? defaultStr : str;
	}
	
	/**
	 * 判断第一个参数是否与后面的其中某一个参数相匹配
	 * @param str
	 * @param args
	 * @return
	 * 2015年6月18日 下午8:07:17
	 * @author lkj
	 */
	public static boolean eqs(String str, String... args) {
		for(int a = 0; a < args.length; a++) {
			if(args[a].equals(str)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 获取Map里的值，为空时返回默认值
	 * @param map
	 * @param key
	 * @param defaultVal
	 * @return
	 * 2015年12月10日 下午12:04:04
	 * @author lkj
	 */
	public static Object getMapVal(Map<?, ?> map, Object key, Object defaultVal) {
		if(map.get(key) == null) {
			return defaultVal;
		} else {
			return map.get(key);
		}
	}
	public static String getMapVal(Map<?, ?> map, Object key, String defaultVal) {
		if(map.get(key) == null) {
			return defaultVal;
		} else {
			return map.get(key).toString();
		}
	}

	public static boolean isEmptry(String s){
	   if (s == null || s.length() == 0)
            return true;
        else
            return false;
    }

	public static Set<String> stringToSet(String s){
		if (s == null || s.length() == 0) {
			return null;
		}
		else {
			Set<String> set = new HashSet<>();
			String[] split = s.split(",");
			CollectionUtils.addAll(set, split);
			return set;
		}
	}


	public static List<String> stringToList(String s){
		if (s == null || s.length() == 0) {
			return null;
		}
		else {
			List<String> list = new ArrayList<>();
			String[] split = s.split(",");
			CollectionUtils.addAll(list, split);
			return list;
		}
	}

	public static List<String> stringToList(String s,String symbol){
		if (s == null || s.length() == 0) {
			return null;
		}
		else {
			List<String> list = new ArrayList<>();
			String[] split = s.split(symbol);
			CollectionUtils.addAll(list, split);
			return list;
		}
	}

	public static String listToString(List<String> list){
		return String.join(",",list);
	}

	/**
	 * SHA1加密
	 *
	 * @param strSrc 明文
	 * @return 加密之后的密文
	 */
	public static String encrypt(String strSrc) {
		MessageDigest md = null;
		String strDes = null;
		byte[] bt = strSrc.getBytes();
		try {
			md = MessageDigest.getInstance("SHA-1");// 将此换成SHA-1、SHA-512、SHA-384等参数
			md.update(bt);
			strDes = bytes2Hex(md.digest()); // to HexString
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		return strDes;
	}

	/**
	 * byte数组转换为16进制字符串
	 *
	 * @param bts 数据源
	 * @return 16进制字符串
	 */
	private static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}

	public static String decodeHexStr(int dataCoding, String hexStr) {
		String realStr = null;
		try {
			if (hexStr != null) {
				if (dataCoding == 15) {
					realStr = new String(Hex.decodeHex(hexStr.toCharArray()), "GBK");
				} else if ((dataCoding & 0x0C) == 0x08) {
					realStr = new String(Hex.decodeHex(hexStr.toCharArray()), "UnicodeBigUnmarked");
				} else {
					realStr = new String(Hex.decodeHex(hexStr.toCharArray()), "ISO8859-1");
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return realStr;
	}
}
