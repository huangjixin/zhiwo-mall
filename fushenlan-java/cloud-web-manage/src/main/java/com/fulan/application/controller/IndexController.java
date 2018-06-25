package com.fulan.application.controller;

import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fulan.api.security.domain.Resource;
import com.fulan.api.security.service.ResourceService;
import com.fulan.api.system.domain.Dictionary;
import com.fulan.api.system.service.DictionaryService;
import com.fulan.application.util.page.PageInfo;

/**
 * @Author: shengchenglong
 * @Date: 2018/1/11 12:52
 */
@Controller
public class IndexController {

	@Autowired
	private ResourceService resourceService;
	
    @Autowired
    private DictionaryService dictionaryClient;
    @GetMapping("/demo")
    public String demo(Model model) {
        model.addAttribute("time", new Date());
        model.addAttribute("message", "Web Demo");

        Dictionary dic = dictionaryClient.findById(new Long(1));
        PageInfo page = dictionaryClient.listByPage(null, null, null, null, 1, 10, null, null);
        List<Dictionary> rootList = dictionaryClient.listRootDictionary();

        model.addAttribute("dic", dic);
        model.addAttribute("page", page);
        model.addAttribute("rootList", rootList);

        return "demo";
    }
    @GetMapping("/demo1")
    @RequiresPermissions("contract:view")
    public String demo1(Model model){
    	Resource resource = new Resource();
    	resource.setResourceName("liyongkang");
    	resourceService.saveForManage(resource);
    	 return "demo";
    }

    @GetMapping("/testUploadFiles")
    public String testUploadFiles(Model model){
    	model.addAttribute("1", 1);
    	 return "testUploadFiles";
    }

    @GetMapping("/layerdemo")
    public String layerdemo(Model model,String flag){
    	 String tips="调用layer弹出层，使用type=2功能弹出时，需要将访问url从页面装饰器WebSiteMeshFilter.java中移除"+flag;
    	 model.addAttribute("tips", tips);
    	 return "layerdemo";
    }
    

}  