package com.fulan.api.system.Vo;

import lombok.Data;

@Data
public class CatagoryVo {
	public CatagoryVo(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	private int id;
	private String name;
}
