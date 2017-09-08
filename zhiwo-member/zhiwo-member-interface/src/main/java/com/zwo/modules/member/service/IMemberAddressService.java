/**
 * 
 */
package com.zwo.modules.member.service;

import java.util.List;

import com.zwo.modules.member.domain.MemberAddress;
import com.zwotech.modules.core.service.IBaseService;

/**
 * @author hjx
 *
 */
public interface IMemberAddressService extends IBaseService<MemberAddress> {
	/**
	 * 查询会员默认地址。
	 * @param memberId
	 * @return
	 */
	MemberAddress selectDefaultAddressByMemberId(String memberId);

	/**
	 * 查询会员所有地址。
	 * @param memberId
	 * @return
	 */
	List<MemberAddress> listAllByMemberId(String memberId);
}
