package com.zwo.modules.mall.web;

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

import com.zwo.modules.mall.domain.PrProductPropertyValue;
import com.zwo.modules.mall.service.IPrProductPropertyValueService;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("productPropertyValue")
@Lazy(true)
public class ProductPropertyValueController extends BaseController<PrProductPropertyValue> {
	@Autowired
	@Lazy(true)
	private IPrProductPropertyValueService productPropertyValueService;
	
	/*@Autowired
	@Lazy(true)*/
	private RedisTemplate redisTemplate = SpringContextHolder.getBean("redisTemplate");
	
	private static final String basePath = "views/mall/productPropertyValue/";
	
	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath+"productPropertyValue_list";
	}
	
	@RequestMapping(value = {"create"},method=RequestMethod.GET)
	public String create(@Valid PrProductPropertyValue productPropertyValue, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("productPropertyValue", productPropertyValue);
		return basePath+"productPropertyValue_edit";
	}
	  
	@RequestMapping(value = "edit",method=RequestMethod.GET)
	public String edit(@RequestParam("id") String id, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		PrProductPropertyValue productPropertyValue = null;
		ValueOperations<String, Object> valueOperations = null;
		if(redisTemplate!=null){
			valueOperations =redisTemplate.opsForValue();
			productPropertyValue = (PrProductPropertyValue) valueOperations.get(id);
		}
		
		if(productPropertyValue==null){
			productPropertyValue=productPropertyValueService.selectByPrimaryKey(id);
			if(valueOperations != null ){
				valueOperations.set(id, productPropertyValue);
			}
		}
		
		uiModel.addAttribute("productPropertyValue", productPropertyValue);
		uiModel.addAttribute("operation", "edit");
		return basePath+"productPropertyValue_edit";
	}
	
	@RequestMapping(value = {"test"},method=RequestMethod.GET)
	public String test(Model uiModel,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("rawData", 123456);
		return "test";
	}
}
