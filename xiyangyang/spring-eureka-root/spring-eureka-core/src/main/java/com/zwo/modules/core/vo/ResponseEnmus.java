package com.zwo.modules.core.vo;

public enum ResponseEnmus {

	SUCCESS("200","操作成功"),
	NO_REGISTER("201","用户未注册"),
	FAIL("400","操作失败"),
	DELETE_SUCCESS("200", "删除成功"),
	DELETE_FAIL("400", "删除失败"),
	VISIT_FAIL("500", "操作失败"),
	WX_LOGIN_FAIL("100", "微信授权信息不能为空"),
	GOODS_IS_DOWN("666","该商品已下架"),
	LOCATION_FAIL("500","定位失败"),
	DELETE_SUBCATEGORY("400","请先删除子分类"),
	DELETE_BATCH_FAIL("400", "删除失败，请先删除子类");

	
	ResponseEnmus(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	private String code;
	private String message;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
