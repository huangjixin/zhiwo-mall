package com.fulan.application.mapper;

import com.fulan.api.agent.vo.PerformVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TextMapper {

    public List<PerformVo>  getPerform(@Param("agentCode") String agentCode,@Param("month") String month,@Param("type") String type);

}
