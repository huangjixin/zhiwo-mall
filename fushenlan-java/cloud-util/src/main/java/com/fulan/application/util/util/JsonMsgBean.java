package com.fulan.application.util.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * 公共json响应bean
 * 
 */
@JsonInclude(Include.ALWAYS)
public class JsonMsgBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final  String SUCCESS_CODE = "000";
	public static final  String FAIL_CODE = "100";
	private String code;
	private String message;
	private Object data; // 返回数据对象

	public JsonMsgBean(){
		setData(null);
	}
	public JsonMsgBean(String message, String code, Object data) {
		super();
		this.message = message;
		this.code = code;
		setData(data);
	}
	public JsonMsgBean initSuccess() {
		this.code = SUCCESS_CODE;
		return this;
	}

	public JsonMsgBean initSuccess(Object data) {
		this.code = SUCCESS_CODE;
		setData(data);
		return this;
	}

	public JsonMsgBean initSuccess(String message,Object data) {
		this.code = SUCCESS_CODE;
		this.message  = message;
		setData(data);
		return this;
	}



	public JsonMsgBean initFailure() {
		this.code = FAIL_CODE;
		return this;
	}

	public JsonMsgBean initFailure(String messagee) {
		this.code = FAIL_CODE;
		this.message = messagee;
		setData(null);
		return this;
	}

	public JsonMsgBean initFailure(String message,Object data) {
		this.code = FAIL_CODE;
		this.message = message;
		setData(data);
		return this;
	}


	@Override
	public String toString() {
		return "JsonMsgBean{" +
				"code='" + code + '\'' +
				", message='" + message + '\'' +
				", data=" + data +
				'}';
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		if(data!=null) {
			this.data = data;
		}else if(data == null){
			this.data = new ArrayList<>();
		}
	}



	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString().replace("-", "").length());
	}

}
