package com.fulan.application.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.security.domain.AgentLicenseInfomation;
import com.fulan.application.mapper.AgentLicenseInfomationMapper;
import com.fulan.application.orm.id.GenerateVOUtil;
import com.fulan.application.service.AgentLicenseInfomationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AgentLicenseInfomationServiceImpl extends ServiceImpl<AgentLicenseInfomationMapper, AgentLicenseInfomation> implements AgentLicenseInfomationService{

    @Autowired
    private AgentLicenseInfomationMapper licenseInfomationMapper;

    public int deleteByPrimaryKey(String agentId){
       return licenseInfomationMapper.deleteByPrimaryKey(agentId);
    }

    public void save(List<AgentLicenseInfomation> record){
        GenerateVOUtil.generate(record);
        insertBatch(record);
    }

    public AgentLicenseInfomation selectByPrimaryKey(Long agentLicenseId){
        return licenseInfomationMapper.selectByPrimaryKey(agentLicenseId);
    }

    public int updateByPrimaryKey(AgentLicenseInfomation record){
        return licenseInfomationMapper.updateByPrimaryKey(record);
    }
}