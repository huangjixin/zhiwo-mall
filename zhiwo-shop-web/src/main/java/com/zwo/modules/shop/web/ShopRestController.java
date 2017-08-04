package com.zwo.modules.shop.web;

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
import com.zwo.modules.shop.domain.Shop;
import com.zwo.modules.shop.domain.ShopCriteria;
import com.zwo.modules.shop.service.IShopService;
import com.zwotech.common.web.BaseController;

@RestController
@RequestMapping("shop")
@Lazy(true)
public class ShopRestController extends BaseController<Shop> {
	@Autowired
	@Lazy(true)
	private IShopService shopService;
	
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
		int result = shopService.deleteBatch(list);
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
		
		int result = shopService.deleteByPrimaryKey(id);
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
	public Shop getShop(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		Shop tbshop = shopService.selectByPrimaryKey(id);
		
		return tbshop;
	}
	
	@RequestMapping(value = "/select")
	@ResponseBody
	public DatagridPage<Shop> select(@ModelAttribute PageInfo<Shop> pageInfo, @ModelAttribute Shop tbshop, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		super.select(pageInfo, uiModel, httpServletRequest, httpServletResponse);
 
		ShopCriteria tbshopCriteria = null;
		tbshopCriteria = new ShopCriteria();
		ShopCriteria.Criteria criteria = tbshopCriteria.createCriteria();
		tbshopCriteria.setOrderByClause("id desc");
		if (null != tbshop.getName() && !"".equals(tbshop.getName())) {
			criteria.andNameLike("%" + tbshop.getName() + "%");
		}
		
		pageInfo = shopService.selectByPageInfo(tbshopCriteria, pageInfo);
		return super.setPage(pageInfo);
	}
	

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid Shop tbshop, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {

		}
		
		String res = ""+shopService.insertSelective(tbshop);
		return res;
	}
	
	@RequestMapping(value = "/testcreate", method = RequestMethod.GET)
	public String testcreate(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		Shop tbshop = new Shop();
		tbshop.setId(System.currentTimeMillis()+"");
		String res = ""+shopService.insertSelective(tbshop);
		return res;
	}
	
	@RequestMapping(value = "/sendCreatProductTopic", method = RequestMethod.GET)
	public void sendCreatProductTopic(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//		prductService.sendCreateProductTopic("创建一个Topic成功。");
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@Valid Shop shop, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			
		}
		
		String res = ""+this.shopService.updateByPrimaryKeySelective(shop);
		return res;
	}
}
