package com.zwo.modules.core.vo;

import java.util.List;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class Response {

	private String code;
	private String message;
	private Object data;
	private List<Object> dataList;
}
