package com.zwo.modules.system.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zwo.modules.system.domain.TbUserGroup;
import com.zwo.modules.system.service.ITbUserGroupService;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("userGroup")
@Lazy(true)
public class UserGroupController extends BaseController<TbUserGroup> {
	@Autowired
	@Lazy(true)
	private ITbUserGroupService userGroupService;
	
	/*@Autowired
	@Lazy(true)*/
	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate = SpringContextHolder.getBean("redisTemplate");
	
	private static final String basePath = "views/system/userGroup/";
	
	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath+"userGroup_list";
	}
	
	@RequestMapping(value = {"create"},method=RequestMethod.GET)
	public String create(@Valid TbUserGroup userGroup, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("userGroup", userGroup);
		return basePath+"userGroup_edit";
	}
	  
	@RequestMapping(value = "edit",method=RequestMethod.GET)
	public String edit(@RequestParam("id") String id, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		TbUserGroup userGroup = null;
		/*ValueOperations<String, Object> valueOperations = null;
		if(redisTemplate!=null){
			valueOperations =redisTemplate.opsForValue();
			userGroup = (TbUserGroup) valueOperations.get(id);
		}*/
		
		if(userGroup==null){
			userGroup=userGroupService.selectByPrimaryKey(id);
			/*if(valueOperations != null ){
				valueOperations.set(id, userGroup);
			}*/
		}
		
		uiModel.addAttribute("userGroup", userGroup);
		uiModel.addAttribute("operation", "edit");
		return basePath+"userGroup_edit";
	}
	
	@RequestMapping(value = {"test"},method=RequestMethod.GET)
	public String test(Model uiModel,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("rawData", 123456);
		return "test";
	}
}
