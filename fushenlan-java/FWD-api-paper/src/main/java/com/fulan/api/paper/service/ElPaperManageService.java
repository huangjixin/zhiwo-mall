package com.fulan.api.paper.service;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fulan.api.paper.domain.el.Paper;
import com.fulan.api.paper.domain.el.PaperQuestion;
import com.fulan.api.paper.domain.el.UserExam;
import com.fulan.api.paper.vo.ElPaperVo;
import com.fulan.api.paper.vo.PaperManageMinVo;
import com.fulan.api.paper.vo.PaperPlanNameVo;
import com.fulan.api.paper.vo.PaperQuestionVo;
import com.fulan.api.paper.vo.QuestionVo;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

@FeignClient(name = "paper")
public interface ElPaperManageService {
	
	/**
	 * manage -- 试卷保存
	 * 接收Paper
	 */
	@PostMapping("/manage/elPaper/save")
	Response<String> saveForManage(@RequestBody PaperQuestionVo paperQuestionVo);
	
	/**
	 * manage -- 试卷根据对象查询
	 * 接收Paper
	 */
	@PostMapping("/manage/elPaper/listObject")
	Response<List<Paper>> listObject(@RequestBody Paper paper);
	
	/**
	 *  manage -- 根据id删除试卷
	 */
	@PostMapping("/manage/elPaper/delete")
	Response<String> deleteForManage(@RequestParam("id") Long id);
	
	/**
	 *  manage -- 批量删除试卷
	 */
	@PostMapping("/manage/elPaper/deleteBatchIds")
	Response<String> deleteBatchIdsForManage(@RequestParam("ids") String ids);

	/**
	 *  manage -- 试卷更新
	 */
	@PostMapping("/manage/elPaper/update")
	Response<String> updateForManage(@RequestBody PaperQuestionVo paperQuestionVo );
	
	/**
	 *  manage -- 查询试卷列表
	 */
	@GetMapping("/manage/elPaper/list")
	PageInfo<ElPaperVo> listForManage(@RequestParam(value="type",required=false) String type,
            @RequestParam(value="pubType",required=false) String pubType,
            @RequestParam(value="submitter",required=false) String submitter,
            @RequestParam(value="createUser",required=false) String createUser,
            @RequestParam(value="name",required=false) String name,
            @RequestParam(value="groupId",required=false) String groupId,
            @RequestParam(value="tagId",required=false) String tagId,
            @RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="10") int pageSize);
	
	@GetMapping("/manage/elPaper/waitForReview")
	PageInfo<ElPaperVo> waitForReviewForManage(
			@RequestParam(value="name",required=false) String name,
			@RequestParam(value="paperState",required=false) String paperState,
			@RequestParam(value="userName",required=false) String userName,
			@RequestParam(value="groupId",required=false) String groupId,
			@RequestParam(value="tagId",required=false) String tagId,
			@RequestParam(value="pageNo",defaultValue="1") int pageNo,
			@RequestParam(value="pageSize",defaultValue="10") int pageSize);
	
	/**
	 *  manage -- 根据Id查询试卷
	 */
	@PostMapping("/manage/elPaper/seleById")
	Response<Paper> seleByIdForManage(@RequestParam("id") Long id);
	
	/**
	 *  manage -- 根据Id查询试卷
	 */
	@PostMapping("/manage/elPaper/isNotRelation")
	List<Map<String,String>> isNotRelation(@RequestParam("ids") String ids);
	
	/**
	 *  manage -- 根据Id查询试卷及其关联的试卷
	 */
	@PostMapping("/manage/elPaper/questionAndAnswer")
	List<QuestionVo> questionAndAnswer(@RequestParam("id") String id);
	
	/**
	 *  manage -- 保存试卷试题关联关系
	 */
	@PostMapping("/manage/elPaper/savePQ")
	Response<String> savePQ(@RequestParam("paperQuestion") List<PaperQuestion> paperQuestion);
	
	/**
	 *  manage -- 删除试卷试题关联关系
	 */
	@PostMapping("/manage/elPaper/delectPQ")
	Response<String> delectPQ(@RequestParam("paperId") String paperId,@RequestParam("questionId") String questionId);
	
	/**
	 *  manage -- 删除试卷试题关联关系
	 */
	@PostMapping("/manage/elPaper/delectBatchPQIds")
	Response<String> delectBatchPQIds(@RequestParam("ids") String ids);
	
	/**
	 *  manage -- 带阅卷查看
	 */
	@PostMapping("/manage/elPaper/seleByIdVo")
	List<ElPaperVo> seleByIdVo(@RequestParam("id") String id);
	
	/**
	 * manage -- 保存UserExam
	 * 
	 */
	@PostMapping("/manage/elPaper/saveUEU")
	Response<String> saveByIdUEU(@RequestBody List<UserExam> userExam);
	
	
	@GetMapping("/manage/elPaper/selectByAllPaper")
	List<Paper> selectAllList();
	
	@PostMapping("/manage/elPaper/seleByIdPublic")
	List<ElPaperVo> seleByIdPublic(@RequestParam("id") String id);
	
	
	@GetMapping(value="/manage/elPaper/selectByCId")
    List<PaperManageMinVo> selectBycId(@RequestParam("cId") String cId);
	
	@GetMapping(value="/manage/elPaper/seleByIdWaitFor")
    ElPaperVo seleByIdWaitFor(@RequestParam("id") String id,
    		@RequestParam(value="userId") String userId,
			@RequestParam(value="examNum") String examNum,
			@RequestParam(value="planCourseId") String planCourseId);
	
	@GetMapping("/manage/elPaper/seleByGroupId")
	Response<List<Paper>> seleByGroupId(@RequestParam(value="submitter",required=false) Integer submitter ,
			@RequestParam(value = "name" , required = false) String name,
			@RequestParam(value = "type" , required = false) Integer type);

	@PostMapping("/manage/elPaper/selectPlanName")
	List<PaperPlanNameVo> selectPlanName(@RequestParam("ids") String ids);
	
	
	@PostMapping(value = "/manage/elPaper/sharePaper")
	Response<Boolean> doSharePapers(@RequestParam(value="groupIds")String[] groupIds, 
			 @RequestParam(value="paperIds")String[] paperIds);
}
