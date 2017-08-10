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
import com.zwo.modules.cms.domain.CmsDocument;
import com.zwo.modules.cms.domain.CmsDocumentCriteria;
import com.zwo.modules.cms.service.ICmsDocumentService;
import com.zwotech.common.web.BaseController;

@RestController
@RequestMapping("cmsDocument")
@Lazy(true)
public class CmsDocumentRestController extends BaseController<CmsDocument> {
	@Autowired
	@Lazy(true)
	private ICmsDocumentService cmsDocumentService;
	
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
		int result = cmsDocumentService.deleteBatch(list);
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
		
		int result = cmsDocumentService.deleteByPrimaryKey(id);
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
	public CmsDocument getCmsDocument(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		CmsDocument tbcmsDocument = cmsDocumentService.selectByPrimaryKey(id);
		
		return tbcmsDocument;
	}
	
	@RequestMapping(value = "/select")
	@ResponseBody
	public DatagridPage<CmsDocument> select(@ModelAttribute PageInfo<CmsDocument> pageInfo, @ModelAttribute CmsDocument tbcmsDocument, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		super.select(pageInfo, uiModel, httpServletRequest, httpServletResponse);
 
		CmsDocumentCriteria tbcmsDocumentCriteria = null;
		tbcmsDocumentCriteria = new CmsDocumentCriteria();
		CmsDocumentCriteria.Criteria criteria = tbcmsDocumentCriteria.createCriteria();
		tbcmsDocumentCriteria.setOrderByClause("id desc");
		if (null != tbcmsDocument.getName() && !"".equals(tbcmsDocument.getName())) {
			criteria.andNameLike("%" + tbcmsDocument.getName() + "%");
		}
		
		pageInfo = cmsDocumentService.selectByPageInfo(tbcmsDocumentCriteria, pageInfo);
		return super.setPage(pageInfo);
	}
}
