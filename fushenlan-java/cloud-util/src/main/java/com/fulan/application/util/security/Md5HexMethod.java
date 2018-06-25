package com.fulan.application.util.security;

import java.util.Date;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fulan.application.util.date.DateUtil;

/**
 * 影像系统参数加密方法
 */
public class Md5HexMethod {

	private static Logger logger = LoggerFactory.getLogger(Md5HexMethod.class);

	/**
	 * @Description 生成一个length位的随机字符串
	 * @param length
	 */
	public static String getRandomString(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 影像系统加密方法
	 * @param strTxt   随机数字
	 * @param keyStr   影像系统提供中间参数
	 * @param dateStr  yyyyMMdd
	 */
	public static String md5Encode(String strTxt, String keyStr, String dateStr) {
		dateStr = DateUtil.toShortDay(new Date());
		logger.info("发起方生成的随机字符串:" + strTxt);
		logger.info("双方系统约定的字符串:" + keyStr);
		logger.info("系统当前的日期(yyyyMMdd):" + dateStr);
		logger.info("准备加密的原文:" + (strTxt + keyStr + dateStr));
		String strMtxt = DigestUtils.md5Hex(strTxt + keyStr + dateStr);
		logger.info("原文加密后得到的密文:" + strMtxt);
		return strMtxt;
	}

}
