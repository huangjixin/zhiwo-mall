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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.DatagridPage;
import com.github.pagehelper.PageInfo;
import com.zwo.modules.system.domain.TbRole;
import com.zwo.modules.system.domain.TbRoleCriteria;
import com.zwo.modules.system.service.ITbRoleService;
import com.zwotech.common.web.BaseController;

@RestController
@RequestMapping("role")
@Lazy(true)
public class RoleRestController extends BaseController<TbRole> {
	@Autowired
	@Lazy(true)
	private ITbRoleService roleService;
	
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
	@RequiresPermissions("system:role:delete")
	public String deleteById(@RequestParam(value = "idstring",required=true) String idstring, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {

		if("".equals(idstring)){
			return "0";
		}
		String[] ids = idstring.split(",");
		List<String> list = Arrays.asList(ids);
		int result = roleService.deleteBatch(list);
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
	@RequiresPermissions("system:role:delete")
	@RequestMapping(value = "/delete")
	public String delete(@RequestParam(value = "id",required=true) String id, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {
		
		int result = roleService.deleteByPrimaryKey(id);
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
	@RequiresPermissions("system:role:view")
	@RequestMapping(value = "/show/{id}")
	public TbRole getTbRole(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		TbRole tbrole = roleService.selectByPrimaryKey(id);
		
		return tbrole;
	}
	
	@RequestMapping(value = "/select")
	@ResponseBody
	public DatagridPage<TbRole> select(@ModelAttribute PageInfo<TbRole> pageInfo, @ModelAttribute TbRole tbrole, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		super.select(pageInfo, uiModel, httpServletRequest, httpServletResponse);
 
		TbRoleCriteria tbroleCriteria = null;
		tbroleCriteria = new TbRoleCriteria();
		TbRoleCriteria.Criteria criteria = tbroleCriteria.createCriteria();
		criteria.andDisabledEqualTo(false);
		tbroleCriteria.setOrderByClause("id desc");
		if (null != tbrole.getName() && !"".equals(tbrole.getName())) {
			criteria.andNameLike("%" + tbrole.getName() + "%");
		}
		
		pageInfo = roleService.selectByPageInfo(tbroleCriteria, pageInfo);
		return super.setPage(pageInfo);
	}
	
	@RequiresPermissions("system:role:view")
	@RequestMapping(value = "listAll")
	public List<TbRole> listAll(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		List<TbRole> list  = roleService.selectByExample(null);
		return list;
	}
}
