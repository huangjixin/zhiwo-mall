package com.zwo.modules.cms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zwo.modules.cms.domain.CmsAssets;
import com.zwo.modules.cms.service.ICmsAssetsService;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("cmsAssets")
@Lazy(true)
public class CmsAssetsController extends BaseController<CmsAssets> {
	@Autowired
	@Lazy(true)
	private ICmsAssetsService cmsAssetsService;

	/*
	 * @Autowired
	 * 
	 * @Lazy(true)
	 */
	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate = SpringContextHolder.getBean("redisTemplate");

	private static final String basePath = "views/system/cmsAssets/";

	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath + "cmsAssets_list";
	}

	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String create(@Valid CmsAssets cmsAssets, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("cmsAssets", cmsAssets);
		return basePath + "cmsAssets_edit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(@RequestParam("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		CmsAssets cmsAssets = null;
		/*
		 * ValueOperations<String, Object> valueOperations = null;
		 * if(redisTemplate!=null){ valueOperations
		 * =redisTemplate.opsForValue(); cmsAssets = (CmsAssets)
		 * valueOperations.get(id); }
		 */

		if (cmsAssets == null) {
			cmsAssets = cmsAssetsService.selectByPrimaryKey(id);
			/*
			 * if(valueOperations != null ){ valueOperations.set(id, cmsAssets);
			 * }
			 */
		}

		uiModel.addAttribute("cmsAssets", cmsAssets);
		uiModel.addAttribute("operation", "edit");
		return basePath + "cmsAssets_edit";
	}

	@RequestMapping(value = { "test" }, method = RequestMethod.GET)
	public String test(Model uiModel, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("rawData", 123456);
		return "test";
	}
}
