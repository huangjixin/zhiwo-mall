package com.zwo.modules.core.vo;

public enum PayAndFlowEnmu {
	
	/**
	 * 第三方支付流水类型:0-vip充值 
	 */
	VIP_PAY("充值vip",0),
	/**
	 * 第三方支付流水类型:1-订单支付
	 */
	ORDER_PAY("支付订单",1),
	/**
	 * 第三方支付流水类型:2-其他
	 */
	OTHOR_PAY("支付其他",2),
	
	/**
	 * 第三方支付流水支付方式:0-微信 
	 */
	PAY_WX("微信支付",0),
	/**
	 * 第三方支付流水支付方式:1-支付宝
	 */
	PAY_ALI("支付宝支付",1),
	/**
	 * 第三方支付流水支付方式: 2-其他
	 */
	PAY_OTHER("其他支付",2),
	
	/**
	 * 第三方支付流水 状态:0-未支付 
	 */
	STATUS_NOT_PAY("未支付",0),
	/**
	 * 第三方支付流水 状态: 1-已支付 
	 */
	STATUS_SUCCUSS_PAY("已支付",1),
	/**
	 * 第三方支付流水 状态: 2-已完成 
	 */
	STATUS_FINISH_PAY("已完成",2),
	/**
	 * 第三方支付流水 状态: -1-支付失败
	 */
	STATUS_FAIL_PAY("支付失败",-1),
	
	/**
	 * 流水类型:0-收入
	 */
	FLOW_TYPR_IN("收入",0),
	/**
	 * 流水类型:1-支出
	 */
	FLOW_TYPR_OUT("支出",1),
	
	/**
	 * 钱包交易类型:0-订单支付
	 */
	WLT_TRADE_TYPE_ORDER("订单支付",0),
	/**
	 * 钱包交易类型:1-充值
	 */
	WLT_TRADE_TYPE_RECHARGE("充值",1),
	/**
	 * 钱包交易类型:2-提现
	 */
	WLT_TRADE_TYPE_WITHDRAWAL("提现",2),
	/**
	 * 钱包交易类型:3-收款
	 */
	WLT_TRADE_TYPE_GATHERING("收款",3),
	/**
	 * 钱包交易类型:4-转账
	 */
	WLT_TRADE_TYPE_TRANSFER("转账",4),
	/**
	 * 钱包交易类型:5-vip充值
	 */
	WLT_TRADE_TYPE_VIP("充值vip",5),
	
	/**
	 * 签到收益交易类型 -1-签到
	 */
	CHECK_TRADE_TYPE_CHECK("签到",-1),
	/**
	 * 签到收益交易类型 -2-提现 
	 */
	CHECK_TRADE_TYPE_WITHDRAWAL("提现",-2),
	/**
	 * 签到收益交易类型 -3-转账
	 */
	CHECK_TRADE_TYPE_TRANSFER("转账",-3)
	;
	
	private String name;
	private Integer code;
	
	PayAndFlowEnmu(String name,Integer code) {
		this.name = name;
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	
	
}
