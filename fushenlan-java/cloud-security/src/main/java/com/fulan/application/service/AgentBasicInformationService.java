package com.fulan.application.service;


import java.util.List;

import com.fulan.api.security.domain.AgentBasicInformation;

public interface AgentBasicInformationService {
    int deleteByPrimaryKey(String agentId);

    void save(AgentBasicInformation record);

    AgentBasicInformation selectByPrimaryKey(Long agentId);

    int updateByPrimaryKey(AgentBasicInformation record);
    
    List<AgentBasicInformation> selectAllAgentBasicInformation();
}