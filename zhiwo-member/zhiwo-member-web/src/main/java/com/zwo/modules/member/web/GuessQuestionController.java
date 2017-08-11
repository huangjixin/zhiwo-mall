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

import com.zwo.modules.member.domain.GuessQuestion;
import com.zwo.modules.member.service.IGuessQuestionService;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("guessQuestion")
@Lazy(true)
public class GuessQuestionController extends BaseController<GuessQuestion> {
	@Autowired
	@Lazy(true)
	private IGuessQuestionService guessQuestionService;
	
	private static final String basePath = "views/mall/guessQuestion/";
	
	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath+"guessQuestion_list";
	}

//	@RequiresPermissions("member:guessQuestion:create")
	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String tocreate(@Valid GuessQuestion guessQuestion, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("guessQuestion", guessQuestion);
		return basePath + "guessQuestion_edit";
	}

//	@RequiresPermissions("member:guessQuestion:view")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		GuessQuestion guessQuestion = guessQuestionService.selectByPrimaryKey(id);

		uiModel.addAttribute("guessQuestion", guessQuestion);
		uiModel.addAttribute("operation", "edit");
		return basePath + "guessQuestion_edit";
	}
	
//	@RequiresPermissions("member:guessQuestion:create")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid GuessQuestion guessQuestion, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("guessQuestion", guessQuestion);
			redirectAttributes.addFlashAttribute("message", "数据绑定有误！");
			return "redirect:/guessQuestion/create";
		}
		
		int res = guessQuestionService.insertSelective(guessQuestion);
		if(res!=0){
			redirectAttributes.addFlashAttribute("guessQuestion", guessQuestion);
			redirectAttributes.addFlashAttribute("message", "保存成功！");
		}
		
		return "redirect:/guessQuestion/create";
	}
	 
//	@RequiresPermissions("member:guessQuestion:edit")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid GuessQuestion guessQuestion, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("guessQuestion", guessQuestion);
			redirectAttributes.addFlashAttribute("message", "填入的数据有误！");
		}
		
		int res = this.guessQuestionService.updateByPrimaryKeySelective(guessQuestion);
		if(res==1){
			redirectAttributes.addFlashAttribute("guessQuestion", guessQuestion);
			redirectAttributes.addFlashAttribute("message", "保存成功！");
		}
		redirectAttributes.addAttribute("operation", "edit");
		return "redirect:/guessQuestion/edit/"+guessQuestion.getId();
	}
}
