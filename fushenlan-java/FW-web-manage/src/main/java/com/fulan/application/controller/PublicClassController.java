package com.fulan.application.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.calendar.service.ManagePublicClassService;
import com.fulan.application.shrio.SessionContextUtils;
import com.fulan.application.util.page.PageInfo;

/**
 * @Author: chenzuyu
 * @Date: 2018/1/31 20:39
 */
@Controller
public class PublicClassController {

	@Autowired
    private ManagePublicClassService managePublicClassService;

	@RequestMapping("/manage/commentListByPage")
	public String commentListByPage(Model model,
			@RequestParam(value="courseId",defaultValue="969264189962977280",required=false)String id,//公开课id
			@RequestParam(value="userName",required=false)String userName,//用户名
			@RequestParam(value="comment",required=false)String comment,//评论内容
			@RequestParam(value="isOpen",required=false)String isOpen,//是否开启
			@RequestParam(value="beginTime",required=false)String beginTime,//开始时间
			@RequestParam(value="endTime",required=false)String endTime,//结束时间
			@RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="10") int pageSize
			){
		System.err.println("-----------------------web1-----------------------"+SessionContextUtils.getLoginName());
		Map<String,Object> map=new HashMap<>();
		map.put("courseId",id);
		map.put("userName", userName);
		map.put("comment", comment);
		map.put("isOpen", isOpen);
		map.put("beginTime", beginTime);
		map.put("endTime", endTime);
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		//PublicClassVo publicClassVo =managePublicClassService.getPublicClassInfo(id);
		PageInfo<Map<String,Object>> page=managePublicClassService.listCommentByPage(map);
		System.out.println("-----------------------web2-----------------------");
		List<Map<String,Object>> list=page.getRecords();
		System.out.println("-----------------------web2---"+list.size()+"--------------------");
		for (Map<String, Object> map2 : list) {
			if(""!=map2.get("gmtCreate")||null!=map2.get("gmtCreate")){
				Long ss=(Long) map2.get("gmtCreate");
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date date = new Date(ss);
				String str = sdf.format(date);
				map2.put("gmtCreate", str);
			}
		}
		
		//model.addAttribute("publicClassVo",publicClassVo);
		model.addAttribute("page",page);
		model.addAttribute("map",map);
		System.out.println("-----------------------web3-----------------------");
		//return "login";
		//return "main";
		//return "/elearning/public_class/public_class2";
		return "/elearning/public_class/publicClass_comment";
	}
	
	
}
