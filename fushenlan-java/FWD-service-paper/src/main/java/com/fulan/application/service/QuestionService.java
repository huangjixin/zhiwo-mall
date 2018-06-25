package com.fulan.application.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.paper.domain.el.Question;
import com.fulan.api.paper.vo.QuestionVo;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

public interface QuestionService {
	
	/**
	 * 试题保存
	 * 接收Question
	 */
	Response<String> saveForManage(QuestionVo questionVo);
	
	/**
	 * 根据id删除试题
	 */
	Response<String> deleteForManage(Long id);
	
	/**
	 * 批量删除试题
	 */
	Response<String> deleteBatchIdsForManage(String ids);
	

	/**
	 * 试题更新
	 */
	Response<String> updateForManage(QuestionVo questionVo);
	
	/**
	 * 查询试题列表
	 */
	PageInfo<Question> listForManage(Page<Question> page,String type,String pubType,String submitter, String createUser,String content,
			  String groupId, String tagId,
			  int pageNo, int pageSize);
	
	
	List<Question> listForOtherManage(String type,String tagId,String keyWord,String createUser);
	
	
	/**
	 * 根据Id查询试题
	 */
	Response<Question> seleByIdForManage(Long id);
	
	/**
	 * 根据Id查询试题
	 */
	Response<List<Question>> seleRandomForManage(List<Map<String,Object>> mapList);
	
	/**
	 * 记录数
	 */
	Response<Integer> seleCountForManage(Question question);
	
	QuestionVo questionAndAnswer(String id);
	
	Response<String> deleById(Long id);
	
	List<Map<String,String>> isNotRelation(String ids);
	
	List<QuestionVo> seleByIdList(List<Long> idList);

	List<String> selectPaperName(String ids);
	
	boolean shareQuestion(String[] groupIds, String[] questionIds);

}
