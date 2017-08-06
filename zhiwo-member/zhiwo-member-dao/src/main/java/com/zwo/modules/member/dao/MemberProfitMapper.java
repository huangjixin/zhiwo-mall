package com.zwo.modules.member.dao;

import com.zwo.modules.member.domain.MemberProfit;

import tk.mybatis.mapper.common.Mapper;

public interface MemberProfitMapper extends Mapper<MemberProfit> {
	/**
	 * 统计会员：我的分销商品盈利
	 * @param memberId
	 * @return
	 */
	Double sumProfitByMemberId(String memberId);
	/**
	 * 统计会员：我的分销商品盈利实际到帐
	 * @param memberId
	 * @return
	 */
	Double sumRealProfitByMemberId(String memberId);
}