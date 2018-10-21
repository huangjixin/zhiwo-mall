package com.zwo.modules.core.utils;

import org.apache.commons.lang3.StringUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @Description: 订单号生成
 * @author xieyc
 * @date 2018年9月29日 下午5:07:00 
 *
 */
public class IDUtils {
	private static String orderNoPre="6666";
	public static String getOrderNo(){
		DateFormat df = new SimpleDateFormat("yyMMddHHmmssSSS");
		int rand = (int)(Math.random()*100);
		String randStr="";
		if(rand<10){
			randStr = "0"+rand;
		}else{
			randStr = rand+"";
		}
		String oid = orderNoPre+df.format(new Date())+randStr;
		return oid;
	}
	public static String getOrderNo(String inOrderNoPre){
		if(StringUtils.isEmpty(inOrderNoPre)){
			inOrderNoPre = orderNoPre;
		}
		DateFormat df = new SimpleDateFormat("yyMMddHHmmssSSS");
		int rand = (int)(Math.random()*10);
		String randStr=""+rand;
		String oid = inOrderNoPre+df.format(new Date())+randStr;
		return oid;
	}
//	public static void main(String[] args) {
//		System.out.println(getOrderNo());
//	}
}

