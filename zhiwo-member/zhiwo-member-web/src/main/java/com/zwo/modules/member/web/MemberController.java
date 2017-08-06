package com.zwo.modules.member.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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

import com.zwo.modules.member.domain.Member;
import com.zwo.modules.member.service.IMemberService;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("member")
@Lazy(true)
public class MemberController extends BaseController<Member> {
	@Autowired
	@Lazy(true)
	private IMemberService memberService;
	
	/*@Autowired
	@Lazy(true)*/
	private RedisTemplate redisTemplate = SpringContextHolder.getBean("redisTemplate");
	
	private static final String basePath = "views/mall/member/";
	
	@RequestMapping(value = { "", "list" })
	@RequiresPermissions("member:member:view")
	public String list(HttpServletRequest httpServletRequest) {
		return basePath+"member_list";
	}
	
	@RequestMapping(value = {"create"},method=RequestMethod.GET)
	@RequiresPermissions("member:member:create")
	public String create(@Valid Member member, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("member", member);
		return basePath+"member_edit";
	}
	  
	@RequestMapping(value = "edit",method=RequestMethod.GET)
	@RequiresPermissions("member:member:edit")
	public String edit(@RequestParam("id") String id, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		Member member = null;
		ValueOperations<String, Object> valueOperations = null;
		if(redisTemplate!=null){
			valueOperations =redisTemplate.opsForValue();
			member = (Member) valueOperations.get(id);
		}
		
		if(member==null){
			member=memberService.selectByPrimaryKey(id);
			if(valueOperations != null ){
				valueOperations.set(id, member);
			}
		}
		
		uiModel.addAttribute("member", member);
		uiModel.addAttribute("operation", "edit");
		return basePath+"member_edit";
	}
	
	@RequestMapping(value = {"test"},method=RequestMethod.GET)
	public String test(Model uiModel,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("rawData", 123456);
		return "test";
	}
}
