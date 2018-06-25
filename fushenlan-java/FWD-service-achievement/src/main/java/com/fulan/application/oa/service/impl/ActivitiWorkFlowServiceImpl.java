package com.fulan.application.oa.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.NativeHistoricProcessInstanceQuery;
import org.activiti.engine.impl.RuntimeServiceImpl;
import org.activiti.engine.impl.cmd.AbstractCustomSqlExecution;
import org.activiti.engine.impl.cmd.CustomSqlExecution;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.NativeProcessInstanceQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fulan.application.context.CommenConstant;
import com.fulan.application.oa.constant.ApplyAction;
import com.fulan.application.oa.constant.ApplyConstant;
import com.fulan.application.oa.constant.ApplyTypeEnum;
import com.fulan.application.oa.mapper.ActivitiMapper;
import com.fulan.application.oa.service.WorkFlowService;
import com.fulan.application.oa.vo.ApprovalRecordVo;


@Service
@Transactional
public class ActivitiWorkFlowServiceImpl implements WorkFlowService{
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private ManagementService managementService;
	@Autowired
	private ActivitiMapper activitiMapper;
	
	@Override
	public void startProcessAndSubmitForm(ApplyTypeEnum applyType,String businessKey,String userId,String branchCode,Map<String,Object> params) {
		
		String applyTypeName = applyType.getName();
		String processDefinitionKey = branchCode + applyTypeName + ApplyConstant.PROCESS_DEFINITION_KEY_SUFFIX;
		
		List<Deployment> deployment = repositoryService.createDeploymentQuery().processDefinitionKey(processDefinitionKey).list();
		if(deployment==null || deployment.size()==0) {
			//TODO
			throw new RuntimeException("此申请流程还没配置");
		}
		
		Map<String,Object> startParams= new HashMap<String,Object>();
		startParams.put(ApplyConstant.PARAM_SUBMIT_USER, userId);
		startParams.put(ApplyConstant.PARAM_LAST_UPDATE_DATE, new Date());
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey,startParams);
		
		if(processInstance!=null) {
			//TODO jump 
			this.handleTask(businessKey, ApplyAction.Agree, null, null, params);
		}
	}


	@Override
	public List<Integer> findProcessedApprovalByUserId(String userId, String[] types, int curPage, int pageSize) {
		int start  = (curPage-1)*pageSize;
		List<Integer> applyList = activitiMapper.selectProcessedApproval(userId, types, start, pageSize);
		return applyList;
	}
	
	@Override
	public List<Integer> findPendingApprovalByUserId(String userId, String[] types, int curPage, int pageSize) {
		int start  = (curPage-1)*pageSize;
		List<Integer> applyList = activitiMapper.selectPendingApproval(userId, types, start, pageSize);
		return applyList;
	}

	private Task getTaskByBusinessKey(String businessKey) {
		return taskService.createTaskQuery()
			.processInstanceBusinessKey(businessKey)
			.singleResult();
	}

	@Override
	public boolean handleTask(String businessKey ,ApplyAction action,String userId, String comment,Map<String,Object> params) {
		
		Task task = taskService.createTaskQuery()
				.processInstanceBusinessKey(businessKey)
				.singleResult();
		
		if(task==null) {
			throw new RuntimeException("找不到对应表单");
		}
		
		String taskId = task.getId();
		String processInstanceId = task.getProcessInstanceId();
		String executionId = task.getExecutionId();
		
		if(params==null)
			params = new HashMap<String,Object>();
		params.put(ApplyConstant.PARAM_LAST_UPDATE_DATE, new Date());
		taskService.setVariables(taskId, params);
		
		if(StringUtils.isNotBlank(comment)) {
			Authentication.setAuthenticatedUserId(userId);
			taskService.addComment(taskId, processInstanceId, comment);
		}
		
		if(action==ApplyAction.Agree) {
			taskService.complete(taskId);
		}else {
			rejectApply(executionId);
		}
		
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstanceId)
				.singleResult();
		return (pi==null);
	}
	
	private void rejectApply(String executionId) {
		((RuntimeServiceImpl)runtimeService).getCommandExecutor().execute(new Command<Object>() {  
			
	         public Object execute(CommandContext commandContext) {  
	        	 
	               ExecutionEntity execution = commandContext
	            		 .getExecutionEntityManager()
	            		 .findExecutionById(executionId);  
	               execution.destroyScope(ApplyAction.Reject.getName()); 
	               
	               ProcessDefinitionImpl processDefinition = execution.getProcessDefinition();  
	               ActivityImpl findActivity = processDefinition.findActivity(ApplyConstant.FLOW_NODE_END);  
	               execution.executeActivity(findActivity);  
	               
	               return execution;  
	           }  
	       
	        });  
	}

	@Override
	public List<ApprovalRecordVo> findApprovalRecord(String businessKey) {
		
		 HistoricProcessInstanceEntity processInstance = (HistoricProcessInstanceEntity) historyService
				.createHistoricProcessInstanceQuery()
				.processInstanceBusinessKey(businessKey)
				.singleResult();
		 
		 if(processInstance==null) {
			 throw new RuntimeException("此表单没有关联流程");
		 }
		 
		String processInstanceId = processInstance.getProcessInstanceId();
		return activitiMapper.selectApprovalRecord(processInstanceId);

	}

	@Override
	public boolean hasApprovalAccess(String agentCode, Integer formId) {
		Task task = getTaskByBusinessKey(formId.toString());
		return (task==null)
				?false
				:agentCode.equals(task.getAssignee());
	}
	
}
