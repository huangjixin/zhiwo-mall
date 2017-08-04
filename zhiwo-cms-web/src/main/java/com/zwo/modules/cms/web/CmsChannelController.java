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

import com.zwo.modules.cms.domain.CmsChannel;
import com.zwo.modules.cms.service.ICmsChannelService;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("cmsChannel")
@Lazy(true)
public class CmsChannelController extends BaseController<CmsChannel> {
	@Autowired
	@Lazy(true)
	private ICmsChannelService cmsChannelService;

	/*
	 * @Autowired
	 * 
	 * @Lazy(true)
	 */
	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate = SpringContextHolder.getBean("redisTemplate");

	private static final String basePath = "views/system/cmsChannel/";

	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath + "cmsChannel_list";
	}

	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String create(@Valid CmsChannel cmsChannel, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("cmsChannel", cmsChannel);
		return basePath + "cmsChannel_edit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(@RequestParam("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		CmsChannel cmsChannel = null;
		/*
		 * ValueOperations<String, Object> valueOperations = null;
		 * if(redisTemplate!=null){ valueOperations
		 * =redisTemplate.opsForValue(); cmsChannel = (CmsChannel)
		 * valueOperations.get(id); }
		 */

		if (cmsChannel == null) {
			cmsChannel = cmsChannelService.selectByPrimaryKey(id);
			/*
			 * if(valueOperations != null ){ valueOperations.set(id, cmsChannel);
			 * }
			 */
		}

		uiModel.addAttribute("cmsChannel", cmsChannel);
		uiModel.addAttribute("operation", "edit");
		return basePath + "cmsChannel_edit";
	}

	@RequestMapping(value = { "test" }, method = RequestMethod.GET)
	public String test(Model uiModel, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("rawData", 123456);
		return "test";
	}
}
