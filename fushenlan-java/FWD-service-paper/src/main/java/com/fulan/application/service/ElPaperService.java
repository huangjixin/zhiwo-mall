package com.fulan.application.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.paper.domain.el.Paper;
import com.fulan.api.paper.domain.el.PaperQuestion;
import com.fulan.api.paper.domain.el.UserExam;
import com.fulan.api.paper.vo.ElPaperVo;
import com.fulan.api.paper.vo.PaperManageMinVo;
import com.fulan.api.paper.vo.PaperPlanNameVo;
import com.fulan.api.paper.vo.PaperQuestionVo;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

public interface ElPaperService {
	
	/**
	 * manage -- 试卷保存
	 * 接收Paper
	 */
	Response<String> saveForManage(PaperQuestionVo paperQuestionVo);
	
	/**
	 *  manage -- 根据id删除试卷
	 */
	Response<String> deleteForManage(Long id);
	
	/**
	 *  manage -- 批量删除试卷
	 */
	Response<String> deleteBatchIdsForManage(String ids);

	/**
	 *  manage -- 试卷更新
	 */
	Response<String> updateForManage(PaperQuestionVo paperQuestionVo);
	
	/**
	 *  manage -- 查询试卷列表
	 */
	PageInfo<ElPaperVo> listForManage(Page<ElPaperVo> page,String type,String pubType,String submitter, String createUser,String name,
			  String groupId, String tagId,
			  int pageNo, int pageSize);
	
	/**
	 *  manage -- 根据Id查询试卷
	 */
	Response<Paper> seleByIdForManage(Long id);
	/**
	 * 试卷状态
	 * @param page
	 * @param name
	 * @param paperState
	 * @param userName
	 * @param groupId
	 * @param tagId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	PageInfo<ElPaperVo> waitForReview(Page<ElPaperVo> page, String name,String paperState, String userName,
			String groupId, String tagId,
			int pageNo, int pageSize);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	List<ElPaperVo> seleByIdVo(String id);
	
	/**
	 * manage -- 保存UserExam
	 * 
	 */
	Response<String> saveUEU(List<UserExam> userExam);
	
	/**
	 * manage -- 根据对象查询
	 * 
	 */
	Response<List<Paper>> listObject(Paper paper);
	
	List<Paper> paperList();
	
	List<ElPaperVo> seleByIdPublic(String id);
	
	ElPaperVo seleByIdWaitFor(String id,String userId,String examNum,String planCourseId );
	
	List<PaperManageMinVo> seleBycId (String cId);
	
	Response<List<Paper>> seleByGroupId(Integer submitter,String name,Integer type);

	List<PaperPlanNameVo> selectPlanName(String ids);
	// 批量分享/单个分享 
    boolean sharePaper(String[] groupIds, String[] paperIds);

}
