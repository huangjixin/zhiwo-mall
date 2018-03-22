/**
 * 
 */
package com.zwo.xiyangyang.modules.cms.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zwo.xiyangyang.modules.cms.domain.CmsDocument;
import com.zwo.xiyangyang.modules.cms.service.ICmsDocumentService;
import com.zwo.xiyangyang.modules.core.service.IBaseService;
import com.zwo.xiyangyang.modules.core.web.BaseController;

/**
 * @author 黄记新
 *
 */
@Controller
@Lazy(value=true)
@RequestMapping("cdocument")
public class CmsDocumentController extends BaseController<CmsDocument> {

	@Autowired
	private ICmsDocumentService documentService;

	@RequestMapping()
	@ResponseBody
	List<CmsDocument> defaultMethod(@ModelAttribute PageInfo<CmsDocument> pageInfo, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		List<CmsDocument> categories = documentService.selectByExample(null, pageInfo);
		return categories;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected IBaseService<CmsDocument> getBaseService() {
		return (IBaseService) documentService;
	}
}
