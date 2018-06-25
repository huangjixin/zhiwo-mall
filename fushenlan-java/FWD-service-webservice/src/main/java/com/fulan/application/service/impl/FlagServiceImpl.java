package com.fulan.application.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fulan.application.service.FlagClient;
import com.fulan.application.service.FlagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlagServiceImpl implements FlagService {

    @Autowired
    FlagClient flagClient;

    Logger logger= LoggerFactory.getLogger(FlagService.class);

    //need to get the agentCode from login
    @Override
    public String searchFlagsByAgentCode( String agentCode) {
        try {
            JSONObject jsonObject=new JSONObject();
           return  flagClient.serachFlags(agentCode);
        }
        catch (Exception e){

            logger.error("erro",e.getCause());
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("code",0);
            jsonObject.put("message","异常:"+e.getMessage());
            jsonObject.put("data","{}");
            return jsonObject.toJSONString();

        }
    }

    @Override
    public String addFlags(String agentCode,String name,String ruleJson) {

        try {
            return flagClient.addFlag(agentCode,name,ruleJson);
        } catch (Exception e) {

            logger.error("erro",e.getCause());
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("code",0);
            jsonObject.put("message","异常:"+e.getMessage());
            jsonObject.put("data","{}");
            return jsonObject.toJSONString();
        }
    }

	@Override
	public String delFlag(String tagId) {
		 try {
	            return flagClient.delFlag(tagId);
	        } catch (Exception e) {

	            logger.error("erro",e.getCause());
	            JSONObject jsonObject=new JSONObject();
	            jsonObject.put("code",0);
	            jsonObject.put("message","异常:"+e.getMessage());
	            jsonObject.put("data","{}");
	            return jsonObject.toJSONString();
	        }
	}
}
