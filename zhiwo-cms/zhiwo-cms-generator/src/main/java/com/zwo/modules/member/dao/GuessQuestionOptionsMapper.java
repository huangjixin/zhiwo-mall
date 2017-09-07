package com.zwo.modules.member.dao;

import com.zwo.modules.member.domain.GuessQuestionOptions;
import com.zwo.modules.member.domain.GuessQuestionOptionsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface GuessQuestionOptionsMapper extends Mapper<GuessQuestionOptions> {
    int countByExample(GuessQuestionOptionsCriteria example);

    int deleteByExample(GuessQuestionOptionsCriteria example);

    List<GuessQuestionOptions> selectByExample(GuessQuestionOptionsCriteria example);

    int updateByExampleSelective(@Param("record") GuessQuestionOptions record, @Param("example") GuessQuestionOptionsCriteria example);

    int updateByExample(@Param("record") GuessQuestionOptions record, @Param("example") GuessQuestionOptionsCriteria example);
}