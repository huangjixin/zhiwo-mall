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

import com.zwo.modules.cms.domain.CmsChannel;
import com.zwo.modules.cms.service.ICmsChannelService;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("channel")
@Lazy(true)
public class CmsChannelController extends BaseController<CmsChannel> {
	@Autowired
	@Lazy(true)
	private ICmsChannelService channelService;

	private static final String basePath = "views/cms/channel/";

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
	
	@RequestMapping(value = { "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath + "channel_list";
	}


//	@RequiresPermissions("cms:channel:create")
	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String tocreate(@Valid CmsChannel channel, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("channel", channel);
		return basePath + "channel_edit";
	}

//	@RequiresPermissions("cms:channel:view")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		CmsChannel channel = channelService.selectByPrimaryKey(id);

		uiModel.addAttribute("channel", channel);
		uiModel.addAttribute("operation", "edit");
		return basePath + "channel_edit";
	}
	
//	@RequiresPermissions("cms:channel:create")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid CmsChannel channel, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("channel", channel);
			redirectAttributes.addFlashAttribute("message", "数据绑定有误！");
			return "redirect:/channel/create";
		}
		
		int res = channelService.insertSelective(channel);
		if(res!=0){
			redirectAttributes.addFlashAttribute("channel", channel);
			redirectAttributes.addFlashAttribute("message", "保存成功！");
		}
		
		return "redirect:/channel/create";
	}
	 
//	@RequiresPermissions("cms:channel:edit")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid CmsChannel channel, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("channel", channel);
			redirectAttributes.addFlashAttribute("message", "填入的数据有误！");
		}
		
		int res = this.channelService.updateByPrimaryKeySelective(channel);
		if(res==1){
			redirectAttributes.addFlashAttribute("channel", channel);
			redirectAttributes.addFlashAttribute("message", "保存成功！");
		}
		redirectAttributes.addAttribute("operation", "edit");
		return "redirect:/channel/edit/"+channel.getId();
	}
}
