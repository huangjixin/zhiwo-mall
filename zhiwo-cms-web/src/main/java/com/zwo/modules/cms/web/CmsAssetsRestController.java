package com.zwo.modules.cms.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import com.zwo.modules.cms.domain.CmsAssets;
import com.zwo.modules.cms.domain.CmsAssetsCriteria;
import com.zwo.modules.cms.service.ICmsAssetsService;
import com.zwotech.common.web.BaseController;

@RestController
@RequestMapping("cmsAssets")
@Lazy(true)
public class CmsAssetsRestController extends BaseController<CmsAssets> {
	@Autowired
	@Lazy(true)
	private ICmsAssetsService cmsAssetsService;
	
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
		int result = cmsAssetsService.deleteBatch(list);
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
		
		int result = cmsAssetsService.deleteByPrimaryKey(id);
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
	public CmsAssets getCmsAssets(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		CmsAssets tbcmsAssets = cmsAssetsService.selectByPrimaryKey(id);
		
		return tbcmsAssets;
	}
	
	@RequestMapping(value = "/select")
	@ResponseBody
	public DatagridPage<CmsAssets> select(@ModelAttribute PageInfo<CmsAssets> pageInfo, @ModelAttribute CmsAssets tbcmsAssets, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		super.select(pageInfo, uiModel, httpServletRequest, httpServletResponse);
 
		CmsAssetsCriteria tbcmsAssetsCriteria = null;
		tbcmsAssetsCriteria = new CmsAssetsCriteria();
		CmsAssetsCriteria.Criteria criteria = tbcmsAssetsCriteria.createCriteria();
		tbcmsAssetsCriteria.setOrderByClause("id desc");
		if (null != tbcmsAssets.getName() && !"".equals(tbcmsAssets.getName())) {
			criteria.andNameLike("%" + tbcmsAssets.getName() + "%");
		}
		
		pageInfo = cmsAssetsService.selectByPageInfo(tbcmsAssetsCriteria, pageInfo);
		return super.setPage(pageInfo);
	}
	

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid CmsAssets tbcmsAssets, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {

		}
		
		String res = ""+cmsAssetsService.insertSelective(tbcmsAssets);
		return res;
	}
	
	@RequestMapping(value = "/testcreate", method = RequestMethod.GET)
	public String testcreate(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		CmsAssets tbcmsAssets = new CmsAssets();
		tbcmsAssets.setId(System.currentTimeMillis()+"");
		String res = ""+cmsAssetsService.insertSelective(tbcmsAssets);
		return res;
	}
	
	@RequestMapping(value = "/sendCreatProductTopic", method = RequestMethod.GET)
	public void sendCreatProductTopic(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//		prductService.sendCreateProductTopic("创建一个Topic成功。");
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@Valid CmsAssets cmsAssets, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			
		}
		
		String res = ""+this.cmsAssetsService.updateByPrimaryKeySelective(cmsAssets);
		return res;
	}
}
