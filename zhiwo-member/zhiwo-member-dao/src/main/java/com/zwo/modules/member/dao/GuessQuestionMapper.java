package com.zwo.modules.member.dao;

import com.zwo.modules.member.domain.GuessQuestion;
import com.zwo.modules.member.domain.GuessQuestionCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface GuessQuestionMapper extends Mapper<GuessQuestion> {
    
}