/**
 * 
 */
package com.zwo.modules.member.service;

import java.util.List;

import com.zwo.modules.member.domain.GroupPurcseMember;
import com.zwotech.modules.core.service.IBaseService;

/**
 * @author hjx
 *
 */
public interface IGroupPurcseMemberService extends IBaseService<GroupPurcseMember> {
	/**
	 * 根据拼团的ID查询个数。
	 * @param groupPurcseId
	 * @return
	 */
	int countByGroupPurcseId(String groupPurcseId);
	
	/**
	 * 查询开团的人。
	 * @param groupPurcseId
	 * @return
	 */
	List<GroupPurcseMember> selectByGroupPurcseId(String groupPurcseId);
}
