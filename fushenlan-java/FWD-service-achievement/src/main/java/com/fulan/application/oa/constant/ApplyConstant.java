package com.fulan.application.oa.constant;

/**
  * @author Sam Zeng 
  * @date 2018年5月22日 
  * @Description: OA 流程相关常量
 */
public class ApplyConstant {
	/**
	 * 分公司
	 */
	public static final String BRANCH_CODE_SHANG_HAI = "SH";
	/**
	 * 流程状态
	 */
	public static final Integer APPLY_STATUS_PROGRESS = 0;
	public static final Integer APPLY_STATUS_PASS = 1; 
	public static final Integer APPLY_STATUS_REJECT = 2;

	/**
	 * 流程定义id后缀
	 */
	public static final String PROCESS_DEFINITION_KEY_SUFFIX="Process";
	
	/**
	 * 任务分配类型
	 */
	public static final String ASSIGN_TYPE_USER = "user";
	public static final String ASSIGN_TYPE_ROLE = "role";
	public static final String ASSIGN_TYPE_RELATIONSHIP = "relationship";
	public static final String ASSIGN_TYPE_SUBMIT = "submitter";
	public static final String SEPARATOR = ":";
	
	/**
	 * 流程节点名称
	 */
	public static final String FLOW_NODE_END = "endevent1";
	public static final String FLOW_NODE_START = "startevent1";
	
	/**
	 * 流程参数
	 */
	public static final String PARAM_LAST_UPDATE_DATE = "last_update_date";
	public static final String PARAM_SUBMIT_USER = "submit_user";
	public static final String PARAM_NEXT_ASSIGNEE = "next_assignee";
}
