package com.zwo.modules.member.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zwo.modules.member.domain.GuessCategory;
import com.zwo.modules.member.service.IGuessCategoryService;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("guessCategory")
@Lazy(true)
public class GuessCategoryController extends BaseController<GuessCategory> {
	@Autowired
	@Lazy(true)
	private IGuessCategoryService guessCategoryService;
	
	private static final String basePath = "views/member/guess/";
	
	
	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath+"guessCategory_list";
	}
	
//	@RequiresPermissions("member:guessCategory:create")
	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String tocreate(@Valid GuessCategory guessCategory, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("guessCategory", guessCategory);
		return basePath + "guessCategory_edit";
	}

//	@RequiresPermissions("member:guessCategory:view")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		GuessCategory guessCategory = guessCategoryService.selectByPrimaryKey(id);

		uiModel.addAttribute("guessCategory", guessCategory);
		uiModel.addAttribute("operation", "edit");
		return basePath + "guessCategory_edit";
	}
	
//	@RequiresPermissions("member:guessCategory:create")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid GuessCategory guessCategory, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("guessCategory", guessCategory);
			redirectAttributes.addFlashAttribute("message", "数据绑定有误！");
			return "redirect:/guessCategory/create";
		}
		
		int res = guessCategoryService.insertSelective(guessCategory);
		if(res!=0){
			redirectAttributes.addFlashAttribute("guessCategory", guessCategory);
			redirectAttributes.addFlashAttribute("message", "保存成功！");
		}
		
		return "redirect:/guessCategory/create";
	}
	 
//	@RequiresPermissions("member:guessCategory:edit")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid GuessCategory guessCategory, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("guessCategory", guessCategory);
			redirectAttributes.addFlashAttribute("message", "填入的数据有误！");
		}
		
		int res = this.guessCategoryService.updateByPrimaryKeySelective(guessCategory);
		if(res==1){
			redirectAttributes.addFlashAttribute("guessCategory", guessCategory);
			redirectAttributes.addFlashAttribute("message", "保存成功！");
		}
		redirectAttributes.addAttribute("operation", "edit");
		return "redirect:/guessCategory/edit/"+guessCategory.getId();
	}
	
}
