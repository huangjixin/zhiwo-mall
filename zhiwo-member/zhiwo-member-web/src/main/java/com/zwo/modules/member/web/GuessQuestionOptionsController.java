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

import com.zwo.modules.member.domain.GuessQuestionOptions;
import com.zwo.modules.member.service.IGuessQuestionOptionsService;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("guessQuestionOptions")
@Lazy(true)
public class GuessQuestionOptionsController extends BaseController<GuessQuestionOptions> {
	@Autowired
	@Lazy(true)
	private IGuessQuestionOptionsService guessQuestionOptionsService;
	
	/*@Autowired
	@Lazy(true)*/
	private RedisTemplate redisTemplate = SpringContextHolder.getBean("redisTemplate");
	
	private static final String basePath = "views/mall/guessQuestionOptions/";
	
	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath+"guessQuestionOptions_list";
	}
	
	@RequestMapping(value = {"create"},method=RequestMethod.GET)
	public String create(@Valid GuessQuestionOptions guessQuestionOptions, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("guessQuestionOptions", guessQuestionOptions);
		return basePath+"guessQuestionOptions_edit";
	}
	  
	@RequestMapping(value = "edit",method=RequestMethod.GET)
	public String edit(@RequestParam("id") String id, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		GuessQuestionOptions guessQuestionOptions = null;
		ValueOperations<String, Object> valueOperations = null;
		if(redisTemplate!=null){
			valueOperations =redisTemplate.opsForValue();
			guessQuestionOptions = (GuessQuestionOptions) valueOperations.get(id);
		}
		
		if(guessQuestionOptions==null){
			guessQuestionOptions=guessQuestionOptionsService.selectByPrimaryKey(id);
			if(valueOperations != null ){
				valueOperations.set(id, guessQuestionOptions);
			}
		}
		
		uiModel.addAttribute("guessQuestionOptions", guessQuestionOptions);
		uiModel.addAttribute("operation", "edit");
		return basePath+"guessQuestionOptions_edit";
	}
	
	@RequestMapping(value = {"test"},method=RequestMethod.GET)
	public String test(Model uiModel,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("rawData", 123456);
		return "test";
	}
}
