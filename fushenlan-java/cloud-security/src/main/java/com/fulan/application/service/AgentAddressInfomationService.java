package com.fulan.application.service;


import com.fulan.api.security.domain.AgentAddressInfomation;

import java.util.List;

public interface AgentAddressInfomationService {

    int deleteByPrimaryKey(String agentId);

    void save(List<AgentAddressInfomation> record);

    AgentAddressInfomation selectByPrimaryKey(Long agentAddrId);

    int updateByPrimaryKey(AgentAddressInfomation record);
}