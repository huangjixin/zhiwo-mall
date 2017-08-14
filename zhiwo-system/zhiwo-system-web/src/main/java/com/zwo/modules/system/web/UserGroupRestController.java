package com.zwo.modules.system.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.zwo.modules.system.domain.TbUserGroup;
import com.zwo.modules.system.domain.TbUserGroupCriteria;
import com.zwo.modules.system.service.ITbUserGroupService;
import com.zwotech.common.web.BaseController;

@RestController
@RequestMapping("userGroup")
@Lazy(true)
public class UserGroupRestController extends BaseController<TbUserGroup> {
	@Autowired
	@Lazy(true)
	private ITbUserGroupService userGroupService;
	
	/** 
	 * @Title: deleteById 
	 * @Description: 批量删除 
	 * @param idstring
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return String    返回类型 
	 * @throws 
	 */
	@RequiresPermissions("system:userGroup:delete")
	@RequestMapping(value = "/deleteById")
	public String deleteById(@RequestParam(value = "idstring",required=true) String idstring, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {

		if("".equals(idstring)){
			return "0";
		}
		String[] ids = idstring.split(",");
		List<String> list = Arrays.asList(ids);
		int result = userGroupService.deleteBatch(list);
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
	@RequiresPermissions("system:userGroup:delete")
	@RequestMapping(value = "/delete")
	public String delete(@RequestParam(value = "id",required=true) String id, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {
		
		int result = userGroupService.deleteByPrimaryKey(id);
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
	@RequiresPermissions("system:userGroup:view")
	@RequestMapping(value = "/show/{id}")
	public TbUserGroup getTbUserGroup(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		TbUserGroup tbuserGroup = userGroupService.selectByPrimaryKey(id);
		
		return tbuserGroup;
	}
	
	@RequiresPermissions("system:userGroup:view")
	@RequestMapping(value = "/select")
	public DatagridPage<TbUserGroup> select(@ModelAttribute PageInfo<TbUserGroup> pageInfo, @ModelAttribute TbUserGroup tbuserGroup, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		super.select(pageInfo, uiModel, httpServletRequest, httpServletResponse);
 
		TbUserGroupCriteria tbuserGroupCriteria = null;
		tbuserGroupCriteria = new TbUserGroupCriteria();
		TbUserGroupCriteria.Criteria criteria = tbuserGroupCriteria.createCriteria();
		tbuserGroupCriteria.setOrderByClause("id desc");
		if (null != tbuserGroup.getName() && !"".equals(tbuserGroup.getName())) {
			criteria.andNameLike("%" + tbuserGroup.getName() + "%");
		}
		
		pageInfo = userGroupService.selectByPageInfo(tbuserGroupCriteria, pageInfo);
		return super.setPage(pageInfo);
	}
	
	@RequestMapping(value = "listAll")
	public List<TbUserGroup> listAll(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		List<TbUserGroup> list  = userGroupService.selectByExample(null);
		return list;
	}
	
}
