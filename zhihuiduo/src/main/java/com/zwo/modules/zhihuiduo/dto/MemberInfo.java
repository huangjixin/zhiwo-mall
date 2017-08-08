/**
 * 
 */
package com.zwo.modules.zhihuiduo.dto;

import java.io.Serializable;
import java.util.List;

import com.zwo.modules.member.domain.MemberAccount;
import com.zwo.modules.member.domain.MemberAddress;
import com.zwo.modules.member.domain.MemberPlayAccount;

/**
 * 前端会员传输对象。
 * @author 黄记新
 * 2017.8.8
 */
public class MemberInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 会员地址。
	 */
	private List<MemberAddress> memberAddress;

	/**
	 * 会员我的账户。
	 */
	private MemberAccount memberAccount;
	
	
	/**
	 * 会员我的智惠豆账户。
	 */
	private MemberPlayAccount memberPlayAccount;
	
	public List<MemberAddress> getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(List<MemberAddress> memberAddress) {
		this.memberAddress = memberAddress;
	}

	public MemberAccount getMemberAccount() {
		return memberAccount;
	}

	public void setMemberAccount(MemberAccount memberAccount) {
		this.memberAccount = memberAccount;
	}

	public MemberPlayAccount getMemberPlayAccount() {
		return memberPlayAccount;
	}

	public void setMemberPlayAccount(MemberPlayAccount memberPlayAccount) {
		this.memberPlayAccount = memberPlayAccount;
	}
}
