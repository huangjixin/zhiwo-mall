/**
 * 
 */
package com.fulan.application.oa.service;

import java.util.List;

import com.fulan.application.oa.domain.FwdOaMobile;

/**
 * @author 黄记新
 * 提供对电话的增删改查
 */
public interface IMobileService {
	/**
	 * 新增一个地址
	 * @param mobile
	 * @return
	 */
	int save(FwdOaMobile mobile)throws Exception;
	/**
	 * 删除一个地址
	 * @param id
	 * @return
	 */
	int delete(int id);
	/**
	 * 修改
	 * @param mobile
	 * @return
	 */
	int update(FwdOaMobile mobile)  throws Exception ;
	/**
	 * 查询全部列表
	 * @return
	 */
	List<FwdOaMobile> selectAll();
	
	/**
	 * 根据ID查询数据
	 * @param FwdOaMobile
	 * @return
	 */
	FwdOaMobile selectById(int id) ;
	
	 /**
		 * 根据代理人编号查询数据
		 * 
		 * @param selectByAgentCode
		 * @return
		 */
	 List<FwdOaMobile> selectByAgentCode(String agentCode) ;
	
	 /**'
	  * 添加或修改本地数据
	  * @param thirdoaMobileCardList   第三方数据
	  * @param oaMobilList    本地数据
	  * @return
	  * @throws Exception
	  */
	 List<FwdOaMobile> saveOrUpdate(List<FwdOaMobile> thirdoaMobileCardList, List<FwdOaMobile> oaMobilList,String agentCode) throws Exception;
}
