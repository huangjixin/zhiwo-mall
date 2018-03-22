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
import com.zwo.xiyangyang.modules.cms.domain.CmsAssets;
import com.zwo.xiyangyang.modules.cms.service.ICmsAssetsService;
import com.zwo.xiyangyang.modules.core.service.IBaseService;
import com.zwo.xiyangyang.modules.core.web.BaseController;

/**
 * @author 黄记新
 *
 */
@Controller
@Lazy(value=true)
@RequestMapping("cassets")
public class CmsAssetsController extends BaseController<CmsAssets> {

	@Autowired
	private ICmsAssetsService cmsAssetsService;

	@RequestMapping()
	@ResponseBody
	List<CmsAssets> defaultMethod(@ModelAttribute PageInfo<CmsAssets> pageInfo,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		List<CmsAssets> questions = cmsAssetsService.selectByExample(null, pageInfo);
		return questions;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected IBaseService<CmsAssets> getBaseService() {
		return (IBaseService)cmsAssetsService;
	}
}
