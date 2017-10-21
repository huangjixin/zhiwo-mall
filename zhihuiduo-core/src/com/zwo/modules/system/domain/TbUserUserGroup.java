package com.zwo.modules.system.domain;

import java.io.Serializable;

public class TbUserUserGroup extends TbUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1285405359389121435L;
	/**
	 * 组名
	 */
	private String groupName;
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}