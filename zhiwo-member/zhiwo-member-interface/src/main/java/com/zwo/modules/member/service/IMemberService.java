/**
 * 
 */
package com.zwo.modules.member.service;

import java.util.List;

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
}
