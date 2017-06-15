package com.zwo.modules.mall.web;

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
import org.springframework.web.util.HtmlUtils;

import com.github.pagehelper.PageInfo2;
import com.zwo.modules.mall.domain.PrCategory;
import com.zwo.modules.mall.domain.PrProduct;
import com.zwo.modules.mall.domain.PrProductCriteria;
import com.zwo.modules.mall.service.IPrductService;
import com.zwotech.common.web.BaseController;

@RestController
@RequestMapping("product")
@Lazy(true)
public class ProductRestController extends BaseController<PrProduct> {
	@Autowired
	@Lazy(true)
	private IPrductService prductService;
	
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
		int result = prductService.deleteBatch(list);
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
	@RequestMapping(value = "/{id}")
	public PrProduct getPrProduct(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		PrProduct product = prductService.selectByPrimaryKey(id);
		
		return product;
	}
	
	@RequestMapping(value = "/select")
	@ResponseBody
	public PageInfo2<PrProduct> select(@ModelAttribute PageInfo2<PrProduct> page, @ModelAttribute PrProduct product, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		super.select(page, uiModel, httpServletRequest, httpServletResponse);

		PrProductCriteria productCriteria = null;
		productCriteria = new PrProductCriteria();
		PrProductCriteria.Criteria criteria = productCriteria.createCriteria();
		productCriteria.setOrderByClause("id desc");
		if (null != product.getName() && !"".equals(product.getName())) {
			criteria.andNameLike("%" + product.getName() + "%");
		}
		
		page = prductService.selectByPageInfo(productCriteria, page);
		return page;
	}
	

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid PrProduct product, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {

		}
		
		String res = ""+prductService.insertSelective(product);
		return res;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@Valid PrProduct product, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			
		}
		
		String res = ""+this.prductService.updateByPrimaryKeySelective(product);
		return res;
	}
}
