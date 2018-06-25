package com.fulan.application.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.paper.domain.el.QuestionAnswer;

@Mapper
public interface QuestionAnswerMapper extends BaseMapper<QuestionAnswer> {
	List<QuestionAnswer> seleByQuestionId(String questionId);
	
	Integer deleBatchIds(List<Long> list);

	Integer deleAnswerById(Long id);
}
