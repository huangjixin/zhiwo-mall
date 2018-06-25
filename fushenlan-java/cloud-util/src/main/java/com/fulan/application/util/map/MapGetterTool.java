package com.fulan.application.util.map;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.apache.commons.collections.BeanMap;

public class MapGetterTool {

	/**
	 * <p>
	 * 根据Key返回一个String
	 * </p>
	 * 
	 * @param key
	 * @return String
	 */
	public static String getString(String key, Map<?, ?> map) {
		if (map.containsKey(key) && map.get(key) != null) {
			if (map.get(key) instanceof String) {
				return (String) map.get(key);
			} else {
				return null;
			}
		} else {
			return "";
		}
	}

	/**
	 * <p>
	 * 根据Key返回一个Date
	 * </p>
	 * 
	 * @param key
	 * @return Date
	 */
	public static Date getDate(String key, Map<?, ?> map) {
		if (map.containsKey(key) && map.get(key) != null) {
			if (map.get(key) instanceof Date) {
				return (Date) map.get(key);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * <p>
	 * 根据Key返回一个Integer
	 * </p>
	 * 
	 * @param key
	 * @return Integer
	 */
	public static Integer getInteger(String key, Map<?, ?> map) {
		if (map.containsKey(key) && map.get(key) != null) {
			if (map.get(key) instanceof Integer) {
				return (Integer) map.get(key);
			} else {
				return null;
			}
		} else {
			return 0;
		}
	}

	/**
	 * <p>
	 * 根据一个Key返回一个Map<String,String>
	 * </p>
	 * 
	 * @param key
	 * @return Map<String,String>
	 */
	public static Map<String, String> getMap(String key, Map<?, Map<String, String>> map) {
		if (map.containsKey(key) && map.get(key) != null) {
			if (map.get(key) instanceof Map) {
				return (Map<String, String>) map.get(key);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * <p>
	 * 根据key返回BigDecimal 如果为null,则返回 new BigDecimal(0)
	 * </p>
	 * 
	 * @param key
	 * @return BigDecimal
	 */
	public static BigDecimal getBigDecimal(String key, Map<?, ?> map) {
		if (map.containsKey(key)) {
			if (map.get(key) instanceof BigDecimal) {
				return (BigDecimal) map.get(key);
			} else {
				return new BigDecimal(0);
			}
		} else {
			return new BigDecimal(0);
		}
	}

	public static Object mapToObject(Map<String, ?> map, Class<?> beanClass) throws Exception {
		if (map == null)
			return null;
		Object obj = beanClass.newInstance();
		org.apache.commons.beanutils.BeanUtils.populate(obj, map);
		return obj;
	}

	public static Map<?, ?> objectToMap(Object obj) {
		if (obj == null)
			return null;
		return new BeanMap(obj);
	}

}