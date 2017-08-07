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

import com.zwo.modules.member.domain.GuessCategory;
import com.zwo.modules.member.service.IGuessCategoryService;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("guessCategory")
@Lazy(true)
public class GuessCategoryController extends BaseController<GuessCategory> {
	@Autowired
	@Lazy(true)
	private IGuessCategoryService guessCategoryService;
	
	/*@Autowired
	@Lazy(true)*/
	private RedisTemplate redisTemplate = SpringContextHolder.getBean("redisTemplate");
	
	private static final String basePath = "views/mall/guessCategory/";
	
	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath+"guessCategory_list";
	}
	
	@RequestMapping(value = {"create"},method=RequestMethod.GET)
	public String create(@Valid GuessCategory guessCategory, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("guessCategory", guessCategory);
		return basePath+"guessCategory_edit";
	}
	  
	@RequestMapping(value = "edit",method=RequestMethod.GET)
	public String edit(@RequestParam("id") String id, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		GuessCategory guessCategory = null;
		ValueOperations<String, Object> valueOperations = null;
		if(redisTemplate!=null){
			valueOperations =redisTemplate.opsForValue();
			guessCategory = (GuessCategory) valueOperations.get(id);
		}
		
		if(guessCategory==null){
			guessCategory=guessCategoryService.selectByPrimaryKey(id);
			if(valueOperations != null ){
				valueOperations.set(id, guessCategory);
			}
		}
		
		uiModel.addAttribute("guessCategory", guessCategory);
		uiModel.addAttribute("operation", "edit");
		return basePath+"guessCategory_edit";
	}
	
}
