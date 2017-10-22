package com.zwo.modules.cms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zwo.modules.cms.domain.CmsAssets;
import com.zwo.modules.cms.service.ICmsAssetsService;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("cmsAssets")
@Lazy(true)
public class CmsAssetsController extends BaseController<CmsAssets> {
//	@Autowired
//	@Lazy(true)
	private ICmsAssetsService assetsService = SpringContextHolder.getBean("cmsAssetsServiceImpl");

	private static final String basePath = "views/cms/cmsAssets/";

	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath + "cmsAssets_list";
	}


//	@RequiresPermissions("cms:assets:create")
	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String tocreate(@Valid CmsAssets cmsAssets, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("cmsAssets", cmsAssets);
		return basePath + "cmsAssets_edit";
	}

//	@RequiresPermissions("cms:assets:view")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		CmsAssets cmsAssets = assetsService.selectByPrimaryKey(id);

		uiModel.addAttribute("cmsAssets", cmsAssets);
		uiModel.addAttribute("operation", "edit");
		return basePath + "cmsAssets_edit";
	}
	
//	@RequiresPermissions("cms:assets:create")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid CmsAssets cmsAssets, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("cmsAssets", cmsAssets);
			redirectAttributes.addFlashAttribute("message", "数据绑定有误！");
			return "redirect:/cmsAssets/create";
		}
		
		int res = assetsService.insertSelective(cmsAssets);
		if(res!=0){
			redirectAttributes.addFlashAttribute("cmsAssets", cmsAssets);
			redirectAttributes.addFlashAttribute("message", "保存成功！");
		}
		
		return "redirect:/cmsAssets/create";
	}
	 
//	@RequiresPermissions("cms:assets:edit")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid CmsAssets cmsAssets, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("cmsAssets", cmsAssets);
			redirectAttributes.addFlashAttribute("message", "填入的数据有误！");
		}
		
		int res = assetsService.updateByPrimaryKeySelective(cmsAssets);
		if(res==1){
			redirectAttributes.addFlashAttribute("cmsAssets", cmsAssets);
			redirectAttributes.addFlashAttribute("message", "保存成功！");
		}
		redirectAttributes.addAttribute("operation", "edit");
		return "redirect:/cmsAssets/edit/"+cmsAssets.getId();
	}
}
