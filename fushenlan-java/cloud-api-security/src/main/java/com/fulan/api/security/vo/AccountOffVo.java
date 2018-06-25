package com.fulan.api.security.vo;

import java.util.Date;

import lombok.Data;

@Data
public class AccountOffVo {
	
	/*
	 * 职级
	 */
	private Long id;
	
	/*
	 * 用户名
	 */
	private String accountName;
	
	/*
	 * 职级
	 */
	private Long postType;
	
	/*
	 * 机构名称
	 */
	private String branchName;
	
	/*
	 * 所属省
	 */
	private String branchProvince;
	
	/*
	 * 报名时间
	 */
	private Date enterTime;
	
}
