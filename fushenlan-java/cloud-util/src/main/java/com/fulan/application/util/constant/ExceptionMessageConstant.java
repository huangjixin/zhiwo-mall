/**
 * 
 */
package com.fulan.application.util.constant;

/**
 * @description: 
 * @author: shenzhongwu
 * @date:2018年4月10日
 */
public class ExceptionMessageConstant {
	
	public static String returnMessage(String cause){
		String result = "";
		switch(cause){
			case "ZuulException" : 
				result = "服务暂时不可用，请重试！";
				break;
			default :
				result = "服务不可用，请重试！";
		}
		return result;
	}

}
