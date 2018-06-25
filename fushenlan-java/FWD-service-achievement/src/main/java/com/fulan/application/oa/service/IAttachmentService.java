/**
 * 
 */
package com.fulan.application.oa.service;

import java.util.List;

import com.fulan.application.oa.domain.FwdOaFormAttachment;

/**
 * @author 黄记新
 * 提供对附件的增删改查
 */
public interface IAttachmentService {
	/**
	 * 新增一个附件
	 * @param address
	 * @return
	 */
	int save(FwdOaFormAttachment address);
	/**
	 * 删除一个附件
	 * @param address
	 * @return
	 */
	int delete(int id);
	/**
	 * 修改
	 * @param address
	 * @return
	 */
	int update(FwdOaFormAttachment address);
	/**
	 * 查询全部列表
	 * @param address
	 * @return
	 */
	List<FwdOaFormAttachment> selectAll();
	
	/**
	 * 根据ID查询数据
	 * @param FwdOaFormAttachment
	 * @return
	 */
	 FwdOaFormAttachment selectById(int id) ;
}
