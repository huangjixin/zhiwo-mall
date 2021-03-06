package com.zwo.modules.member.web;

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
import com.zwo.modules.member.domain.Member;
import com.zwo.modules.member.domain.MemberCriteria;
import com.zwo.modules.member.service.IMemberService;
import com.zwotech.common.web.BaseController;

@RestController
@RequestMapping("member")
@Lazy(true)
public class MemberRestController extends BaseController<Member> {
	@Autowired
	@Lazy(true)
	private IMemberService memberService;
	
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
	@RequiresPermissions("member:member:delete")
	public String deleteById(@RequestParam(value = "idstring",required=true) String idstring, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {

		String[] ids = idstring.split(",");
		List<String> list = Arrays.asList(ids);
		int result = memberService.deleteBatch(list);
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
	@RequiresPermissions("member:member:delete")
	public String delete(@RequestParam(value = "id",required=true) String id, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {
		
		int result = memberService.deleteByPrimaryKey(id);
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
	@RequiresPermissions("member:member:view")
	public Member getMember(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		Member member = memberService.selectByPrimaryKey(id);
		
		return member;
	}
	
	@RequestMapping(value = "/select")
	@RequiresPermissions("member:member:view")
	public DatagridPage<Member> select(@ModelAttribute PageInfo<Member> pageInfo, @ModelAttribute Member member, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		super.select(pageInfo, uiModel, httpServletRequest, httpServletResponse);
 
		MemberCriteria memberCriteria = null;
		memberCriteria = new MemberCriteria();
		MemberCriteria.Criteria criteria = memberCriteria.createCriteria();
		memberCriteria.setOrderByClause("id desc");
		if (null != member.getUsername() && !"".equals(member.getUsername())) {
			criteria.andUsernameLike("%" + member.getUsername() + "%");
		}
		if (null != member.getMobilPhone() && !"".equals(member.getMobilPhone())) {
			criteria.andMobilPhoneLike("%" + member.getMobilPhone() + "%");
		}
		
		pageInfo = memberService.selectByPageInfo(memberCriteria, pageInfo);
		return super.setPage(pageInfo);
	}
	
}
