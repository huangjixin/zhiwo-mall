package com.fulan.application.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.application.operatelog.domain.OperateLog;
import com.fulan.application.service.OperateLogService;
import com.fulan.application.util.page.PageInfo;


@RestController
@RequestMapping(value="/operateLog")
public class OperateLogController {
	
	
	private static final Logger LOG = LoggerFactory.getLogger(OperateLog.class);
	
	@Autowired
	private OperateLogService operateLogService;
	
	@RequestMapping(value="selectList",method=RequestMethod.POST)
	@ResponseBody
	public List<OperateLog> selectByList(){
		Map<String,Object> map = new HashMap<>();
		List<OperateLog> list = operateLogService.selectByMap(map);
		System.out.println(list.size());
		return list;
	}
	
	
    @RequestMapping(value="/selectPageList",method=RequestMethod.GET)
    public @ResponseBody PageInfo<OperateLog> listByPage(
    		 @RequestParam(name = "requestIp") String requestIp,
    		 @RequestParam(name = "requestUrl") String requestUrl,
             @RequestParam(name = "pageNo") int pageNo,
             @RequestParam(name = "pageSize") int pageSize
    ) {
    	Page<OperateLog> page = new Page<OperateLog>(pageNo, pageSize);
    	return operateLogService.listByPage(page, requestIp, requestUrl, pageNo, pageSize);
    	
    }

	

}
