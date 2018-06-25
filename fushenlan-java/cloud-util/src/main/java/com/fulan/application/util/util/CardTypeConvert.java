/**
 * 
 */
package com.fulan.application.util.util;

/**
 * @description: 
 * @author: shenzhongwu
 * @date:2018年4月19日
 */
public class CardTypeConvert {
	public static int transformPositive(String index){
		int result=0;
		switch(index){
			case "111" : 
				result =1;
				break;
			case "414" : 
				result =2;
				break;
			case "511" : 
				result =17;
				break;
			case "516" : 
				result =18;
				break;
		}
		return result;
	}
	
	public static int transformBack(String index){
		int result=0;
		switch(index){
			case "111" : 
				result =2;
				break;
			case "414" : 
				result =2;
				break;
			case "511" : 
				result =2;
				break;
			case "516" : 
				result =19;
				break;
		}
		return result;
	}
}
