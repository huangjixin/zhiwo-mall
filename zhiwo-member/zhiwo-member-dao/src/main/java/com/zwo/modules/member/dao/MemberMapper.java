package com.zwo.modules.member.dao;

import java.util.List;

import com.zwo.modules.member.domain.Member;

import tk.mybatis.mapper.common.Mapper;

public interface MemberMapper extends Mapper<Member> {
    /**
     * 查询分销该商品的会员
     * @param productId
     * @return
     */
    List<Member> selectByPid(String productId);
    
    /**
     * 查询分销该用户ID的会员
     * @param productId
     * @return
     */
    List<Member> selectByUserId(String userId);
}