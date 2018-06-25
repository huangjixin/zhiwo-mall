package com.fulan.core.monitoring.cat.constant;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.dianping.cat.Cat.Context;

public class RemoteContext implements Context,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1588751197470682101L;
	
	private Map<String,String> properties = new HashMap<String,String>();

	@Override
	public void addProperty(String key, String value) {
		properties.put(key,value);
	}

	@Override
	public String getProperty(String key) {
		return properties.get(key);
	}

}
