package com.fulan.application.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.paper.domain.el.QuestionAnswer;
import com.fulan.application.mapper.QuestionAnswerMapper;
import com.fulan.application.service.QuestionAnswerService;
import com.fulan.application.util.domain.Response;

@Service
public class QuestionAnswerServiceImpl extends ServiceImpl<QuestionAnswerMapper, QuestionAnswer> implements QuestionAnswerService {

	@Autowired
	private QuestionAnswerMapper questionAnswerMapper;
	
	/**
	 * 试题答案保存
	 * 接收Question
	 */
	@Override
	@Transactional
	public Response<String> saveForManage(QuestionAnswer  questionAnswer) {
		Response<String> response = new Response<String>(Response.SUCCESS,"试题保存成功");
		int count = questionAnswerMapper.insert(questionAnswer);
		if(count == 0){
			response.setCode(Response.ERROR);
			response.setMsg("试题保存失败");
		}
		return response;
	}
	
	/**
	 * 根据id删除试题答案
	 */
	@Override
	@Transactional
	public Response<String> deleteForManage(Long id) {
		Response<String> response = new Response<String>(Response.SUCCESS,"试题删除成功");
		int count = questionAnswerMapper.deleteById(id);
		if(count == 0){
			response.setCode(Response.ERROR);
			response.setMsg("试题删除失败");
		}
		return response;
	}

	/**
	 * 批量删除试题答案
	 */
	@Override
	@Transactional
	public Response<String> deleteBatchIdsForManage(String ids) {
		Response<String> response = new Response<String>(Response.SUCCESS,"试题删除成功");
		List<Long> idList = new ArrayList<Long>();
		String[] idArr = ids.split(",");
		for(String id : idArr){
			idList.add(Long.valueOf(id));
		}
		int count = questionAnswerMapper.deleteBatchIds(idList);
		if(count == 0){
			response.setCode(Response.ERROR);
			response.setMsg("试题删除失败");
		}
		return response;
	}

	/**
	 * 试题答案更新
	 */
	@Override
	@Transactional
	public Response<String> updateForManage(QuestionAnswer  questionAnswer) {
		Response<String> response = new Response<String>(Response.SUCCESS,"试题更新成功");
		int count = questionAnswerMapper.updateById(questionAnswer);
		if(count == 0){
			response.setCode(Response.ERROR);
			response.setMsg("试题更新失败");
		}
		return response;
	}

	/**
	 * 根据Id查询试题答案
	 */
	@Override
	public Response<QuestionAnswer> seleByIdForManage(Long id) {
		Response<QuestionAnswer> response = new Response<QuestionAnswer>(Response.SUCCESS,"查询数据成功");
		QuestionAnswer questionAnswer = this.selectById(id);
		if(null != questionAnswer){
			response.setData(questionAnswer);
		}else{
			response.setCode(Response.ERROR);
			response.setMsg("查询数据失败");
		}
		return response;
	}

	/**
	 * 答案记录数
	 */
	@Override
	public Response<Integer> seleCountForManage(QuestionAnswer questionAnswer) {
		Response<Integer> response = new Response<Integer>(Response.SUCCESS,"查询数据成功");
		int count = questionAnswerMapper.updateById(questionAnswer);
		response.setData(count);
		if(count == 0){
			response.setCode(Response.ERROR);
			response.setMsg("查询数据失败");
		}
		return response;
	}

}
