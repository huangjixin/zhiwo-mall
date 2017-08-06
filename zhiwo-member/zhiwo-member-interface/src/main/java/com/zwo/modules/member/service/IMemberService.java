/**
 * 
 */
package com.zwo.modules.member.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zwo.modules.mall.domain.PrProduct;
import com.zwo.modules.member.domain.Member;
import com.zwo.modules.member.domain.MemberAddress;
import com.zwotech.modules.core.service.IBaseService;

/**
 * @author hjx
 *
 */
public interface IMemberService extends IBaseService<Member> {
	/**
	 * 根据会员Id进行地址查询。
	 * @param memberId
	 * @return
	 */
	List<MemberAddress> selectByMId(String memberId);
	
	/**
     * 查询会员分销的商品。
     * @param memberId
     * @return
     */
	List<PrProduct> selectByMemberId(String memberId);
	
	/**
	 * 分页查询会员分销的商品。
	 * @param example
	 * @param pageInfo
	 * @return
	 */
	PageInfo<PrProduct> selectByMemberId(String memberId,PageInfo<PrProduct> pageInfo);
}
