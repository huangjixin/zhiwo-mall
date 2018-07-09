/**
 * 
 */
package com.fulan.application.oa.service;

import java.util.List;

import com.fulan.application.oa.domain.FwdOaBankCard;

/**
 * @author 黄记新
 * 提供对银行的增删改查
 */
public interface IBankCardService {
	/**
	 * 新增一个银行
	 * @param bankCard
	 * @return
	 * @throws Exception 
	 */
	int save(FwdOaBankCard bankCard) throws Exception;
	/**
	 * 删除一个银行
	 * @param id
	 * @return
	 */
	int delete(int id);
	/**
	 * 修改
	 * @param bankCard
	 * @return
	 * @throws Exception 
	 */
	int update(FwdOaBankCard bankCard) throws Exception;
	/**
	 * 查询全部列表
	 * @return
	 */
	List<FwdOaBankCard> selectAll();
	
	/**
	 * 根据ID查询数据
	 * @param FwdOaBankCard
	 * @return
	 */
	 FwdOaBankCard selectById(int id) ;
	 
	 /**
		 * 根据代理人编号查询数据
		 * 
		 * @param selectByAgentCode
		 * @return
		 */
	 List<FwdOaBankCard> selectByAgentCode(String agentCode) ;
	 
	 /**'
	  * 添加或修改本地数据
	  * @param thirdOaBankCardList   第三方数据
	  * @param fwdOaBankCardList    本地数据
	  * @return
	  * @throws Exception
	  */
	  List<FwdOaBankCard> saveOrUpdate(List<FwdOaBankCard> thirdOaBankCardList, List<FwdOaBankCard> fwdOaBankCardList,String agentCode) throws Exception;
	
	  /**
	   * @Description:从common query 获取代理人银行卡信息
	   * @param agentCode
	   * @return
	   */
	  List<FwdOaBankCard> getAgentBankCardFromCommonQuery(String agentCode);
}
