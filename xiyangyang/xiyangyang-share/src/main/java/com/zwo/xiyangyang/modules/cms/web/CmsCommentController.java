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
import com.zwo.xiyangyang.modules.cms.domain.CmsComment;
import com.zwo.xiyangyang.modules.cms.service.ICmsCommentService;
import com.zwo.xiyangyang.modules.core.service.IBaseService;
import com.zwo.xiyangyang.modules.core.web.BaseController;

/**
 * @author 黄记新
 *
 */
@Controller
@Lazy(value=true)
@RequestMapping("ccomment")
public class CmsCommentController extends BaseController<CmsComment> {

	@Autowired
	private ICmsCommentService commentService;

	@RequestMapping()
	@ResponseBody
	List<CmsComment> defaultMethod(@ModelAttribute PageInfo<CmsComment> pageInfo, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		List<CmsComment> categories = commentService.selectByExample(null, pageInfo);
		return categories;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected IBaseService<CmsComment> getBaseService() {
		return (IBaseService) commentService;
	}
}
