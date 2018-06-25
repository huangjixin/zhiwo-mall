package com.fulan.application.service;


import com.fulan.api.security.domain.AgentLicenseInfomation;

import java.util.List;

public interface AgentLicenseInfomationService {
    int deleteByPrimaryKey(String agentId);

    void save(List<AgentLicenseInfomation> record);

    AgentLicenseInfomation selectByPrimaryKey(Long agentLicenseId);

    int updateByPrimaryKey(AgentLicenseInfomation record);
}