package com.fulan.api.paper.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.paper.domain.el.PaperQuesShare;
import com.fulan.application.util.domain.Response;

@FeignClient(name = "paper")
public interface PaperQuesShareService {
	
	/**
	 * 试题保存
	 * 接收Question
	 */
	@PostMapping("/manage/paperQuesShare/save")
	Response<String> saveForManage(@RequestBody PaperQuesShare paperQuesShare);
	
	/**
	 * 试题批量保存
	 * type 1试题2试卷
	 * groupIds 多个用户组Id以逗号分割
	 * hostId 试卷/试题Id
	 */
	@PostMapping("/manage/paperQuesShare/saveBatch")
	Response<Integer> saveBatchForManage(@RequestParam(value = "hostIds") String hostIds ,
			@RequestParam(value = "type") String type, 
			@RequestParam(value = "groupIds") String groupIds,
			@RequestParam(value = "isShare") String isShare
			);
	
	/**
	 * 根据id删除试题
	 */
	@PostMapping("/manage/paperQuesShare/delete")
	Response<String> deleteByIdForManage(@RequestParam("id") Long id);
	
	/**
	 * 批量删除试题
	 */
	@PostMapping("/manage/paperQuesShare/deleteBatchIds")
	Response<String> deleteBatchIdsForManage(@RequestParam("ids") String ids);
	
	/**
	 * 试题更新
	 */
	@PostMapping("/manage/paperQuesShare/update")
	Response<String> updateForManage(@RequestBody PaperQuesShare paperQuesShare);
	
	/**
	 * 查询试题列表
	 */
	@PostMapping("/manage/paperQuesShare/list")
	Response<List<PaperQuesShare>> listForManage(@RequestBody PaperQuesShare paperQuesShare);
	
	/**
	 * 根据Id查询试题
	 */
	@PostMapping("/manage/paperQuesShare/seleById")
	Response<PaperQuesShare> seleByIdForManage(@RequestParam("id") Long id);
	
	/**
	 * 记录数
	 */
	@PostMapping("/manage/paperQuesShare/seleCount")
	Response<Integer> seleCountForManage(@RequestBody PaperQuesShare paperQuesShare);

	/**
	 * 查询试题已分享的范围
	 * @param id
	 * @return
	 */
	@GetMapping("/manage/paperQuesShare/selectShareQuestion")
	List<Long>  selectShareQuestion(@RequestParam("id") String id);


	/**
	 * 查询试卷已分享的范围
	 * @param id
	 * @return
	 */
	@GetMapping("/manage/paperQuesShare/selectSharePaper")
	List<Long>  selectSharePaper(@RequestParam("id") String id);


}
