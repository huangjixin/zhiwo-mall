package com.zwo.modules.core.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @Description: 退款类型枚举
 * @author xieyc
 * @date 2018年10月10日 上午10:37:04 
 *
 */
public enum RefundTypeEnum {
	REFUND_ALL(1,"全额退款"),
	REFUND_PART(2,"部分退款");
	private Integer code;
	private String reason;
    
	private RefundTypeEnum(Integer code, String reason) {
		this.code = code;
		this.reason = reason;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	public static List<Map<String,Object>> getRefundTypeList(){
		List<Map<String,Object>> list = new ArrayList<>();
		Map<String,Object> mapAll = new HashMap<>();
		mapAll.put("code", REFUND_ALL.getCode());
		mapAll.put("reason", REFUND_ALL.getReason());
		
		Map<String,Object> mapPart = new HashMap<>();
		mapPart.put("code", REFUND_PART.getCode());
		mapPart.put("reason", REFUND_PART.getReason());
		

		list.add(mapAll);
		list.add(mapPart);
		return list;
	}	
}

	
