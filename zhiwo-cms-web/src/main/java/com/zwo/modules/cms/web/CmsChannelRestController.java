package com.zwo.modules.cms.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.DatagridPage;
import com.github.pagehelper.PageInfo;
import com.zwo.modules.cms.domain.CmsChannel;
import com.zwo.modules.cms.domain.CmsChannelCriteria;
import com.zwo.modules.cms.service.ICmsChannelService;
import com.zwotech.common.utils.TreeBuilder;
import com.zwotech.common.web.BaseController;

@RestController
@RequestMapping("channel")
@Lazy(true)
public class CmsChannelRestController extends BaseController<CmsChannel> {
	@Autowired
	@Lazy(true)
	private ICmsChannelService cmsChannelService;
	
	/** 
	 * @Title: deleteById 
	 * @Description: 批量删除 
	 * @param idstring
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return String    返回类型 
	 * @throws 
	 */
	@RequestMapping(value = "/deleteById")
	public String deleteById(@RequestParam(value = "idstring",required=true) String idstring, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {

		String[] ids = idstring.split(",");
		List<String> list = new ArrayList<String>();
		for (String idstr : ids) {
			list.add(idstr);
		}
		int result = cmsChannelService.deleteBatch(list);
		return result+"";
	}
	
	/** 
	 * @Title: deleteById 
	 * @Description: 批量删除 
	 * @param idstring
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return String    返回类型 
	 * @throws 
	 */
	@RequestMapping(value = "/delete")
	public String delete(@RequestParam(value = "id",required=true) String id, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {
		
		int result = cmsChannelService.deleteByPrimaryKey(id);
		return result+"";
	}
	 
	/**
	 * @Description: 查看详情 
	 * @param id
	 * @param uiModel
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@RequestMapping(value = "/show/{id}")
	public CmsChannel getCmsChannel(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		CmsChannel tbcmsChannel = cmsChannelService.selectByPrimaryKey(id);
		
		return tbcmsChannel;
	}
	
	@RequestMapping(value = "/select")
	@ResponseBody
	public DatagridPage<CmsChannel> select(@ModelAttribute PageInfo<CmsChannel> pageInfo, @ModelAttribute CmsChannel tbcmsChannel, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		super.select(pageInfo, uiModel, httpServletRequest, httpServletResponse);
 
		CmsChannelCriteria tbcmsChannelCriteria = null;
		tbcmsChannelCriteria = new CmsChannelCriteria();
		CmsChannelCriteria.Criteria criteria = tbcmsChannelCriteria.createCriteria();
		tbcmsChannelCriteria.setOrderByClause("id desc");
		if (null != tbcmsChannel.getName() && !"".equals(tbcmsChannel.getName())) {
			criteria.andNameLike("%" + tbcmsChannel.getName() + "%");
		}
		
		pageInfo = cmsChannelService.selectByPageInfo(tbcmsChannelCriteria, pageInfo);
		return super.setPage(pageInfo);
	}
	
	@RequestMapping(value = "getChannelTree")
	public List<CmsChannel> getResourcesCheckboxTree(Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		TreeBuilder<CmsChannel> tb = new TreeBuilder<CmsChannel>();
		List<CmsChannel> list = cmsChannelService.selectByExample(null);
		list = tb.buildListToTree(list, false);
		return list;
	}
}
