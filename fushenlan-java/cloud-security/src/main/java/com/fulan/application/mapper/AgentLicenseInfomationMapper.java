package com.fulan.application.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.security.domain.AgentLicenseInfomation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentLicenseInfomationMapper  extends BaseMapper<AgentLicenseInfomation> {
    int deleteByPrimaryKey(String agentId);

    int insertSelective(List<AgentLicenseInfomation> record);

    AgentLicenseInfomation selectByPrimaryKey(Long agentLicenseId);

    int updateByPrimaryKeySelective(AgentLicenseInfomation record);

    int updateByPrimaryKey(AgentLicenseInfomation record);
}