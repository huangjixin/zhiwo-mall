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

import com.zwo.modules.member.domain.MemberAccount;
import com.zwo.modules.member.service.IMemberService;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("memberAccount")
@Lazy(true)
public class MemberAccountController extends BaseController<MemberAccount> {
	@Autowired
	@Lazy(true)
	private IMemberService memberService;
	
	private static final String basePath = "views/member/member/";
	
	@RequestMapping(value = { "", "list" })
	@RequiresPermissions("member:memberAccount:view")
	public String list(HttpServletRequest httpServletRequest) {
		return basePath+"memberAccount_list";
	}

	@RequiresPermissions("member:memberAccount:view")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		MemberAccount memberAccount = memberService.selectMemberAccountByMId(id);

		uiModel.addAttribute("memberAccount", memberAccount);
		uiModel.addAttribute("operation", "edit");
		return basePath + "memberAccount_edit";
	}
	 
	@RequiresPermissions("member:memberAccount:edit")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid MemberAccount memberAccount, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("memberAccount", memberAccount);
			redirectAttributes.addFlashAttribute("message", "填入的数据有误！");
		}
		
		int res = memberService.updateByPrimaryKeySelective(memberAccount);
		if(res==1){
			redirectAttributes.addFlashAttribute("memberAccount", memberAccount);
			redirectAttributes.addFlashAttribute("message", "保存成功！");
		}
		redirectAttributes.addAttribute("operation", "edit");
		return "redirect:/memberAccount/edit/"+memberAccount.getId();
	}
}
