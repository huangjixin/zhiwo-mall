package com.fulan.core.monitoring.cat.constant;

public class Constant {
	
	public static final String REMOTE_REST = "rest";
	public static final String REMOTE_FEIGN = "feign";
	public static final String REMOTE_DEFAULT = "no";
	public static final String DAO_JPA = "jpa";
	public static final String DAO_MYBATIS = "mybatis";
	public static final String DAO_DEFAULT = "no";
	public static final String GATEWAY_NO = "false";
	public static final String GATEWAY_YES = "true";

	/**
	 * 登录信息存入缓存KEY
	 */
	public static final String LOGIN_ACCOUNT = "login_account_";
	
	/**
	 * DMS登录信息存入缓存KEY
	 */
	public static final String DMS_LOGIN_ACCOUNT = "dms_login_account_";
	/**
	 * 手机黑名单所有类型
	 */
	public static final String SMS_ALL = "ALL";
	/**
	 * 手机黑名单验证类型
	 */
		public static final String SMS_VALIDATE = "VALIDATE";
	/**
	 * 手机黑名单业务类型
	 */
	public static final String SMS_BUSNIESS = "BUSNIESS";
	/**
	 * 通道
	 */
	public static final String SMS_CHANNEL = "SMS_CHANNEL";
	/**
	 * 系统
	 */
	public static final String SMS_SYSTEM = "SMS_SYSTEM";
	/**
	 * 远程ip  白名单
	 */
	public static final String REMOTE_IP = "REMOTE_IP";
	/**
	 * 因子
	 */
	public static final String SMS_FACTOR = "SMS_FACTOR";
	/**
	 * 验证码有效期
	 */
	public static final String SMS_VALID_TIME = "SMS_VALID_TIME";


}
