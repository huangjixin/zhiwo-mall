package com.fulan.application.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.security.domain.UserGroup;

/**
 * <p>
 * 用户组 Mapper 接口
 * </p>
 *
 * @author fulan123
 * @since 2018-03-23
 */
@Repository
public interface GroupMapper extends BaseMapper<UserGroup> {
    
    /**
     * 
     * @param accountId
     * @return
     */
    List<UserGroup> selectGroupByAccountId(@Param("accountId")Long accountId);

}
