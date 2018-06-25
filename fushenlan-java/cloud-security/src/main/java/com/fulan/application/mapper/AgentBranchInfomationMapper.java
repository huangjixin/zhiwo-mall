package com.fulan.application.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.security.domain.AgentBranchInfomation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentBranchInfomationMapper extends BaseMapper<AgentBranchInfomation> {
    int deleteByPrimaryKey(String agentId);

    int insertSelective(List<AgentBranchInfomation> record);

    AgentBranchInfomation selectByPrimaryKey(Long agentBranchId);
    
    AgentBranchInfomation getAgentBranchInfoById(Long agentId);

    int updateByPrimaryKeySelective(AgentBranchInfomation record);

    int updateByPrimaryKey(AgentBranchInfomation record);
    
    List<AgentBranchInfomation> selectAllAgentBranchInformation();
}