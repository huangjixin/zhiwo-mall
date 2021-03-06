package com.zwo.modules.system.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.zwo.modules.system.domain.TbOrg;
import com.zwo.modules.system.domain.TbOrgCriteria;
import com.zwo.modules.system.service.ITbOrgService;
import com.zwotech.common.web.BaseController;

@RestController
@RequestMapping("org")
@Lazy(true)
public class OrgRestController extends BaseController<TbOrg> {
	@Autowired
	@Lazy(true)
	private ITbOrgService orgService;
	
	/** 
	 * @Title: deleteById 
	 * @Description: 批量删除 
	 * @param idstring
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return String    返回类型 
	 * @throws 
	 */
	@RequiresPermissions("system:org:delete")
	@RequestMapping(value = "/deleteById")
	public String deleteById(@RequestParam(value = "idstring",required=true) String idstring, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {

		if("".equals(idstring)){
			return "0";
		}
		String[] ids = idstring.split(",");
		List<String> list = Arrays.asList(ids);
		int result = orgService.deleteBatch(list);
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
	@RequiresPermissions("system:org:delete")
	@RequestMapping(value = "/delete")
	public String delete(@RequestParam(value = "id",required=true) String id, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {
		
		int result = orgService.deleteByPrimaryKey(id);
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
	@RequiresPermissions("system:org:view")
	@RequestMapping(value = "/show/{id}")
	public TbOrg getTbOrg(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		TbOrg tborg = orgService.selectByPrimaryKey(id);
		
		return tborg;
	}
	
	@RequiresPermissions("system:org:view")
	@RequestMapping(value = "/select")
	public DatagridPage<TbOrg> select(@ModelAttribute PageInfo<TbOrg> pageInfo, @ModelAttribute TbOrg tborg, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		super.select(pageInfo, uiModel, httpServletRequest, httpServletResponse);
 
		TbOrgCriteria tborgCriteria = null;
		tborgCriteria = new TbOrgCriteria();
		TbOrgCriteria.Criteria criteria = tborgCriteria.createCriteria();
		tborgCriteria.setOrderByClause("id desc");
		if (null != tborg.getName() && !"".equals(tborg.getName())) {
			criteria.andNameLike("%" + tborg.getName() + "%");
		}
		
		pageInfo = orgService.selectByPageInfo(tborgCriteria, pageInfo);
		return super.setPage(pageInfo);
	}
	
}
