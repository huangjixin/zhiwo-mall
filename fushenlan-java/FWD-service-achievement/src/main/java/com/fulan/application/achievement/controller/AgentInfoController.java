package com.fulan.application.achievement.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fulan.application.achievement.domain.FwdAgentInfo;
import com.fulan.application.achievement.service.IAgentInfoService;
/**
 * 头像控制层
 * 
 * @author 曾文明
 *
 */
@RestController
@RequestMapping("/agentInfo")
public class AgentInfoController {

	@Autowired
	private IAgentInfoService agentInfoService;

	/**
	 * 保存数据
	 * 
	 * @param save
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	private int save(FwdAgentInfo agentInfo) {
		agentInfo.setCreateDatetime(new Date());
		agentInfo.setUpdateDatetime(new Date());
		int result = agentInfoService.save(agentInfo);
		return result;
	}

	/**
	 * 删除数据
	 * 
	 * @param delete
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	private int delete(@PathVariable(value = "id") Integer id) {
		int result = agentInfoService.delete(id);
		return result;
	}

	/**
	 * 修改数据
	 * 
	 * @param update
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	private int update(FwdAgentInfo agentInfo) {
		agentInfo.setUpdateDatetime(new Date());
		int result = agentInfoService.update(agentInfo);
		return result;
	}

	/**
	 * 查询数据
	 * 
	 * @param selectAll
	 * @return
	 */
	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	private List<FwdAgentInfo> selectAll() {
		List<FwdAgentInfo> agentInfoList = agentInfoService.selectAll();
		return agentInfoList;
	}
}
