package com.zwo.modules.member.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

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
import com.zwo.modules.member.domain.GuessCategory;
import com.zwo.modules.member.domain.GuessCategoryCriteria;
import com.zwo.modules.member.service.IGuessCategoryService;
import com.zwo.modules.system.domain.TbUserAssets;
import com.zwo.modules.system.domain.TbUserAssetsCriteria;
import com.zwo.modules.system.service.ITbUserAssetsService;
import com.zwotech.common.utils.TreeBuilder;
import com.zwotech.common.web.BaseController;

@RestController
@RequestMapping("guessCategory")
@Lazy(true)
public class GuessCategoryRestController extends BaseController<GuessCategory> {
	@Autowired
	@Lazy(true)
	private IGuessCategoryService guessCategoryService;
	@Autowired
	@Lazy(true)
	private ITbUserAssetsService userAssetsService;

	/**
	 * @Title: deleteById @Description: 批量删除 @param idstring @param
	 * httpServletRequest @param httpServletResponse @return String 返回类型 @throws
	 */
	@RequiresPermissions("member:guessCategory:delete")
	@RequestMapping(value = "deleteById")
	public String deleteById(@RequestParam(value = "idstring", required = true) String idstring,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

		if("".equals(idstring)){
			return "0";
		}
		String[] ids = idstring.split(",");
		List<String> list = Arrays.asList(ids);
		TbUserAssetsCriteria assetsCriteria = new TbUserAssetsCriteria();
		assetsCriteria.createCriteria().andOrgIdIn(list);
		List<TbUserAssets> userAssets = userAssetsService.selectByExample(assetsCriteria);
		for (TbUserAssets tbUserAssets : userAssets) {
			//文件
		    Path path = Paths.get(tbUserAssets.getPath());
		    Files.deleteIfExists(path);
		}
		int result = guessCategoryService.deleteBatch(list);
		return result + "";
	}

	/**
	 * @Title: deleteById @Description: 批量删除 @param idstring @param
	 * httpServletRequest @param httpServletResponse @return String 返回类型 @throws
	 */
	@RequiresPermissions("member:guessCategory:delete")
	@RequestMapping(value = "delete")
	public String delete(@RequestParam(value = "id", required = true) String id, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {

		int result = guessCategoryService.deleteByPrimaryKey(id);
		return result + "";
	}

	/**
	 * @Description: 查看详情
	 * @param id
	 * @param uiModel
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	// @RequiresPermissions("member:guessCategory:view")
	@RequestMapping(value = "/show/{id}")
	public GuessCategory getGuessCategory(@PathVariable("id") String id, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		GuessCategory guessCategory = guessCategoryService.selectByPrimaryKey(id);

		return guessCategory;
	}

	@RequiresPermissions("member:guessCategory:view")
	@RequestMapping(value = "/select")
	public DatagridPage<GuessCategory> select(@ModelAttribute PageInfo<GuessCategory> pageInfo,
			@ModelAttribute GuessCategory guessCategory, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		super.select(pageInfo, uiModel, httpServletRequest, httpServletResponse);

		GuessCategoryCriteria guessCategoryCriteria = null;
		guessCategoryCriteria = new GuessCategoryCriteria();
		GuessCategoryCriteria.Criteria criteria = guessCategoryCriteria.createCriteria();
		guessCategoryCriteria.setOrderByClause("id desc");
		if (null != guessCategory.getName() && !"".equals(guessCategory.getName())) {
			criteria.andNameLike("%" + guessCategory.getName() + "%");
		}

		pageInfo = guessCategoryService.selectByPageInfo(guessCategoryCriteria, pageInfo);
		return super.setPage(pageInfo);
	}
	

	@RequestMapping(value = "getGuessCategoryTree")
	public List<GuessCategory> getGuessCategoryTree(Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		TreeBuilder<GuessCategory> tb = new TreeBuilder<GuessCategory>();
		List<GuessCategory> list = guessCategoryService.selectByExample(null);
		list = tb.buildListToTree(list, false);
		return list;
	}
}
