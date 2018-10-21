package com.zwo.modules.core.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @Description: 退款原因
 * @author xieyc
 * @date 2018年10月10日 上午10:44:06 
 *
 */
public enum RefundReasonEnum {
	GOODS_IS_BAD(1,"坏果/烂果"),
	GOODS_QUALITY_PROBLEM(2,"质量有问题"),
	GOODS_DESC_WRONG(3,"所收货物与描述不符"),
	GOODS_WRONG_ITMES(4,"发错货");
	private Integer code;
	private String reason;
    
	private RefundReasonEnum(Integer code, String reason) {
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
	public static List<Map<String,Object>> getRefundReasonList(){
		List<Map<String,Object>> list = new ArrayList<>();
		Map<String,Object> mapGoodsBad = new HashMap<>();
		mapGoodsBad.put("code",GOODS_IS_BAD.getCode());
		mapGoodsBad.put("reason", GOODS_IS_BAD.getReason());
		
		Map<String,Object> mapQualityProblem= new HashMap<>();
		mapQualityProblem.put("code", GOODS_QUALITY_PROBLEM.getCode());
		mapQualityProblem.put("reason", GOODS_QUALITY_PROBLEM.getReason());
		
		Map<String,Object> mapDescWrong = new HashMap<>();
		mapDescWrong.put("code", GOODS_DESC_WRONG.getCode());
		mapDescWrong.put("reason", GOODS_DESC_WRONG.getReason());
		
		Map<String,Object> mapWrongItmes = new HashMap<>();
		mapWrongItmes.put("code", GOODS_WRONG_ITMES.getCode());
		mapWrongItmes.put("reason", GOODS_WRONG_ITMES.getReason());
		

		list.add(mapGoodsBad);
		list.add(mapQualityProblem);
		list.add(mapDescWrong);
		list.add(mapWrongItmes);
		return list;
	}	
	
}

	
