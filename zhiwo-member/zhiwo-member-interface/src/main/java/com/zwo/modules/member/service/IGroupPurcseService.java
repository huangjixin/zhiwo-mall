/**
 * 
 */
package com.zwo.modules.member.service;

import java.util.List;

import com.zwo.modules.member.domain.GroupPurcse;
import com.zwotech.modules.core.service.IBaseService;

/**
 * @author hjx
 *
 */
public interface IGroupPurcseService extends IBaseService<GroupPurcse> {
	/**
     * 根据产品ID查询没有成团的列表，仅查前面十条记录。
     * @param productId
     * @return
     */
    List<GroupPurcse> selectUnFormByPId(String productId);
}
