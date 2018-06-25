package com.fulan.application.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fulan.api.system.Vo.DictionaryVo;
import com.fulan.api.system.domain.Dictionary;
import com.fulan.api.system.service.DictionaryService;
import com.fulan.application.util.domain.Response;

@Controller
@RequestMapping("/manage/dictionary")
public class DictionaryController {
	
	 @Autowired
	 private DictionaryService dictionaryClient;
//	 
//	/**
//	 * 添加一个字典
//	 * @param dictionary 字典实体类
//	 * @return
//	 */
//
//	 @RequestMapping(value="/insert",method=RequestMethod.POST)
//	 @ResponseBody
//	 public Response<String> insertDictionary(@RequestBody Dictionary dictionary){
//		 return dictionaryClient.insertDictionary(dictionary);
//		
//	 }

//	 @RequestMapping(value="/insertDictionary",method=RequestMethod.POST)
//	 @ResponseBody
//	 public Response<Dictionary> insertDiction(@RequestBody Dictionary dictionary){
//		 return dictionaryClient.insertDic(dictionary);
//	 }
//	 
	 
		/**
		 * 根据主键id删除字典（post请求方式)
		 * @param id 字典主键
		 * @return
		 */ 
	@RequiresPermissions("/manage/dictionary/getDictary")
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Response<String> deleteDictionary(@RequestParam("id") Long id){
		return dictionaryClient.deleteDictionary(id);
		
	}
//	/**
//	 * 根据主键id删除字典（get请求方式)
//	 * @param id 字典主键
//	 * @return 
//	 */
//	
//	@RequestMapping(value="/deled",method=RequestMethod.GET)
//	@ResponseBody
//	public Response<String> deleta(@RequestParam("id") Long id){
//		return dictionaryClient.deleteDiction(id);
//	}
	

	/**
	 * 删除多个字典（post请求方式）
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("/manage/dictionary/getDictary")
	@RequestMapping(value="/deletes",method=RequestMethod.POST)
	@ResponseBody
	public Response<String> deletes(Long[] ids){
		return dictionaryClient.deletes(ids);
	}
	
	@RequiresPermissions("/manage/dictionary/getDictary")
	@RequestMapping(value="/updateOne" ,method = RequestMethod.POST)
	@ResponseBody
	public Response<Dictionary> updateOne(@RequestBody Dictionary dictionary){
	 return dictionaryClient.updateDictionary(dictionary);
	 
	}

	@RequiresPermissions("/manage/dictionary/getDictary")
	@RequestMapping(value="/selectOneByCode",method=RequestMethod.GET)
	@ResponseBody
	public Response<Dictionary> selectOneDict(@RequestParam("code") String code){
		return dictionaryClient.findByCodeDictionary(code);
	}
	@RequiresPermissions("/manage/dictionary/getDictary")
	@RequestMapping(value="/selectOneById",method=RequestMethod.GET)
	@ResponseBody
	public Response<Dictionary> selectOneDictary(@RequestParam("id") Long id){
		return dictionaryClient.findDictionaryById(id);
	}
	
	/**
	 * 初始化字典Dictionary页数据
	 * @return
	 */
	@RequiresPermissions("/manage/dictionary/getDictary")
	@RequestMapping(value="/getDictary",method=RequestMethod.GET)
	public ModelAndView getDictary(){
		ModelAndView mv = new ModelAndView();
		List data=dictionaryClient.infoDictionary();
		mv.addObject("data", data);
		mv.setViewName("dictionary/dictionary");
		return mv;
	}
	
	
	/**
	 * 查询所有字典数据组装树形图
	 * @return
	 */
	@RequiresPermissions("/manage/dictionary/getDictary")
	@RequestMapping(value="/ajaxSelectDictary",method=RequestMethod.GET)
	@ResponseBody
	public List<Dictionary> selectDictary(){
	
		List<Dictionary> dictionarys =  dictionaryClient.selectAll();
		return dictionarys;
	}
	@RequiresPermissions("/manage/dictionary/getDictary")
	@RequestMapping(value="/updateOrInsert" ,method = RequestMethod.POST)
	@ResponseBody
	public Response<Dictionary> upOrIn( Dictionary dictionary ,String ids ){
		if(ids != null && !"".equals(ids)){
			dictionary.setId(Long.parseLong(ids));
		}
		return dictionaryClient.updateOrInsert(dictionary);
		
	}
	
	
	@RequiresPermissions("/manage/dictionary/getDictary")
	@RequestMapping(value="/findChilds",method=RequestMethod.GET)
	@ResponseBody
	public DictionaryVo findAllChilds(@RequestParam("id") Long id){
		List<Dictionary> dictionarys=dictionaryClient.selectAllChild(id);
		Dictionary dictionary=dictionaryClient.selectDictionaryById(id);
		DictionaryVo dictionaryVo=new DictionaryVo();
		dictionaryVo.setDictionary(dictionary);
		dictionaryVo.setDictionarys(dictionarys);
		return dictionaryVo;
	}
	
	/**
	 * 根据code值查询字典对象
	 * @param id
	 * @return
	 */
	@RequiresPermissions("/manage/dictionary/getDictary")
	@RequestMapping(value="/selcetListDictionary",method=RequestMethod.GET)
	@ResponseBody
	public Dictionary selcetListDictionary(@RequestParam(value="code",required=false) String code){
		Dictionary dictionary=dictionaryClient.selcetListDictionary(code);
		return dictionary;
	}
	
	/**
	 * 根据code值查询字典子节点对象集合
	 * @param id
	 * @return
	 */
	@RequiresPermissions("/manage/dictionary/getDictary")
	@RequestMapping(value="/selcetChildListDictionary",method=RequestMethod.GET)
	@ResponseBody
	public List<Dictionary> selcetChildListDictionary(@RequestParam(value="code",required=false) String code){
		List<Dictionary> dictionary=dictionaryClient.selcetChildListDictionary(code);
		return dictionary;
	}
	
	
}
