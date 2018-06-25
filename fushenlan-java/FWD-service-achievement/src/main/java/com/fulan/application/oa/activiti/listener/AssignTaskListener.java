package com.fulan.application.oa.activiti.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fulan.application.oa.constant.ApplyConstant;
import com.google.gson.JsonObject;

public class AssignTaskListener implements TaskListener{
	
	public void notify(DelegateTask delegateTask) {
		
		String paramNextAssignee = delegateTask.getVariable(ApplyConstant.PARAM_NEXT_ASSIGNEE, String.class);
		if(StringUtils.isNotBlank(paramNextAssignee)) {
			delegateTask.setAssignee(paramNextAssignee);
			delegateTask.removeVariable(ApplyConstant.PARAM_NEXT_ASSIGNEE);
			return;
		}
		
		String delegateTaskAssignee = delegateTask.getAssignee();
		if(StringUtils.isEmpty(delegateTaskAssignee))
			return;
		
		
		JSONObject jsonObject = this.parseJson(delegateTaskAssignee);
		if(jsonObject!=null) {
			String assigneeId = "";
			String user = jsonObject.getString("user");
			String role = jsonObject.getString("role");
			if(StringUtils.isNotBlank(user)) {
				assigneeId = user;
			}else if(StringUtils.isNotBlank(role)) {
				assigneeId= getAssigneeByRole(role);
			}
			
			delegateTask.setAssignee(assigneeId);
		}
		
	}
	
	private String getAssigneeByRelationship(String typeValue) {
		return "";
	}
	
	private JSONObject parseJson(String s) {
		JSONObject ret = null;
		try {
			ret = JSONObject.parseObject(s);
		}catch(Exception e) {
			return null;
		}
		return ret;
	}
	
	private String getAssigneeByRole(String role) {
		//TODO get submitter from param
		int agentCode = 1000 + new Random().nextInt(999);
		return role+":"+agentCode;
	}
}
