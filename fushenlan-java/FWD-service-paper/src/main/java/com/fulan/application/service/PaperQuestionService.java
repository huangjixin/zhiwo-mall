package com.fulan.application.service;

import java.util.List;

import com.fulan.api.paper.domain.el.PaperQuestion;
import com.fulan.api.paper.vo.ExamPaperVo;
import com.fulan.api.paper.vo.QuestionDto;
import com.fulan.application.util.domain.Response;

public interface PaperQuestionService {
	
	public Response<String> delectBatchIds(String ids);
	
	public Response<String> save(List<PaperQuestion> paperQuestion);
	
	public Response<String> delect(String paperId,String questionId);

}
