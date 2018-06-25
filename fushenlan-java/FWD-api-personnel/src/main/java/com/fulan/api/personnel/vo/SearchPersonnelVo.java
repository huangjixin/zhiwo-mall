/**
 * 
 */
package com.fulan.api.personnel.vo;

import lombok.Data;

/**
 * @description: 
 * @author: shenzhongwu
 * @date:2018年4月26日
 */
@Data
public class SearchPersonnelVo {
	
	private String keyWord;
	
	private int searchType;//1:人才库列表2:增员人才列表3:复核列表
	
	private String orgId;
	
	private int pageNo=1;
	
	private int pageSize=10;

}
