package com.zwo.modules.shop.web;

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
import com.zwo.modules.shop.domain.ShopCategory;
import com.zwo.modules.shop.domain.ShopCategoryCriteria;
import com.zwo.modules.shop.service.IShopCategoryService;
import com.zwotech.common.web.BaseController;

@RestController
@RequestMapping("shopCategory")
@Lazy(true)
public class ShopCategoryRestController extends BaseController<ShopCategory> {
	@Autowired
	@Lazy(true)
	private IShopCategoryService shopCategoryService;
	
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
		int result = shopCategoryService.deleteBatch(list);
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
		
		int result = shopCategoryService.deleteByPrimaryKey(id);
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
	public ShopCategory getShopCategory(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		ShopCategory tbshopCategory = shopCategoryService.selectByPrimaryKey(id);
		
		return tbshopCategory;
	}
	
	@RequestMapping(value = "/select")
	@ResponseBody
	public DatagridPage<ShopCategory> select(@ModelAttribute PageInfo<ShopCategory> pageInfo, @ModelAttribute ShopCategory tbshopCategory, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		super.select(pageInfo, uiModel, httpServletRequest, httpServletResponse);
 
		ShopCategoryCriteria tbshopCategoryCriteria = null;
		tbshopCategoryCriteria = new ShopCategoryCriteria();
		ShopCategoryCriteria.Criteria criteria = tbshopCategoryCriteria.createCriteria();
		tbshopCategoryCriteria.setOrderByClause("id desc");
		if (null != tbshopCategory.getName() && !"".equals(tbshopCategory.getName())) {
			criteria.andNameLike("%" + tbshopCategory.getName() + "%");
		}
		
		pageInfo = shopCategoryService.selectByPageInfo(tbshopCategoryCriteria, pageInfo);
		return super.setPage(pageInfo);
	}
}
