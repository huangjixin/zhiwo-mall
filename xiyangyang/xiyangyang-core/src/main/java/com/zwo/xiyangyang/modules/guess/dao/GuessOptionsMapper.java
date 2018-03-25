package com.zwo.xiyangyang.modules.guess.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zwo.xiyangyang.modules.guess.domain.GuessOptions;

import tk.mybatis.mapper.common.Mapper;

public interface GuessOptionsMapper extends Mapper<GuessOptions> {
	/**
	 * 根据问题ID查询选项。
	 * @param questionId
	 * @return
	 */
	List<GuessOptions> selectByQuestionId(@Param(value="questionId")String questionId);
}