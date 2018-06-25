package com.fulan.application.mapper;

import com.fulan.api.security.domain.AccountAgent;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountAgentMapper {
    int deleteByPrimaryKey(String id);

    int insert(AccountAgent record);

    int insertSelective(AccountAgent record);

    AccountAgent selectByPrimaryKey(String agentId);

    int updateByPrimaryKeySelective(AccountAgent record);

    int updateByPrimaryKey(AccountAgent record);
}