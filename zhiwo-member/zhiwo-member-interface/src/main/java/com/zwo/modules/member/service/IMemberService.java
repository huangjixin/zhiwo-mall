/**
 * 
 */
package com.zwo.modules.member.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zwo.modules.mall.domain.OrderTrade;
import com.zwo.modules.mall.domain.PrProduct;
import com.zwo.modules.member.domain.GuessQuestion;
import com.zwo.modules.member.domain.Member;
import com.zwo.modules.member.domain.MemberAccount;
import com.zwo.modules.member.domain.MemberAddress;
import com.zwo.modules.member.domain.MemberPlayAccount;
import com.zwo.modules.member.domain.MemberPlayHisAccount;
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
//	List<MemberAddress> selectMemberAddressByMId(String memberId);
	
	/**
	 * 根据会员Id进行会员账户查询。
	 * @param memberId
	 * @return
	 */
	MemberAccount selectMemberAccountByMId(String memberId);
	
	/**
	 * 修改会员账户。
	 * @param memberAccount
	 */
	int updateMemberAccountByPrimaryKeySelective(MemberAccount memberAccount);
	
	/**
	 * 修改会员智惠豆账户。
	 * @param memberAccount
	 */
	int updateMemberPlayAccountByPrimaryKeySelective(MemberPlayAccount playAccount);
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
	
	/**
     * 根据会员ID查询会员参与的竞猜
     * @param memberId
     * @return
     */
    List<GuessQuestion> selectGuessQuestionByMemberId(String memberId);
    
    /**
	 * 分页会员ID查询会员参与的竞猜
	 * @param example
	 * @param pageInfo
	 * @return
	 */
	PageInfo<GuessQuestion> selectGuessQuestionByMemberId(String memberId,PageInfo<GuessQuestion> pageInfo);
	/**
	 * 根据会员ID查询会员参与的订单
	 * @param memberId
	 * @return
	 */
	List<OrderTrade> selectOrderByMemberId(String memberId);
	
	/**
	 * 分页会员ID查询会员参与的订单
	 * @param example
	 * @param pageInfo
	 * @return
	 */
	PageInfo<OrderTrade> selectOrderByMemberId(String memberId,PageInfo<OrderTrade> pageInfo);
	
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
	
	/**
	 * 根据会员ID查询会员智慧豆账户记录
	 * @param memberId
	 * @return
	 */
	MemberPlayAccount selectMemberPlayAccountByMemberId(String memberId);
	
	/**
	 * 根据会员ID查询会员智慧豆账户历史记录
	 * @param memberId
	 * @return
	 */
	List<MemberPlayHisAccount> selectMemberPlayHisAccountByMemberId(String memberId);
	
	/**
	 * 分页会员ID查询会员智慧豆账户历史记录
	 * @param example
	 * @param pageInfo
	 * @return
	 */
	PageInfo<MemberPlayHisAccount> selectMemberPlayHisAccountByMemberId(String memberId,PageInfo<MemberPlayHisAccount> pageInfo);
	
	/**
	 * 根据用户或者电话或者Email来查找会员。
	 * @param usernameOrMphoneOrEmail
	 * @return
	 */
	Member selectMember(String usernameOrMphoneOrEmail);
}
