package com.fulan.api.paper.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.paper.domain.el.Question;
import com.fulan.api.paper.domain.el.QuestionAnswer;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

@FeignClient(name = "paper")
public interface QuestionAnswerService {
	
	/**
	 * 试题保存
	 * 接收Question
	 */
	@PostMapping("/manage/questionAnswer/save")
	Response<String> saveForManage(@RequestBody QuestionAnswer questionAnswer);
	
	/**
	 * 根据id删除试题
	 */
	@PostMapping("/manage/questionAnswer/delete")
	Response<String> deleteByIdForManage(@RequestParam("id") Long id);
	
	/**
	 * 批量删除试题
	 */
	@PostMapping("/manage/questionAnswer/deleteBatchIds")
	Response<String> deleteBatchIdsForManage(@RequestParam("ids") String ids);
	
	/**
	 * 试题更新
	 */
	@PostMapping("/manage/questionAnswer/update")
	Response<String> updateForManage(@RequestBody QuestionAnswer questionAnswer);
	
	/**
	 * 查询试题列表
	 */
	@PostMapping("/manage/questionAnswer/list")
	PageInfo<QuestionAnswer> listForManage(
			@RequestParam(value="type",required=false) String type,
            @RequestParam(value="pubType",required=false) String pubType,
            @RequestParam(value="submitter",required=false) String submitter,
            @RequestParam(value="createUser",required=false) String createUser,
            @RequestParam(value="content",required=false) String content,
            @RequestParam(value="groupId",required=false) String groupId,
            @RequestParam(value="tagId",required=false) String tagId,
            @RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="10") int pageSize);
	
	/**
	 * 根据Id查询试题
	 */
	@PostMapping("/manage/questionAnswer/seleById")
	Response<QuestionAnswer> seleByIdForManage(@RequestParam("id") Long id);
	
	/**
	 * 记录数
	 */
	@PostMapping("/manage/questionAnswer/seleCount")
	Response<String> seleCountForManage(@RequestBody QuestionAnswer questionAnswer);

}
