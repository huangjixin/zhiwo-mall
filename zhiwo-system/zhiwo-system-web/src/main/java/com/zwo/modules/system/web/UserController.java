package com.zwo.modules.system.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zwo.modules.system.domain.TbUser;
import com.zwo.modules.system.service.ITbUserService;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("user")
@Lazy(true)
public class UserController extends BaseController<TbUser> {
	@Autowired
	@Lazy(true)
	private ITbUserService userService;
	
	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate = SpringContextHolder.getBean("redisTemplate");
	
	private static final String basePath = "views/system/user/";
	
//	@RequiresPermissions("system:user:view")
	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath+"user_list";
	}
	
//	@RequiresPermissions("system:user:create")
	@RequestMapping(value = {"create"},method=RequestMethod.GET)
	public String create(@Valid TbUser user, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("user", user);
		return basePath+"user_edit";
	}
	  
//	@RequiresPermissions("system:user:edit")
	@RequestMapping(value = "edit",method=RequestMethod.GET)
	public String edit(@RequestParam("id") String id, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		TbUser user = null;
		/*ValueOperations<String, Object> valueOperations = null;
		if(redisTemplate!=null){
			valueOperations =redisTemplate.opsForValue();
			user = (TbUser) valueOperations.get(id);
		}*/
		
		if(user==null){
			user=userService.selectByPrimaryKey(id);
			/*if(valueOperations != null ){
				valueOperations.set(id, user);
			}*/
		}
		
		uiModel.addAttribute("user", user);
		uiModel.addAttribute("operation", "edit");
		return basePath+"user_edit";
	}
	
	@RequestMapping(value = {"test"},method=RequestMethod.GET)
	public String test(Model uiModel,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("rawData", 123456);
		return "test";
	}
}
