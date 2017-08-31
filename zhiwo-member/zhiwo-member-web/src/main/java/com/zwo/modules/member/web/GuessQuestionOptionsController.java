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

import com.zwo.modules.member.domain.GuessQuestionOptions;
import com.zwo.modules.member.service.IGuessQuestionOptionsService;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("guessQuestionOptions")
@Lazy(true)
public class GuessQuestionOptionsController extends BaseController<GuessQuestionOptions> {
	@Autowired
	@Lazy(true)
	private IGuessQuestionOptionsService guessQuestionOptionsService;
	
	private static final String basePath = "views/mall/guessQuestionOptions/";
	
	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath+"guessQuestionOptions_list";
	}
	
	@RequiresPermissions("member:guessQuestionOptions:create")
	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String tocreate(@Valid GuessQuestionOptions guessQuestionOptions, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("guessQuestionOptions", guessQuestionOptions);
		return basePath + "guessQuestionOptions_edit";
	}

	@RequiresPermissions("member:guessQuestionOptions:view")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		GuessQuestionOptions guessQuestionOptions = guessQuestionOptionsService.selectByPrimaryKey(id);

		uiModel.addAttribute("guessQuestionOptions", guessQuestionOptions);
		uiModel.addAttribute("operation", "edit");
		return basePath + "guessQuestionOptions_edit";
	}
	
}
