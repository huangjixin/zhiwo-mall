package com.zwo.modules.member.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zwo.modules.member.domain.Member;
import com.zwo.modules.member.service.IMemberService;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("member")
@Lazy(true)
public class MemberController extends BaseController<Member> {
	@Autowired
	@Lazy(true)
	private IMemberService memberService;
	
	private static final String basePath = "views/mall/member/";
	
	@RequestMapping(value = { "", "list" })
	@RequiresPermissions("member:member:view")
	public String list(HttpServletRequest httpServletRequest) {
		return basePath+"member_list";
	}
	
//	@RequiresPermissions("system:member:create")
	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String tocreate(@Valid Member member, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("member", member);
		return basePath + "member_edit";
	}

//	@RequiresPermissions("system:member:view")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		Member member = memberService.selectByPrimaryKey(id);

		uiModel.addAttribute("member", member);
		uiModel.addAttribute("operation", "edit");
		return basePath + "member_edit";
	}
	
//	@RequiresPermissions("system:member:create")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid Member tbmember, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {

		}
		
		int res = memberService.insertSelective(tbmember);
		if(res==1){
			redirectAttributes.addFlashAttribute("member", tbmember);
			redirectAttributes.addFlashAttribute("message", "保存用户成功！");
		}
		
		return "redirect:/member/create";
	}
	 
//	@RequiresPermissions("system:member:edit")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid Member member, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			
		}
		
		int res = this.memberService.updateByPrimaryKeySelective(member);
		if(res==1){
			redirectAttributes.addFlashAttribute("member", member);
			redirectAttributes.addFlashAttribute("message", "保存用户成功！");
		}
		uiModel.addAttribute("member", member);
		uiModel.addAttribute("operation", "edit");
		return basePath + "member_edit";
	}
}
