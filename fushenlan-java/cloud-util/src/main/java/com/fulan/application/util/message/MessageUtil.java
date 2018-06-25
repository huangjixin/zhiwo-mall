package com.fulan.application.util.message;
/**
 * 用户操作生成对应提示信息
 * */
public class MessageUtil {
	public static String getContent(int pre,String userName,int suf){
		String content = null;
		content = getPreState(pre)+userName;
		if(pre == 1){
			content = content+getSufState(suf);
		}else{
			content = content+getSufStateAction(suf);
		}
		return content;
	}
	
	public static String getSufState(int index){
		String result=null;
		switch(index){
			case 2 : 
				result ="的初审面试资料";
				break;
			case 3 : 
				result ="的甄选试卷资料";
				break;
			case 4 : 
				result ="的决定试卷资料";
				break;
		}
		return result;
	}
	public static String getSufStateAction(int index){
		String result=null;
		switch(index){
			case 3 : 
				result ="已通过初审面试";
				break;
			case 4 : 
				result ="已通过甄选面试";
				break;
			case 5 : 
				result ="已通过决定面试";
				break;
			case 6 : 
				result ="已报名课程";
				break;
			case 7 : 
				result ="已考试通过";
				break;
			case 8 : 
				result ="已完成签约。";
				break;
			
		}
		return result;
	}
	
	public static String getPreState(int index){
		String result=null;
		switch(index){
			case 1 : 
				result ="收到";
				break;
			case 2 : 
				result ="您推荐的";
				break;
		}
		return result;
	}
}
