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

import com.zwo.modules.cms.domain.CmsDocument;
import com.zwo.modules.cms.domain.CmsDocumentWithBLOBs;
import com.zwo.modules.cms.service.ICmsDocumentService;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("cmsDocument")
@Lazy(true)
public class CmsDocumentController extends BaseController<CmsDocument> {
	@Autowired
	@Lazy(true)
	private ICmsDocumentService documentService;

	private static final String basePath = "views/cms/document/";

	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath + "document_list";
	}
	
//	@RequiresPermissions("system:document:create")
	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String tocreate(@Valid CmsDocumentWithBLOBs document, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("document", document);
		return basePath + "document_edit";
	}

//	@RequiresPermissions("system:document:view")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		CmsDocumentWithBLOBs document = documentService.selectByPrimKey(id);

		uiModel.addAttribute("document", document);
		uiModel.addAttribute("operation", "edit");
		return basePath + "document_edit";
	}
	
//	@RequiresPermissions("system:document:create")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid CmsDocumentWithBLOBs tbdocument, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {

		}
		
		int res = documentService.insertSelective(tbdocument);
		if(res==1){
			redirectAttributes.addFlashAttribute("document", tbdocument);
			redirectAttributes.addFlashAttribute("message", "保存用户成功！");
		}
		
		return "redirect:/document/create";
	}
	 
//	@RequiresPermissions("system:document:edit")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid CmsDocumentWithBLOBs document, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			
		}
		
		int res = this.documentService.updateByPrimaryKeySelective(document);
		if(res==1){
			redirectAttributes.addFlashAttribute("document", document);
			redirectAttributes.addFlashAttribute("message", "保存用户成功！");
		}
		uiModel.addAttribute("document", document);
		uiModel.addAttribute("operation", "edit");
		return basePath + "document_edit";
	}
}
