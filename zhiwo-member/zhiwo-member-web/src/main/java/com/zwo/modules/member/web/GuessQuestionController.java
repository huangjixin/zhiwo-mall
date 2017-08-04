package com.zwo.modules.member.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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

import com.zwo.modules.member.domain.GuessQuestion;
import com.zwo.modules.member.service.IGuessQuestionService;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("guessQuestion")
@Lazy(true)
public class GuessQuestionController extends BaseController<GuessQuestion> {
	@Autowired
	@Lazy(true)
	private IGuessQuestionService guessQuestionService;
	
	/*@Autowired
	@Lazy(true)*/
	private RedisTemplate redisTemplate = SpringContextHolder.getBean("redisTemplate");
	
	private static final String basePath = "views/mall/guessQuestion/";
	
	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath+"guessQuestion_list";
	}
	
	@RequestMapping(value = {"create"},method=RequestMethod.GET)
	public String create(@Valid GuessQuestion guessQuestion, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("guessQuestion", guessQuestion);
		return basePath+"guessQuestion_edit";
	}
	  
	@RequestMapping(value = "edit",method=RequestMethod.GET)
	public String edit(@RequestParam("id") String id, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		GuessQuestion guessQuestion = null;
		ValueOperations<String, Object> valueOperations = null;
		if(redisTemplate!=null){
			valueOperations =redisTemplate.opsForValue();
			guessQuestion = (GuessQuestion) valueOperations.get(id);
		}
		
		if(guessQuestion==null){
			guessQuestion=guessQuestionService.selectByPrimaryKey(id);
			if(valueOperations != null ){
				valueOperations.set(id, guessQuestion);
			}
		}
		
		uiModel.addAttribute("guessQuestion", guessQuestion);
		uiModel.addAttribute("operation", "edit");
		return basePath+"guessQuestion_edit";
	}
	
	@RequestMapping(value = {"test"},method=RequestMethod.GET)
	public String test(Model uiModel,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("rawData", 123456);
		return "test";
	}
}
