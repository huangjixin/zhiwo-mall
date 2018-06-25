package com.fulan.application.service;


import com.fulan.api.paper.domain.el.QuestionAnswer;
import com.fulan.application.util.domain.Response;

public interface QuestionAnswerService {
	
	/**
	 * 试题答案保存
	 * 接收Question
	 */
	Response<String> saveForManage(QuestionAnswer questionAnswer);
	
	/**
	 * 根据id删除试题答案
	 */
	Response<String> deleteForManage(Long id);
	
	/**
	 * 批量删除试题答案
	 */
	Response<String> deleteBatchIdsForManage(String ids);

	/**
	 * 试题答案更新
	 */
	Response<String> updateForManage(QuestionAnswer questionAnswer);
	
	/**
	 * 查询试题答案列表
	 */
	/*PageInfo<Question> listForManage(Page<Question> page,String type,String pubType,String submitter, String createUser,String content,
			  String groupId, String tagId,
			  int pageNo, int pageSize);*/
	
	/**
	 * 根据Id查询试题答案
	 */
	Response<QuestionAnswer> seleByIdForManage(Long id);
	
	/**
	 * 答案记录数
	 */
	Response<Integer> seleCountForManage(QuestionAnswer questionAnswer);

}
