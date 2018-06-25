package com.fulan.application.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.security.domain.AgentContactInformation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentContactInformationMapper  extends BaseMapper<AgentContactInformation> {
    int deleteByPrimaryKey(String agentId);

    int insert(List<AgentContactInformation> record);

    int insertSelective(List<AgentContactInformation> record);

    AgentContactInformation selectByPrimaryKey(Long agentContactId);

    int updateByPrimaryKeySelective(AgentContactInformation record);

    int updateByPrimaryKey(AgentContactInformation record);
}