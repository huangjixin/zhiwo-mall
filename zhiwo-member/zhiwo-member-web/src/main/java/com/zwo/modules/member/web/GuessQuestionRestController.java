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
import com.zwo.modules.member.domain.GuessQuestion;
import com.zwo.modules.member.domain.GuessQuestionAnswer;
import com.zwo.modules.member.domain.GuessQuestionAnswerCriteria;
import com.zwo.modules.member.domain.GuessQuestionCriteria;
import com.zwo.modules.member.service.IGuessQuestionAnswerService;
import com.zwo.modules.member.service.IGuessQuestionService;
import com.zwo.modules.system.domain.TbUserAssets;
import com.zwo.modules.system.domain.TbUserAssetsCriteria;
import com.zwo.modules.system.service.ITbUserAssetsService;
import com.zwotech.common.web.BaseController;

@RestController
@RequestMapping("guessQuestion")
@Lazy(true)
public class GuessQuestionRestController extends BaseController<GuessQuestion> {
	@Autowired
	@Lazy(true)
	private IGuessQuestionAnswerService answerService;
	
	@Autowired
	@Lazy(true)
	private IGuessQuestionService guessQuestionService;
	
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
	@RequiresPermissions("member:guessQuestion:delete")
	public String deleteById(@RequestParam(value = "idstring",required=true) String idstring, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {

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
		
		int result = guessQuestionService.deleteBatch(list);
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
	@RequiresPermissions("member:guessQuestion:delete")
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
		
		int result = guessQuestionService.deleteByPrimaryKey(id);
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
	@RequiresPermissions("member:guessQuestion:view")
	public GuessQuestion getGuessQuestion(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		GuessQuestion guessQuestion = guessQuestionService.selectByPrimaryKey(id);
		
		return guessQuestion;
	}
	
	@RequestMapping(value = "/select")
	@RequiresPermissions("member:guessQuestion:view")
	public DatagridPage<GuessQuestion> select(@ModelAttribute PageInfo<GuessQuestion> pageInfo, @ModelAttribute GuessQuestion guessQuestion, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		super.select(pageInfo, uiModel, httpServletRequest, httpServletResponse);
 
		GuessQuestionCriteria guessQuestionCriteria = null;
		guessQuestionCriteria = new GuessQuestionCriteria();
		GuessQuestionCriteria.Criteria criteria = guessQuestionCriteria.createCriteria();
		guessQuestionCriteria.setOrderByClause("id desc");
		if (null != guessQuestion.getName() && !"".equals(guessQuestion.getName())) {
			criteria.andNameLike("%" + guessQuestion.getName() + "%");
		}
		
		pageInfo = guessQuestionService.selectByPageInfo(guessQuestionCriteria, pageInfo);
		return super.setPage(pageInfo);
	}
	
	 
		/**
		 * @Description: 查看详情 
		 * @param id
		 * @param uiModel
		 * @param httpServletRequest
		 * @param httpServletResponse
		 * @return
		 */
		@RequestMapping(value = "releaseAnswer")
		@RequiresPermissions("member:guessQuestionAnswer:edit")
		public String releaseAnswer(@ModelAttribute GuessQuestionAnswer questionAnswer, Model uiModel, HttpServletRequest httpServletRequest,
				HttpServletResponse httpServletResponse) {
			GuessQuestionAnswerCriteria answerCriteria = new GuessQuestionAnswerCriteria();
			answerCriteria.createCriteria().andQuestionIdEqualTo(questionAnswer.getQuestionId());
			answerService.deleteByExample(answerCriteria);
			
			questionAnswer.setId(System.currentTimeMillis()+"");
			int result = answerService.insert(questionAnswer);
			return ""+result;
		}
}
