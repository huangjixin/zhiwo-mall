package com.zwo.modules.mall.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.zwo.modules.mall.domain.PrImage;
import com.zwo.modules.mall.domain.PrImageType;
import com.zwo.modules.mall.domain.PrProduct;
import com.zwo.modules.mall.domain.PrProductCriteria;
import com.zwo.modules.mall.domain.PrProductWithBLOBs;
import com.zwo.modules.mall.service.IPrImageService;
import com.zwo.modules.mall.service.IPrProductPackagePriceService;
import com.zwo.modules.mall.service.IPrProductPropertyValueService;
import com.zwo.modules.mall.service.IPrductService;
import com.zwotech.common.utils.FreeMarkerUtil;
import com.zwotech.common.web.BaseController;

@RestController
@RequestMapping("product")
@Lazy(true)
public class ProductRestController extends BaseController<PrProduct> {
	@Autowired
	@Lazy(true)
	private IPrductService prductService;
	@Autowired
	@Lazy(true)
	private IPrImageService imageService;
	
	@Autowired
	@Lazy(true)
	private IPrProductPropertyValueService productPropertyValueService;
	@Autowired
	@Lazy(true)
	private IPrProductPackagePriceService packagePriceService;
	private String replaceAll;
	

//	private RedisTemplate redisTemplate = SpringContextHolder.getBean("redisTemplate");
	/** 
	 * @Title: deleteById 
	 * @Description: 批量删除 
	 * @param idstring
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return String    返回类型 
	 * @throws 
	 */
	@RequiresPermissions("mall:product:delete")
	@RequestMapping(value = "/deleteById")
	public String deleteById(@RequestParam(value = "idstring",required=true) String idstring, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {

		String[] ids = idstring.split(",");
		List<String> list = new ArrayList<String>();
		for (String idstr : ids) {
			packagePriceService.deleteByProductId(idstr);
			productPropertyValueService.deleteByProductId(idstr);
			imageService.deletePrImageByProductId(idstr);
			list.add(idstr);
		}
		int result = prductService.deleteBatch(list);
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
	@RequiresPermissions("mall:product:delete")
	@RequestMapping(value = "delete")
	public String delete(@RequestParam(value = "id",required=true) String id, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {
		packagePriceService.deleteByProductId(id);
		productPropertyValueService.deleteByProductId(id);
		imageService.deletePrImageByProductId(id);
		
//		if(redisTemplate ==null){
//		}else{
//			redisTemplate.delete(id+"_productPackagePrices");  
//			redisTemplate.delete(id+"_productPropertyValues");
//		}
		
		int result = prductService.deleteByPrimaryKey(id);
		return result+"";
	}
	
	/**
	 * 逻辑删除
	 * @param id
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws IOException
	 */
	@RequiresPermissions("mall:product:delete")
	@RequestMapping(value = "logicDelete")
	public String logicDelete(@RequestParam(value = "id",required=true) String id, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {
		int result = 0;
		PrProduct product = prductService.selectByPrimaryKey(id);
		if(product!=null){
			PrProductWithBLOBs product1 =  new PrProductWithBLOBs();
			product1.setId(product.getId());
			product1.setDisable(false);
			result = prductService.updateByPrimaryKeySelective(product1);
		}
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
	@RequiresPermissions("mall:product:view")
	@RequestMapping(value = "/show/{id}")
	public PrProduct getPrProduct(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		PrProduct product = prductService.selectByPrimaryKey(id);
		
		return product;
	}
	
	@RequiresPermissions("mall:product:view")
	@RequestMapping(value = "/select")
	public DatagridPage<PrProduct> select(@ModelAttribute PageInfo<PrProduct> pageInfo, @ModelAttribute PrProduct product, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		super.select(pageInfo, uiModel, httpServletRequest, httpServletResponse);
 
		PrProductCriteria productCriteria = null;
		productCriteria = new PrProductCriteria();
		PrProductCriteria.Criteria criteria = productCriteria.createCriteria();
		productCriteria.setOrderByClause("id desc");
		if (null != product.getName() && !"".equals(product.getName())) {
			criteria.andNameLike("%" + product.getName() + "%");
		}
		if (null != product.getCheckStatus() && !"".equals(product.getCheckStatus())) {
			criteria.andCheckStatusEqualTo(product.getCheckStatus());
		}
		
		if (null != product.getDisable() && !"".equals(product.getDisable())) {
			criteria.andDisableEqualTo(product.getDisable());
		}
		
		if (null != product.getStatus() && !"".equals(product.getStatus())) {
			criteria.andStatusEqualTo(product.getStatus());
		}
		
		pageInfo = prductService.selectByPageInfo(productCriteria, pageInfo);
		return super.setPage(pageInfo);
	}
	
	@RequestMapping(value = "selectByProductId")
	public List<PrImage> selectByProductId(@RequestParam String productId,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
//		List<PrImage> list = prductService.selectByProductId(productId,false);
		List<PrImage> list = imageService.selectByProductId(productId,PrImageType.PROP);
		return list;
	}

	@RequestMapping(value = "selectSwiperImages")
	public List<PrImage> selectSwiperImages(@RequestParam String productId,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//		List<PrImage> list = prductService.selectByProductId(productId,true);
		List<PrImage> list = imageService.selectByProductId(productId,PrImageType.SWIPER);;
		return list;
	}

	@RequestMapping(value = "setStatus")
	public int setStatus(@RequestParam String id,@RequestParam String status,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		PrProductWithBLOBs product = new PrProductWithBLOBs();
		product.setStatus(status);
		product.setId(id);
		int result = prductService.updateByPrimaryKeySelective(product);

		return result;
	}
	

	@RequestMapping(value = "setDisable")
	public int setDisable(@RequestParam String id,@RequestParam boolean disable,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		PrProductWithBLOBs product = new PrProductWithBLOBs();
		product.setDisable(disable);;
		product.setId(id);
		int result = prductService.updateByPrimaryKeySelective(product);

		return result;
	}
	
	@RequestMapping(value = "geneProductHtm")
	public void geneProductHtm(@RequestParam String id){
		String cpath = this.getClass().getResource("/").getPath().replaceFirst("/", "");
		String webappRoot = cpath.replaceAll("/WEB-INF/classes/", "");
		String templatePath = webappRoot+ File.separator+"template";
		String templateName = "goodsDetail.ftl";
		String fileName =webappRoot+ File.separator+"goodsDetail"+ File.separator+id+".htm";
		System.out.println(cpath);
		/*PrProductWithBLOBs product = prductService.selectByPrimKey(id);
		String path = System.getProperty("webapp.root");
		path += File.separator+"WEB-INF" + File.separator+"views"+ File.separator+"goods";
		String templateName = "goodsDetail.ftl";
		String fileName = product.getId()+".jsp";*/
		Map root = new HashMap<>();
		root.put("rawData", "");
		FreeMarkerUtil.analysisTemplate(templatePath, templateName, fileName, root);
	}
}
