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

import com.zwo.modules.cms.domain.CmsDocument;
import com.zwo.modules.cms.service.ICmsDocumentService;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("cmsDocument")
@Lazy(true)
public class CmsDocumentController extends BaseController<CmsDocument> {
	@Autowired
	@Lazy(true)
	private ICmsDocumentService cmsDocumentService;

	/*
	 * @Autowired
	 * 
	 * @Lazy(true)
	 */
	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate = SpringContextHolder.getBean("redisTemplate");

	private static final String basePath = "views/cms/document/";

	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath + "cmsDocument_list";
	}

	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String create(@Valid CmsDocument cmsDocument, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("cmsDocument", cmsDocument);
		return basePath + "cmsDocument_edit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(@RequestParam("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		CmsDocument cmsDocument = null;
		/*
		 * ValueOperations<String, Object> valueOperations = null;
		 * if(redisTemplate!=null){ valueOperations
		 * =redisTemplate.opsForValue(); cmsDocument = (CmsDocument)
		 * valueOperations.get(id); }
		 */

		if (cmsDocument == null) {
			cmsDocument = cmsDocumentService.selectByPrimaryKey(id);
			/*
			 * if(valueOperations != null ){ valueOperations.set(id, cmsDocument);
			 * }
			 */
		}

		uiModel.addAttribute("cmsDocument", cmsDocument);
		uiModel.addAttribute("operation", "edit");
		return basePath + "cmsDocument_edit";
	}

	@RequestMapping(value = { "test" }, method = RequestMethod.GET)
	public String test(Model uiModel, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("rawData", 123456);
		return "test";
	}
}
