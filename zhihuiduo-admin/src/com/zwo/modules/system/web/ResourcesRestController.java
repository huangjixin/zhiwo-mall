package com.zwo.modules.system.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.DatagridPage;
import com.github.pagehelper.PageInfo;
import com.zwo.modules.system.domain.TbResources;
import com.zwo.modules.system.domain.TbResourcesCriteria;
import com.zwo.modules.system.service.ITbResourcesService;
import com.zwotech.common.utils.TreeBuilder;
import com.zwotech.common.web.BaseController;

@RestController
@RequestMapping("resources")
@Lazy(true)
public class ResourcesRestController extends BaseController<TbResources> {
	@Autowired
	@Lazy(true)
	private ITbResourcesService resourcesService;
	
	/** 
	 * @Title: deleteById 
	 * @Description: 批量删除 
	 * @param idstring
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return String    返回类型 
	 * @throws 
	 */
	@RequiresPermissions("system:resources:delete")
	@RequestMapping(value = "/deleteById")
	public String deleteById(@RequestParam(value = "idstring",required=true) String idstring, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {

		if("".equals(idstring)){
			return "0";
		}
		String[] ids = idstring.split(",");
		List<String> list = Arrays.asList(ids);
		int result = resourcesService.deleteBatch(list);
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
	@RequiresPermissions("system:resources:delete")
	@RequestMapping(value = "/delete")
	public String delete(@RequestParam(value = "id",required=true) String id, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {
		
		int result = resourcesService.deleteByPrimaryKey(id);
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
	@RequiresPermissions("system:resources:view")
	@RequestMapping(value = "/show/{id}")
	public TbResources getTbResources(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		TbResources tbresources = resourcesService.selectByPrimaryKey(id);
		
		return tbresources;
	}
	
	@RequiresPermissions("system:resources:view")
	@RequestMapping(value = "/select")
	public DatagridPage<TbResources> select(@ModelAttribute PageInfo<TbResources> pageInfo, @ModelAttribute TbResources tbresources, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		super.select(pageInfo, uiModel, httpServletRequest, httpServletResponse);
 
		TbResourcesCriteria tbresourcesCriteria = null;
		tbresourcesCriteria = new TbResourcesCriteria();
		TbResourcesCriteria.Criteria criteria = tbresourcesCriteria.createCriteria();
		tbresourcesCriteria.setOrderByClause("id desc");
		if (null != tbresources.getName() && !"".equals(tbresources.getName())) {
			criteria.andNameLike("%" + tbresources.getName() + "%");
		}
		
		pageInfo = resourcesService.selectByPageInfo(tbresourcesCriteria, pageInfo);
		return super.setPage(pageInfo);
	}
	
	@RequiresPermissions("system:resources:view")
	@RequestMapping(value = "getResourcesCheckboxTree")
	public List<TbResources> getResourcesCheckboxTree(Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		TreeBuilder<TbResources> tb = new TreeBuilder<TbResources>();
		List<TbResources> list = resourcesService.selectByExample(null);
		list = tb.buildListToTree(list, false);
		return list;
	}
	
	@RequiresPermissions("system:resources:view")
	@RequestMapping(value = "getMenu")
	public List<TbResources> getMenu(Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		TreeBuilder<TbResources> tb = new TreeBuilder<TbResources>();
		List<TbResources> list = resourcesService.selectByExample(null);
		
		for (Iterator it = list.iterator(); it.hasNext();) {
			TbResources resources = (TbResources) it.next();
			if("button".equals(resources.getType().toString())){
				it.remove();
			}
		}
		list = tb.buildListToTree(list, false);
		return list;
	}
	
}
