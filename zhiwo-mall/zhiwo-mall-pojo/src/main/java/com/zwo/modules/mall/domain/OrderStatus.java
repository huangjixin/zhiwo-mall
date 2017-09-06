package com.zwo.modules.mall.domain;

public class OrderStatus {
	/**
	 * 待付款
	 */
	public static final String TO_BE_PAYED = "toBePayed"; 
	/**
	 * 待成团
	 */
	public static final String TO_BE_GROUPED = "toBeGrouped"; 
	/**
	 * 待发货(已付款)
	 */
	public static final String TO_BE_SENT_PAYED = "toBeSentPayed"; 
	/**
	 * 待收货(已付款)
	 */
	public static final String TO_BE_RECIVED_PAYED = "toBeRecivedPayed"; 
	/**
	 * 待评价(已付款)
	 */
	public static final String TO_BE_COMMENT_PAYED = "toBeCommentPayed"; 
	
	/**
	 * 待发货(未付款)
	 */
	public static final String TO_BE_SENT_UNPAYED = "toBeSentUnpayed"; 
	/**
	 * 待收货(未付款)
	 */
	public static final String TO_BE_RECIVED_UNPAYED = "toBeRecivedUnpayed"; 
	/**
	 * 待评价(未付款)
	 */
	public static final String TO_BE_COMMENT_UNPAYED = "toBeCommentUnpayed"; 
	
	
}
