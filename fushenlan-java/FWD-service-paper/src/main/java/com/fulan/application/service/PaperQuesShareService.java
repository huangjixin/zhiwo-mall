package com.fulan.application.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.paper.domain.el.PaperQuesShare;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

public interface PaperQuesShareService {
	/**
	 * 试题试卷分享保存
	 * 接收PaperQuesShare
	 */
	Response<String> saveForManage(PaperQuesShare paperQuesShare);
	
	/**
	 * 根据id删除试题试卷分享
	 */
	Response<String> deleteForManage(Long id);
	
	/**
	 * 批量删除试题试卷分享
	 */
	Response<String> deleteBatchIdsForManage(String ids);

	/**
	 * 试题试卷分享更新
	 */
	Response<String> updateForManage(PaperQuesShare paperQuesShare);
	
	/**
	 * 查询试题试卷分享列表
	 */
	Response<List<PaperQuesShare>> listForManage(PaperQuesShare paperQuesShare);
	/**
	 * 查询试题试卷分享列表
	 */
	PageInfo<PaperQuesShare> listByPage(Page<PaperQuesShare> page,int pageNo, int pageSize);
	
	/**
	 * 根据Id查询试题试卷分享
	 */
	Response<PaperQuesShare> seleByIdForManage(Long id);
	
	/**
	 * 试题试卷分享记录数
	 */
	Response<Integer> seleCountForManage(PaperQuesShare paperQuesShare);
	
	/**
	 * 批量新增
	 * @param hostId
	 * @param type
	 * @param groupIds
	 * @return
	 */
	Response<Integer> saveBatch(String hostIds,String type,String groupIds,String isShare);
	/**
	 * 查询试题已分享的范围
	 */
	List<Long>  selectShareQuestion(String id);
	/**
	 * 查询试卷已分享的范围
	 */
	List<Long> selectSharePaper(String id);

}
