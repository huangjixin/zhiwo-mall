package com.fulan.api.paper.service;


import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.paper.domain.el.Question;
import com.fulan.api.paper.vo.QuestionVo;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

@FeignClient(name = "paper")
public interface QuestionService {
	
	/**
	 * 试题保存
	 * 接收Question
	 */
	@PostMapping("/manage/question/save")
	Response<String> saveForManage(@RequestBody QuestionVo questionVo);
	
	/**
	 * 根据id删除试题
	 */
	@PostMapping("/manage/question/delete")
	Response<String> deleteByIdForManage(@RequestParam("id") Long id);
	
	/**
	 * 批量删除试题
	 */
	@PostMapping("/manage/question/deleteBatchIds")
	Response<String> deleteBatchIdsForManage(@RequestParam("ids") String ids);
	
	/**
	 * 试题更新
	 */
	@PostMapping("/manage/question/update")
	Response<String> updateForManage(@RequestBody QuestionVo questionVo);
	
	/**
	 * 查询试题列表
	 */
	@GetMapping("/manage/question/list")
	PageInfo<Question> listForManage(
			@RequestParam(value="type",required=false) String type,
            @RequestParam(value="pubType",required=false) String pubType,
            @RequestParam(value="submitter",required=false) String submitter,
            @RequestParam(value="createUser",required=false) String createUser,
            @RequestParam(value="content",required=false) String content,
            @RequestParam(value="groupId",required=false) String groupId,
            @RequestParam(value="tagId",required=false) String tagId,
            @RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="10") int pageSize);
	
	@GetMapping("/manage/question/listOther")
	List<Question> listForOtherManage(
			@RequestParam(value="type",required=false) String type,
			@RequestParam(value="tagId",required=false) String tagId,
			@RequestParam(value="keyWord",required=false)String keyWord,
			@RequestParam(value="createUser",required=false)String createUser);
	
	/**
	 * 根据Id查询试题
	 */
	@PostMapping("/manage/question/seleById")
	Response<Question> seleByIdForManage(@RequestParam("id") Long id);
	
	/**
	 * 根据Id查询试题
	 */
	@PostMapping("/manage/question/questionAndAnswer")
	QuestionVo questionAndAnswer(@RequestParam("id") String id);
	
	/**
	 * 记录数
	 */
	@PostMapping("/manage/question/seleCount")
	Response<String> seleCountForManage(@RequestBody Question question);
	
	/**
	 * 随机选题
	 * Map 键type 
	 *     键tagId 
	 *     键questionNum 
	 */
	@PostMapping("/manage/question/seleRandom")
	Response<List<Question>> seleRandomForManage(@RequestBody List<Map<String,Object>> mapList);
	
	
	/**
	 * 查询是否关联试卷
	 */
	@PostMapping("/manage/question/isNotRelation")
	List<Map<String,String>> isNotRelation(@RequestParam("ids") String ids);
	
	/**
	 * 根据Id集合 查询试题集合
	 */
	@PostMapping("/manage/question/seleByIdList")
	List<QuestionVo> seleByIdList(@RequestParam("idList") List<Long> idList);

	@PostMapping("/manage/question/selectPaperName")
	List<String> selectPaperName(@RequestParam("ids") String ids);
	
	@PostMapping(value = "/manage/question/shareQuestion")
	Response<Boolean> shareQuestion(@RequestParam(value="groupIds")String[] groupIds, 
			 @RequestParam(value="questionIds")String[] questionIds);
	
}
