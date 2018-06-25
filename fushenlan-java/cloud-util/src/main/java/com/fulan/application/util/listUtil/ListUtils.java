package com.fulan.application.util.listUtil;

import java.util.List;

public class ListUtils {
	/**
	 * list集合 是否为null 以及 是否为空
	 * @author kang
	 * @param list
	 * @return
	 */
	public  static <T> boolean isEmpty(List<T> list){
		if(null == list){
			return true;
		}
		return list.isEmpty();
	}
}
