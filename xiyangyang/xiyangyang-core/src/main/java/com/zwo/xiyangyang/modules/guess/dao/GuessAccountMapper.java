package com.zwo.xiyangyang.modules.guess.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zwo.xiyangyang.modules.guess.domain.GuessAccount;

import tk.mybatis.mapper.common.Mapper;

public interface GuessAccountMapper extends Mapper<GuessAccount> {
	/**
	 * 根据会员Id查询账户。
	 * @param memId
	 * @return
	 */
	List<GuessAccount> selectByMemId(@Param("memId")String memId);
}