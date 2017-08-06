package com.zwo.modules.member.dao;

import java.util.List;

import com.zwo.modules.member.domain.GuessQuestion;

import tk.mybatis.mapper.common.Mapper;

public interface GuessQuestionMapper extends Mapper<GuessQuestion> {
    /**
     * 根据会员ID查询会员参与的竞猜
     * @param memberId
     * @return
     */
    List<GuessQuestion> selectByMemberId(String memberId);
}