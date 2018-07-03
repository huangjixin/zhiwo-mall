/**
 * 
 */
package com.fulan.application.oa.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fulan.application.oa.constant.ApplyAction;
import com.fulan.application.oa.domain.FwdOaApplyForm;
import com.fulan.application.oa.vo.OAApplyFormVo;
import com.fulan.application.oa.vo.OAApplyFormVoParameter;

/**
 * @author 黄记新
 * 提供对申请表单的增删改查
 */
public interface IApplyFormService {
	/**
	 * 新增一个申请表单
	 * @param applyForm
	 * @return
	 */
	int save(FwdOaApplyForm applyForm);
	/**
	 * 删除一个申请表单
	 * @param applyForm
	 * @return
	 */
	int delete(int id);
	/**
	 * 修改
	 * @param applyForm
	 * @return
	 */
	int update(FwdOaApplyForm applyForm);
	/**
	 * 查询全部列表
	 * @param applyForm
	 * @return
	 */
	List<FwdOaApplyForm> selectAll();
	
	 /**
     * 传入参数FwdOaApplyForm的实例对象，
     * 判断其里面的type,status等属性，分页查询申请表单（主表），地址（子表），银行卡（子表），手机号（子表）
     * @param record
     * @param start
     * @param size
     * @return
     */
	List<OAApplyFormVo> selectOAApplyFormVo(OAApplyFormVoParameter parameter);
	
	/**
	 * 根据ID查询数据
	 * @param FwdOaApplyForm
	 * @return
	 */
	FwdOaApplyForm selectById(int id);
	
	 /**
	  * 单个文件上传
	  * @return
	  */
	 int saveForm(String strDirPath,MultipartFile file,FwdOaApplyForm applyForm) throws Exception;
	 
	 /**
	  * 多个文件上传
	  * @return
	  */
	 int saveMultipleForm(String strDirPath,CommonsMultipartFile[] files,FwdOaApplyForm applyForm) throws Exception;

	/**
	 * 多个文件上传 base64
	 * @return
	 */
	int saveMultipleFormBase64(String strDirPath,String[] files,FwdOaApplyForm applyForm) throws Exception;
	
	 /**
	  * @Description:id查申请表单
	  * @param formId
	  * @return
	  */
	FwdOaApplyForm findById(Integer formId);
	
	/**
	 * @Description:待我审批
	 * @param agentCode
	 * @param pageSize
	 * @param curPage
	 */
	List<OAApplyFormVo> findPendingApproval(String agentCode, String[] types, Integer pageSize, Integer curPage);
	
	/**
	 * @Description:我已审批
	 * @param agentCode
	 * @param types 
	 * @param pageSize
	 * @param curPage
	 * @return
	 */
	List<OAApplyFormVo> findProcessedApproval(String agentCode, String[] types, Integer pageSize, Integer curPage);
	
	/**
	 * @param nextAssignee 
	 * @Description:处理审批
	 */
	void handleApproval(Integer formId, ApplyAction action,String userId ,String comment, String nextAssignee);
	
	
	boolean isApplyFormActive(Integer formId);
	
	/**
	 * 读取bpmn文件
	 * 
	 * @param name
	 * @return
	 */
    List<Map<String, String>> getTaskInfosByName(String name) throws Exception;
    
    /**
	 * 修改bpmn文件
	 * 
	 * @param name
	 * @return
	 */
    void updateTaskInfosByName(String name,List<Map<String, String>> nodeMap) throws Exception;
}
