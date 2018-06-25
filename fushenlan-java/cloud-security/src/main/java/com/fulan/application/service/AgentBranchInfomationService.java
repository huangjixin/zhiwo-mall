package com.fulan.application.service;


import com.fulan.api.security.domain.AgentBranchInfomation;
import com.fulan.application.util.domain.Response;

import java.util.List;

public interface AgentBranchInfomationService {
    int deleteByPrimaryKey(String agentId);

    void save(List<AgentBranchInfomation> record);

    AgentBranchInfomation selectByPrimaryKey(Long agentBranchId);

    int updateByPrimaryKey(AgentBranchInfomation record);
    
    Response<AgentBranchInfomation> getAgentBranchInfoById(Long agentId);
    
    List<AgentBranchInfomation> selectAllAgentBranchInformation();
}