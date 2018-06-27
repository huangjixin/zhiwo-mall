/**
 * 
 */
package com.zwo.xiyangyang.modules.guess.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zwo.xiyangyang.modules.core.service.IBaseService;
import com.zwo.xiyangyang.modules.core.web.BaseController;
import com.zwo.xiyangyang.modules.guess.domain.GuessQuestion;
import com.zwo.xiyangyang.modules.guess.service.IQuestionService;

import io.swagger.annotations.Api;

/**
 * @author 黄记新
 *
 */
@Controller
@Lazy(value=true)
@RequestMapping("gquestion")
@Api("竞猜问题的api")
public class GuessQuestionController extends BaseController<GuessQuestion> {

	@Resource(name="questionServiceImpl")
	private IQuestionService questionService;

	@RequestMapping(method = RequestMethod.GET)
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
	

	@RequestMapping(value="checkQuestion",method = RequestMethod.POST)
	@ResponseBody
	void checkQuestion(@RequestParam String questionId,@RequestParam String optionId,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		questionService.checkQuestion(questionId, optionId);
	}

}
