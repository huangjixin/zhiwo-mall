package com.fulan.application.common;

/**
 * @Description: 阿里云返回状态码
 * @author: guiyang
 * @date: 2018/3/12 16:28
 */
public class ConstantUtil {

    /**
     *请求成功中文
     */
    public final static String SEND_SUCCESS = "发送成功";
    /**
     *请求成功
     */
    public final static String OK = "OK";
    /**
     *RAM权限DENY
     */
    public final static String RAM_PERMISSION_DENY = "isp.RAM_PERMISSION_DENY";
    /**
     *业务停机
     */
    public final static String OUT_OF_SERVICE = "isv.OUT_OF_SERVICE";
    /**
     *未开通云通信产品的阿里云客户
     */
    public final static String PRODUCT_UN_SUBSCRIPT = "isv.PRODUCT_UN_SUBSCRIPT";
    /**
     *产品未开通
     */
    public final static String PRODUCT_UNSUBSCRIBE = "isv.PRODUCT_UNSUBSCRIBE";
    /**
     *账户不存在
     */
    public final static String ACCOUNT_NOT_EXISTS = "isv.ACCOUNT_NOT_EXISTS";
    /**
     *账户异常
     */
    public final static String ACCOUNT_ABNORMAL = "isv.ACCOUNT_ABNORMAL";
    /**
     *短信模板不合法
     */
    public final static String SMS_TEMPLATE_ILLEGAL = "isv.SMS_TEMPLATE_ILLEGAL";
    /**
     *短信签名不合法
     */
    public final static String SMS_SIGNATURE_ILLEGAL = "isv.SMS_SIGNATURE_ILLEGAL";
    /**
     *参数异常
     */
    public final static String INVALID_PARAMETERS = "isv.INVALID_PARAMETERS";
    /**
     *系统错误
     */
    public final static String SYSTEM_ERROR = "isp.SYSTEM_ERROR";
    /**
     *非法手机号
     */
    public final static String MOBILE_NUMBER_ILLEGAL = "isv.MOBILE_NUMBER_ILLEGAL";
    /**
     *手机号码数量超过限制
     */
    public final static String MOBILE_COUNT_OVER_LIMIT = "isv.MOBILE_COUNT_OVER_LIMIT";
    /**
     *模板缺少变量
     */
    public final static String TEMPLATE_MISSING_PARAMETERS = "isv.TEMPLATE_MISSING_PARAMETERS";
    /**
     *业务限流
     */
    public final static String BUSINESS_LIMIT_CONTROL = "isv.BUSINESS_LIMIT_CONTROL";
    /**
     *JSON参数不合法，只接受字符串值
     */
    public final static String INVALID_JSON_PARAM = "isv.INVALID_JSON_PARAM";
    /**
     *黑名单管控
     */
    public final static String BLACK_KEY_CONTROL_LIMIT = "isv.BLACK_KEY_CONTROL_LIMIT";
    /**
     *参数超出长度限制
     */
    public final static String PARAM_LENGTH_LIMIT = "isv.PARAM_LENGTH_LIMIT";
    /**
     *不支持URL
     */
    public final static String PARAM_NOT_SUPPORT_URL = "isv.PARAM_NOT_SUPPORT_URL";
    /**
     *账户余额不足
     */
    public final static String AMOUNT_NOT_ENOUGH = "isv.AMOUNT_NOT_ENOUGH";
}
