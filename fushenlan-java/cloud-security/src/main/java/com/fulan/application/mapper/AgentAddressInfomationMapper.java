package com.fulan.application.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.security.domain.AgentAddressInfomation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentAddressInfomationMapper extends BaseMapper<AgentAddressInfomation> {
    int deleteByPrimaryKey(String agentId);

    int insertSelective(List<AgentAddressInfomation> record);

    AgentAddressInfomation selectByPrimaryKey(Long agentAddrId);

    int updateByPrimaryKeySelective(AgentAddressInfomation record);

    int updateByPrimaryKey(AgentAddressInfomation record);
}