package com.zwo.modules.member.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.pagehelper.DatagridPage;
import com.github.pagehelper.PageInfo;
import com.zwo.modules.member.domain.GuessQuestionOptions;
import com.zwo.modules.member.domain.GuessQuestionOptionsCriteria;
import com.zwo.modules.member.service.IGuessQuestionOptionsService;
import com.zwo.modules.system.domain.TbUserAssets;
import com.zwo.modules.system.domain.TbUserAssetsCriteria;
import com.zwo.modules.system.service.ITbUserAssetsService;
import com.zwotech.common.web.BaseController;

@RestController
@RequestMapping("guessQuestionOptions")
@Lazy(true)
public class GuessQuestionOptionsRestController extends BaseController<GuessQuestionOptions> {
	@Autowired
	@Lazy(true)
	private IGuessQuestionOptionsService guessQuestionOptionsService;
	
	@Autowired
	@Lazy(true)
	private ITbUserAssetsService userAssetsService;
	
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
	@RequiresPermissions("member:guessQuestionOptions:delete")
	public String deleteById(@RequestParam(value = "idstring",required=true) String idstring, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {

		if("".equals(idstring)){
			return "0";
		}
		String[] ids = idstring.split(",");
		List<String> list = Arrays.asList(ids);
		int result = guessQuestionOptionsService.deleteBatch(list);
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
	@RequiresPermissions("member:guessQuestionOptions:delete")
	public String delete(@RequestParam(value = "id",required=true) String id, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {
		TbUserAssetsCriteria assetsCriteria = new TbUserAssetsCriteria();
		assetsCriteria.createCriteria().andOrgIdEqualTo(id);
		List<TbUserAssets> userAssets = userAssetsService.selectByExample(assetsCriteria);
		for (TbUserAssets tbUserAssets : userAssets) {
			//文件
		    Path path = Paths.get(tbUserAssets.getPath());
		    Files.deleteIfExists(path);
		}
		
		int result = guessQuestionOptionsService.deleteByPrimaryKey(id);
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
	@RequiresPermissions("member:guessQuestionOptions:view")
	public GuessQuestionOptions getGuessQuestionOptions(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		GuessQuestionOptions guessQuestionOptions = guessQuestionOptionsService.selectByPrimaryKey(id);
		
		return guessQuestionOptions;
	}
	
	@RequiresPermissions("member:guessQuestionOptions:create")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid GuessQuestionOptions tbguessQuestionOptions, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {

		}
		
		int res = guessQuestionOptionsService.insertSelective(tbguessQuestionOptions);		
		return ""+res;
	}
	
	@RequiresPermissions("member:guessQuestionOptions:edit")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid GuessQuestionOptions guessQuestionOptions, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			
		}
		
		int res = this.guessQuestionOptionsService.updateByPrimaryKeySelective(guessQuestionOptions);
		
		return res+"";
	}
	
	@RequestMapping(value = "selectByQuestionId")
	@RequiresPermissions("member:guessQuestionOptions:view")
	public DatagridPage<GuessQuestionOptions> select(@ModelAttribute PageInfo<GuessQuestionOptions> pageInfo, @ModelAttribute GuessQuestionOptions guessQuestionOptions, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		super.select(pageInfo, uiModel, httpServletRequest, httpServletResponse);
 
		GuessQuestionOptionsCriteria guessQuestionOptionsCriteria = null;
		guessQuestionOptionsCriteria = new GuessQuestionOptionsCriteria();
		GuessQuestionOptionsCriteria.Criteria criteria = guessQuestionOptionsCriteria.createCriteria();
		guessQuestionOptionsCriteria.setOrderByClause("id desc");
		if (null != guessQuestionOptions.getName() && !"".equals(guessQuestionOptions.getName())) {
			criteria.andNameLike("%" + guessQuestionOptions.getName() + "%");
		}
		if (null != guessQuestionOptions.getGuessQuestionId() && !"".equals(guessQuestionOptions.getGuessQuestionId())) {
			criteria.andGuessQuestionIdEqualTo(guessQuestionOptions.getGuessQuestionId());
		}
		
		pageInfo = guessQuestionOptionsService.selectByPageInfo(guessQuestionOptionsCriteria, pageInfo);
		return super.setPage(pageInfo);
	}
	

}
