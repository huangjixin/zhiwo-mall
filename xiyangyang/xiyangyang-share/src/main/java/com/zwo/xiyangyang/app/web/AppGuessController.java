package com.zwo.xiyangyang.app.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zwo.xiyangyang.modules.guess.domain.GuessOptions;
import com.zwo.xiyangyang.modules.guess.domain.GuessQuestion;
import com.zwo.xiyangyang.modules.guess.service.IOptionsService;
import com.zwo.xiyangyang.modules.guess.service.IQuestionService;

import tk.mybatis.mapper.entity.Example;

/**
 * 前端应用竞猜控制器。
 * 
 * @author 黄记新
 *
 */
@Controller
@RequestMapping("appguess")
public class AppGuessController {
	@Autowired
	private IQuestionService questionService;
	@Autowired
	private IOptionsService optionsService;

	@RequestMapping()
	@ResponseBody
	List<GuessQuestion> defaultMethod(PageInfo<GuessQuestion> pageInfo,HttpServletRequest httpServletRequest, 
			HttpServletResponse httpServletResponse) {
		Example example = new Example(GuessQuestion.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andGreaterThanOrEqualTo("questionEndTime", new Date());
        
		List<GuessQuestion> list = questionService.selectByExample(example, pageInfo);
		for (GuessQuestion guessQuestion : list) {
			List<GuessOptions> options = optionsService.selectByQuestionId(guessQuestion.getId());
			guessQuestion.setGuessOptions(options);
		}
		return list;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
