package com.fulan.application.service.impl;

import java.util.*;

import com.fulan.api.paper.domain.el.QuestionAnswer;
import com.fulan.api.paper.domain.el.UserExam;
import com.fulan.api.paper.vo.ExamPaperVo;
import com.fulan.api.paper.vo.QuestionDto;
import com.fulan.api.paper.vo.QuestionVo;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.mapper.PaperQuestionMapper;
import com.fulan.application.mapper.UserExamMapper;
import com.fulan.application.orm.id.IdGenerator;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.fulan.api.paper.domain.el.PaperQuestion;
import com.fulan.api.paper.domain.el.Question;
import com.fulan.application.mapper.QuestionMapper;
import com.fulan.application.service.PaperQuestionService;
import com.fulan.application.util.domain.Response;

@Service
public class PaperQuestionServiceImpl implements PaperQuestionService {

	private Logger logger = LoggerFactory.getLogger(PaperQuestionServiceImpl.class);

	@Autowired
	private PaperQuestionMapper paperQuestionMapper;

	@Autowired
	private UserExamMapper userExamMapper;

	@Autowired
	private IdGenerator idGenerator;

	@Autowired
	private QuestionMapper questionMapper;

	@Override
	@Transactional
	public Response<String> delectBatchIds(String ids) {
		Response<String>  response = new Response<>(Response.SUCCESS,"删除数据成功");
		List<Long> idList = new ArrayList<>();
		String[] idArr = ids.split(",");
		for (String id : idArr) {
			idList.add(Long.valueOf(id));
		}
		int count = paperQuestionMapper.deleBatchHostId(idList);
		if(count>0){
			return response;
		}
		response.setCode(Response.ERROR);
		response.setMsg("删除数据失败");
		return response;
	}

	@Override
	@Transactional
	public Response<String> save(List<PaperQuestion> paperQuestion) {
		Response<String>  response = new Response<>(Response.SUCCESS,"新增数据成功");
		int count = 0;
		for(PaperQuestion pq : paperQuestion){
			if(null != pq.getPaperId() && null != pq.getQuestionId() && null != pq.getQuestionScore()){ 
				EntityWrapper<PaperQuestion> wrapper = new EntityWrapper<>(pq);
				List<PaperQuestion> listPQ = paperQuestionMapper.selectList(wrapper);
				if(CollectionUtils.isNotEmpty(listPQ)){
					response.setCode(Response.ERROR);
					Question question = questionMapper.selectById(pq.getQuestionId());
					response.setMsg(question.getContent()+"已关联该试卷");
					return response;
				}
				count = paperQuestionMapper.insert(pq);
			}
		}
		if(count>0){
			return response;
		}
		response.setCode(Response.ERROR);
		response.setMsg("新增数据失败");
		return response;
	}

	@Override
	@Transactional
	public Response<String> delect(String paperId,String questionId) {
		Response<String>  response = new Response<>(Response.SUCCESS,"删除数据成功");
		PaperQuestion paperQuestion = new PaperQuestion();
		int count = 0;
		if(StringUtils.isNotEmpty(paperId)&&StringUtils.isNotEmpty(questionId)){
			paperQuestion.setPaperId(Long.valueOf(paperId));
			paperQuestion.setQuestionId(Long.valueOf(paperId));
		    EntityWrapper<PaperQuestion> wrapper = new EntityWrapper<>(paperQuestion);
		    count= paperQuestionMapper.delete(wrapper);
		} 
		if(count>0){
			return response;
		}
		response.setCode(Response.ERROR);
		response.setMsg("删除数据失败");
		return response;
	}

}
