package com.fulan.application.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.security.domain.AgentBasicInformation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentBasicInformationMapper extends BaseMapper<AgentBasicInformation> {
    int deleteByPrimaryKey(String agentId);

    int insertSelective(List<AgentBasicInformation> record);

    AgentBasicInformation selectByPrimaryKey(Long agentId);

    int updateByPrimaryKeySelective(AgentBasicInformation record);

    int updateByPrimaryKey(AgentBasicInformation record);
    
    List<AgentBasicInformation> selectAllAgentBasicInformation();
}