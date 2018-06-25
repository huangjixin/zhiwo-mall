package com.fulan.application.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.security.domain.AccountGroup;
import com.fulan.api.security.vo.AccountGroupVo;

@Repository
public interface AccountGroupMapper extends BaseMapper<AccountGroup> {
    
    int countUser (@Param("groupId") Long groupId);
    
    List<AccountGroupVo> listAccountGroupByGroupId (@Param("groupId") Long groupId);
    
}
