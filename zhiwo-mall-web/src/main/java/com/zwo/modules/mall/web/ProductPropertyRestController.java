package com.zwo.modules.mall.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.DatagridPage;
import com.github.pagehelper.PageInfo;
import com.zwo.modules.mall.domain.PrProductProperty;
import com.zwo.modules.mall.domain.PrProductPropertyCriteria;
import com.zwo.modules.mall.service.IPrProductPropertyService;
import com.zwotech.common.web.BaseController;

@RestController
@RequestMapping("productProperty")
@Lazy(true)
public class ProductPropertyRestController extends BaseController<PrProductProperty> {
	@Autowired
	@Lazy(true)
	private IPrProductPropertyService productPropertyService;
	
	/** 
	 * @Title: deleteById 
	 * @Description: 批量删除 
	 * @param idstring
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return String    返回类型 
	 * @throws 
	 */
	@RequiresPermissions("mall:productProperty:delete")
	@RequestMapping(value = "deleteById")
	public String deleteById(@RequestParam(value = "idstring",required=true) String idstring, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {

		String[] ids = idstring.split(",");
		List<String> list = new ArrayList<String>();
		for (String idstr : ids) {
			list.add(idstr);
		}
		int result = productPropertyService.deleteBatch(list);
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
	@RequiresPermissions("mall:productProperty:delete")
	@RequestMapping(value = "delete")
	public String delete(@RequestParam(value = "id",required=true) String id, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {
		
		int result = productPropertyService.deleteByPrimaryKey(id);
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
	@RequiresPermissions("mall:productProperty:view")
	@RequestMapping(value = "/show/{id}")
	public PrProductProperty getPrProductProperty(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		PrProductProperty product = productPropertyService.selectByPrimaryKey(id);
		
		return product;
	}
	
	@RequiresPermissions("mall:productProperty:view")
	@RequestMapping(value = "/select")
	public DatagridPage<PrProductProperty> select(@ModelAttribute PageInfo<PrProductProperty> pageInfo, @ModelAttribute PrProductProperty product, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		super.select(pageInfo, uiModel, httpServletRequest, httpServletResponse);
 
		PrProductPropertyCriteria productCriteria = null;
		productCriteria = new PrProductPropertyCriteria();
		PrProductPropertyCriteria.Criteria criteria = productCriteria.createCriteria();
		productCriteria.setOrderByClause("id desc");
		if (null != product.getName() && !"".equals(product.getName())) {
			criteria.andNameLike("%" + product.getName() + "%");
		}
		
		pageInfo = productPropertyService.selectByPageInfo(productCriteria, pageInfo);
		return super.setPage(pageInfo);
	}
	
	@RequiresPermissions("mall:productProperty:view")
	@RequestMapping(value = "listAll")
	public List<PrProductProperty> listAll(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
		PrProductPropertyCriteria productCriteria = null;
		productCriteria = new PrProductPropertyCriteria();
		productCriteria.setOrderByClause("id asc");
		List<PrProductProperty> list = productPropertyService.selectByExample(productCriteria);
		return list;
	}
}
