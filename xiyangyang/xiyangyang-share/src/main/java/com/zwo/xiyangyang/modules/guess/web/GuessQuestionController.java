/**
 * 
 */
package com.zwo.xiyangyang.modules.guess.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zwo.xiyangyang.modules.core.service.IBaseService;
import com.zwo.xiyangyang.modules.core.web.BaseController;
import com.zwo.xiyangyang.modules.guess.domain.GuessQuestion;
import com.zwo.xiyangyang.modules.guess.service.IQuestionService;

/**
 * @author 黄记新
 *
 */
@Controller
@Lazy(value=true)
@RequestMapping("gquestion")
public class GuessQuestionController extends BaseController<GuessQuestion> {

	@Autowired
	private IQuestionService questionService;

	@RequestMapping()
	@ResponseBody
	List<GuessQuestion> defaultMethod(@ModelAttribute PageInfo<GuessQuestion> pageInfo,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		List<GuessQuestion> questions = questionService.selectByExample(null, pageInfo);
		return questions;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected IBaseService<GuessQuestion> getBaseService() {
		return (IBaseService)questionService;
	}
	

	@RequestMapping("checkQuestion")
	@ResponseBody
	void checkQuestion(@RequestParam String questionId,@RequestParam String optionId,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		questionService.checkQuestion(questionId, optionId);
	}

}
