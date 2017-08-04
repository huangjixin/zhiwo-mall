package com.zwo.modules.system.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.DatagridPage;
import com.github.pagehelper.PageInfo;
import com.zwo.modules.system.domain.TbResources;
import com.zwo.modules.system.domain.TbResourcesCriteria;
import com.zwo.modules.system.service.ITbResourcesService;
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
	@RequestMapping(value = "/deleteById")
	public String deleteById(@RequestParam(value = "idstring",required=true) String idstring, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {

		String[] ids = idstring.split(",");
		List<String> list = new ArrayList<String>();
		for (String idstr : ids) {
			list.add(idstr);
		}
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
	@RequestMapping(value = "/show/{id}")
	public TbResources getTbResources(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		TbResources tbresources = resourcesService.selectByPrimaryKey(id);
		
		return tbresources;
	}
	
	@RequestMapping(value = "/select")
	@ResponseBody
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
	

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid TbResources tbresources, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {

		}
		
		String res = ""+resourcesService.insertSelective(tbresources);
		return res;
	}
	
	@RequestMapping(value = "/testcreate", method = RequestMethod.GET)
	public String testcreate(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		TbResources tbresources = new TbResources();
		tbresources.setId(System.currentTimeMillis()+"");
		String res = ""+resourcesService.insertSelective(tbresources);
		return res;
	}
	
	@RequestMapping(value = "/sendCreatProductTopic", method = RequestMethod.GET)
	public void sendCreatProductTopic(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//		prductService.sendCreateProductTopic("创建一个Topic成功。");
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@Valid TbResources resources, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			
		}
		
		String res = ""+this.resourcesService.updateByPrimaryKeySelective(resources);
		return res;
	}
}