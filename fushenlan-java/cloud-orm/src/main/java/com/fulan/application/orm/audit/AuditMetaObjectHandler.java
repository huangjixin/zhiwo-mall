package com.fulan.application.orm.audit;

import java.sql.Timestamp;

import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.fulan.application.context.ContextHolder;
import com.fulan.application.context.GlobalContext;

@Component
public class AuditMetaObjectHandler extends MetaObjectHandler {
	
   private static Logger log = LoggerFactory.getLogger(AuditMetaObjectHandler.class);
   
   private final static String CREATE_BY_FIELD = "createBy";
   private final static String CREATE_BY_NAME_FIELD = "createByName";
   private final static String CREATE_BY_TIME_FIELD = "createByTime";
   private final static String UPDATE_BY_FIELD = "updateBy";
   private final static String UPDATE_BY_NAME_FIELD = "updateByName";
   private final static String UPDATE_BY_TIME_FIELD = "updateByTime";
 
   @Override
   public void insertFill(MetaObject metaObject) {
    	GlobalContext context = ContextHolder.get();
    	if(context == null)
    		return;
    	
    	if(metaObject.hasGetter(CREATE_BY_FIELD) && context.getAccountId()!=null) {
    		metaObject.setValue(CREATE_BY_FIELD, context.getAccountId());
    		metaObject.setValue(CREATE_BY_NAME_FIELD, context.getAccountName());
    		metaObject.setValue(CREATE_BY_TIME_FIELD, new Timestamp(System.currentTimeMillis()));
    	}
    	
    	if(metaObject.hasGetter(UPDATE_BY_FIELD) && context.getAccountId()!=null) {
    		metaObject.setValue(UPDATE_BY_FIELD, context.getAccountId());
    		metaObject.setValue(UPDATE_BY_NAME_FIELD, context.getAccountName());
    		metaObject.setValue(UPDATE_BY_TIME_FIELD, new Timestamp(System.currentTimeMillis()));
    	}
    	    		
    }

    @Override
    public void updateFill(MetaObject metaObject) {
    	GlobalContext context = ContextHolder.get();
    	if(context == null)
    		return;
    	
    	if(metaObject.hasGetter(UPDATE_BY_FIELD) && context.getAccountId()!=null) {
    		metaObject.setValue(UPDATE_BY_FIELD, context.getAccountId());
    		metaObject.setValue(UPDATE_BY_NAME_FIELD, context.getAccountName());
    		metaObject.setValue(UPDATE_BY_TIME_FIELD, new Timestamp(System.currentTimeMillis()));
    	}
    }
}