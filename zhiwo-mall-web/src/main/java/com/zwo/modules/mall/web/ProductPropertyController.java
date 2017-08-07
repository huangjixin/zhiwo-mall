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

import com.zwo.modules.mall.domain.PrProductProperty;
import com.zwo.modules.mall.service.IPrProductPropertyService;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("productProperty")
@Lazy(true)
public class ProductPropertyController extends BaseController<PrProductProperty> {
	@Autowired
	@Lazy(true)
	private IPrProductPropertyService productPropertyService;
	
	/*@Autowired
	@Lazy(true)*/
	private RedisTemplate redisTemplate = SpringContextHolder.getBean("redisTemplate");
	
	private static final String basePath = "views/mall/productProperty/";
	
	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath+"productProperty_list";
	}
	
	@RequestMapping(value = {"create"},method=RequestMethod.GET)
	public String create(@Valid PrProductProperty productProperty, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("productProperty", productProperty);
		return basePath+"productProperty_edit";
	}
	  
	@RequestMapping(value = "edit",method=RequestMethod.GET)
	public String edit(@RequestParam("id") String id, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		PrProductProperty productProperty = null;
		ValueOperations<String, Object> valueOperations = null;
		if(redisTemplate!=null){
			valueOperations =redisTemplate.opsForValue();
			productProperty = (PrProductProperty) valueOperations.get(id);
		}
		
		if(productProperty==null){
			productProperty=productPropertyService.selectByPrimaryKey(id);
			if(valueOperations != null ){
				valueOperations.set(id, productProperty);
			}
		}
		
		uiModel.addAttribute("productProperty", productProperty);
		uiModel.addAttribute("operation", "edit");
		return basePath+"productProperty_edit";
	}
}
