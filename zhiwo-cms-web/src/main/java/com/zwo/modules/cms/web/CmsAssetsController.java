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
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("cmsAssets")
@Lazy(true)
public class CmsAssetsController extends BaseController<CmsAssets> {
	@Autowired
	@Lazy(true)
	private ICmsAssetsService assetsService;

	private static final String basePath = "views/cms/cmsAssets/";

	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath + "cmsAssets_list";
	}

	
//	@RequiresPermissions("system:assets:create")
	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String tocreate(@Valid CmsAssets assets, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("assets", assets);
		return basePath + "assets_edit";
	}

//	@RequiresPermissions("system:assets:view")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		CmsAssets assets = assetsService.selectByPrimaryKey(id);

		uiModel.addAttribute("assets", assets);
		uiModel.addAttribute("operation", "edit");
		return basePath + "assets_edit";
	}
	
//	@RequiresPermissions("system:assets:create")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid CmsAssets tbassets, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {

		}
		
		int res = assetsService.insertSelective(tbassets);
		if(res==1){
			redirectAttributes.addFlashAttribute("assets", tbassets);
			redirectAttributes.addFlashAttribute("message", "保存用户成功！");
		}
		
		return "redirect:/assets/create";
	}
	 
//	@RequiresPermissions("system:assets:edit")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid CmsAssets assets, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			
		}
		
		int res = this.assetsService.updateByPrimaryKeySelective(assets);
		if(res==1){
			redirectAttributes.addFlashAttribute("assets", assets);
			redirectAttributes.addFlashAttribute("message", "保存用户成功！");
		}
		uiModel.addAttribute("assets", assets);
		uiModel.addAttribute("operation", "edit");
		return basePath + "assets_edit";
	}
}
