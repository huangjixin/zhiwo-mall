package com.zwo.modules.member.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.zwo.modules.member.domain.GuessQuestionOptions;
import com.zwo.modules.member.domain.GuessQuestionOptionsCriteria;
import com.zwo.modules.member.service.IGuessQuestionOptionsService;
import com.zwotech.common.web.BaseController;

@RestController
@RequestMapping("guessQuestionOptions")
@Lazy(true)
public class GuessQuestionOptionsRestController extends BaseController<GuessQuestionOptions> {
	@Autowired
	@Lazy(true)
	private IGuessQuestionOptionsService guessQuestionOptionsService;
	
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
	public String delete(@RequestParam(value = "id",required=true) String id, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {
		
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
	public GuessQuestionOptions getGuessQuestionOptions(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		GuessQuestionOptions guessQuestionOptions = guessQuestionOptionsService.selectByPrimaryKey(id);
		
		return guessQuestionOptions;
	}
	
	@RequestMapping(value = "/select")
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
		
		pageInfo = guessQuestionOptionsService.selectByPageInfo(guessQuestionOptionsCriteria, pageInfo);
		return super.setPage(pageInfo);
	}
	

}
