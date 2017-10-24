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

import com.zwo.modules.mall.domain.PrImage;
import com.zwo.modules.mall.service.IPrImageService;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("prImage")
@Lazy(true)
public class PrImageController extends BaseController<PrImage> {
	@Autowired
	@Lazy(true)
	private IPrImageService prImageService;
	
	/*@Autowired
	@Lazy(true)*/
	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate;
	
	private static final String basePath = "views/mall/prImage/";
	

	public PrImageController() {
		super();
		if(SpringContextHolder.getApplicationContext().containsBean("redisTemplate")){
			redisTemplate = SpringContextHolder.getBean("redisTemplate");
		}
	}
	
	/**
	 * 默认执行方法。
	 * 
	 * @param uiModel
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping()
	String defaultMethod(Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		return list(httpServletRequest);
	}
	
	@RequestMapping(value = {"list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath+"prImage_list";
	}
	
	@RequestMapping(value = {"create"},method=RequestMethod.GET)
	public String create(@Valid PrImage prImage, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("prImage", prImage);
		return basePath+"prImage_edit";
	}
	  
	@RequestMapping(value = "edit",method=RequestMethod.GET)
	public String edit(@RequestParam("id") String id, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		PrImage prImage = null;
		/*ValueOperations<String, Object> valueOperations = null;
		if(redisTemplate!=null){
			valueOperations =redisTemplate.opsForValue();
			prImage = (PrImage) valueOperations.get(id);
		}*/
		
		if(prImage==null){
			prImage=prImageService.selectByPrimaryKey(id);
			/*if(valueOperations != null ){
				valueOperations.set(id, prImage);
			}*/
		}
		
		uiModel.addAttribute("prImage", prImage);
		uiModel.addAttribute("operation", "edit");
		return basePath+"prImage_edit";
	}
}
