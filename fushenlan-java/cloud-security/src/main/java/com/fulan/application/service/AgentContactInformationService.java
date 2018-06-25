package com.fulan.application.service;


import com.fulan.api.security.domain.AgentContactInformation;

import java.util.List;

public interface AgentContactInformationService {
    int deleteByPrimaryKey(String agentId);

    void save(List<AgentContactInformation> record);

    AgentContactInformation selectByPrimaryKey(Long agentContactId);

    int updateByPrimaryKey(AgentContactInformation record);
}