package com.fulan.application.orm.tenant;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fulan.application.util.clazz.AnnotationClassLoader;
import com.fulan.application.util.clazz.ClassUtil;

public class TenantEntityHolder {
	
	private static Logger log = LoggerFactory.getLogger(TenantEntityHolder.class);
	private final static Map<String,TenantType> entityTenantTypes = new  HashMap<String,TenantType>(32);
   
	public static void scan(String[] packageName){
		AnnotationClassLoader acl = new AnnotationClassLoader(packageName,null);
		Set<Class<?>> classes = null;
		try {
			classes = acl.getClassSet();
			for (Class<?> clz : classes) {
				String table = clz.getSimpleName().toLowerCase();
				
				if(ClassUtil.existMethod(clz, "getCollegeId"))
					entityTenantTypes.put(table, TenantType.COLLEGE);
				else if(ClassUtil.existMethod(clz, "getCompanyId"))
					entityTenantTypes.put(table, TenantType.COMPANY);
			}
		} catch (Exception e) {
			log.error("扫描实体类过程中出现错误:" + e.getMessage());
			throw new RuntimeException("扫描实体类过程中出现错误:" + e.getMessage());
		}
	}
	
	public static TenantType getTenantType(String table) {
		return entityTenantTypes.get(table);
	}
}
