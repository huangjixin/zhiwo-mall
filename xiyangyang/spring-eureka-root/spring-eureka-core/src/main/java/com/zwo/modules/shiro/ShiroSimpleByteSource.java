package com.zwo.modules.shiro;

import java.io.Serializable;

import org.apache.shiro.util.SimpleByteSource;

public class ShiroSimpleByteSource extends SimpleByteSource implements
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ShiroSimpleByteSource(byte[] bytes) {
		super(bytes);
	}


}
