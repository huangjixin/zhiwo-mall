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
import com.zwo.modules.system.domain.TbUser;
import com.zwo.modules.system.domain.TbUserCriteria;
import com.zwo.modules.system.service.ITbUserService;
import com.zwotech.common.web.BaseController;

@RestController
@RequestMapping("user")
@Lazy(true)
public class UserRestController extends BaseController<TbUser> {
	@Autowired
	@Lazy(true)
	private ITbUserService userService;
	
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
	@RequiresPermissions("system:user:delete")
	public String deleteById(@RequestParam(value = "idstring",required=true) String idstring, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {
		if("".equals(idstring)){
			return "0";
		}
		String[] ids = idstring.split(",");
		List<String> list = Arrays.asList(ids);
		int result = userService.deleteBatch(list);
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
	@RequiresPermissions("system:user:delete")
	public String delete(@RequestParam(value = "id",required=true) String id, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {
		
		int result = userService.deleteByPrimaryKey(id);
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
	@RequiresPermissions("system:user:view")
	@RequestMapping(value = "/show/{id}")
	public TbUser getTbUser(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		TbUser tbuser = userService.selectByPrimaryKey(id);
		
		return tbuser;
	}
	
	@RequiresPermissions("system:user:view")
	@RequestMapping(value = "/select")
	@ResponseBody
	public DatagridPage<TbUser> select(@ModelAttribute PageInfo<TbUser> pageInfo, @ModelAttribute TbUser tbuser, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		super.select(pageInfo, uiModel, httpServletRequest, httpServletResponse);
 
		TbUserCriteria tbuserCriteria = null;
		tbuserCriteria = new TbUserCriteria();
		TbUserCriteria.Criteria criteria = tbuserCriteria.createCriteria();
		criteria.andDisableEqualTo(false);
		tbuserCriteria.setOrderByClause("id desc");
		if (null != tbuser.getUsername() && !"".equals(tbuser.getUsername())) {
			criteria.andUsernameLike("%" + tbuser.getUsername() + "%");
		}
		if (null != tbuser.getUsergroupId() && !"".equals(tbuser.getUsergroupId())) {
			criteria.andUsergroupIdEqualTo(tbuser.getUsergroupId());
		}
		
		
		pageInfo = userService.selectByPageInfo(tbuserCriteria, pageInfo);
		return super.setPage(pageInfo);
	}
}
