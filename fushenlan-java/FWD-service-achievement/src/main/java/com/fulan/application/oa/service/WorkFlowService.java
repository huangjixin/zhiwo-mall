package com.fulan.application.oa.service;

import java.util.List;
import java.util.Map;

import com.fulan.application.oa.constant.ApplyAction;
import com.fulan.application.oa.constant.ApplyTypeEnum;
import com.fulan.application.oa.vo.ApprovalRecordVo;

/**
  * @author Sam Zeng 
  * @date 2018年5月22日 
  * @Description:工作流
 */
public interface WorkFlowService {

	/**
	 * @Description:启动流程
	 * @param applyType 申请类型
	 * @param businessKey  暂时默认为表单id
	 */
	public void startProcessAndSubmitForm(ApplyTypeEnum applyType,String businessKey,String userId,String branchCode,Map<String,Object> params);
	
    /**
     * 我的审批-待我审批
     *
     * @return
     */
	public List<Integer> findPendingApprovalByUserId(String userId, String[] types,int curPage,int pageSize);
	
	/**
     * 我的审批-已审批
     *
     * @return
     */
	public List<Integer> findProcessedApprovalByUserId(String userId, String[] types , int curPage,int pageSize);
	
	/**
	 * 
	 * @Description:处理任务
	 * @param businessKey 默认为表单id
	 * @param userId 处理人
	 * @param action 处理结果
	 * @param comment 评论
	 * @return 流程是否结束
	 */
	public boolean handleTask(String businessKey, ApplyAction action,String userId ,String comment,Map<String,Object> params);
	
	/**
	 * @Description: 查找任务的所有审批评论记录
	 * @param processInstanceId
	 * @return
	 */
	public List<ApprovalRecordVo> findApprovalRecord(String businessKey);

	/**
	 * @Description:是否有权限
	 * @param agentCode
	 * @param formId
	 * @return
	 */
	public boolean hasApprovalAccess(String agentCode, Integer formId);
}
