package com.fulan.application.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fulan.api.system.domain.Dictionary;
import com.fulan.application.service.DictionaryService;
import com.fulan.application.util.domain.Response;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
public class DictionaryManageController {
	@Autowired
	DictionaryService dictionaryService;
	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/manage/dictionary/delete",method=RequestMethod.POST)
	public Response<String> deleteDictionary(@RequestParam("id") Long id){
		try{
			List<Dictionary> dictionarys=dictionaryService.selectAll();
			for (Dictionary dic : dictionarys) {
				Long parentId=dic.getParentId();
				if(id==parentId){
					Response<String> res= new Response<>(Response.ERROR,Response.ERROR_MESSAGE);
					res.setData("存在子节点，不允许删除!");
					return res;
				}
			}
			
			
			Dictionary dictionary=new Dictionary();
			dictionary.setId(id);
			dictionary.setEnabled(Boolean.FALSE);
		    dictionaryService.updateDicParid(dictionary);
		   Response<String> res= new Response<>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
		   res.setData("删除成功");
		   return res;
		}catch(Exception e){
			e.printStackTrace();
			Response<String> res= new Response<>(Response.ERROR,Response.ERROR_MESSAGE);
			res.setData("删除失败");
			return res;
		}
	}
	

	@RequestMapping(value="/manage/dictionary/dele",method=RequestMethod.GET)
	public Response<String> deleted(@RequestParam("id") Long id){
		try{
			dictionaryService.deleteById(id);
			return new Response<>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
			}catch(Exception e){
				e.printStackTrace();
				return new Response<>(Response.ERROR,Response.ERROR_MESSAGE);
			}
	}
	
	@RequestMapping(value="/manage/dictionary/batch/delete",method=RequestMethod.POST)
	public Response<String> deleteDictionarys(@RequestParam ("id") Long[] ids){
		try{
			dictionaryService.batchDelete(ids);
			
			return new Response<>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
		}catch(Exception e){
			e.printStackTrace();
			return new Response<>(Response.ERROR,Response.ERROR_MESSAGE);
		}
		
	}
	
	@RequestMapping(value="/manage/dictionary/update",method=RequestMethod.POST)
	public Response<Dictionary> updateDictionary(@RequestBody Dictionary dictionary){
		try{
			dictionaryService.updateAllColumnById(dictionary);
			
			return new Response<Dictionary>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
		}catch(Exception e){
			e.printStackTrace();
			return new Response<>(Response.ERROR,Response.ERROR_MESSAGE);
		}
	}
	
	    @GetMapping(value = "/manage/dictionary/child/list")
	    public List<Dictionary> listChildDictionary(
	            @RequestParam(name = "id", defaultValue = "0") Long id,
	            @RequestParam(name = "includeParent", defaultValue = "false") Boolean includeParent,
	            @RequestParam(name = "layer", defaultValue = "1") Integer layer
	    ) {
	        try {
	            return dictionaryService.listChildren(id, includeParent, layer);
	        } catch (Exception e) {
	            throw e;
	        }

	    }
	
	@RequestMapping(value="/manage/dictionary/code/find",method=RequestMethod.GET)
	public Response<Dictionary> findByCode(@RequestParam("code") String code){
		
		try{
			Dictionary dictionary=dictionaryService.findByCode(code);
			Response<Dictionary> response=new Response<Dictionary>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
			response.setData(dictionary);
			return response;
			
		}catch(Exception e){
			e.printStackTrace();
			return new Response<Dictionary>(Response.ERROR,Response.ERROR_MESSAGE);
		}
	}
	
	

	@RequestMapping(value="/manage/dictionary/find",method=RequestMethod.GET)
	public Response<Dictionary> findById(@RequestParam("id") Long id){
		
		try{
			Dictionary dictionary=dictionaryService.selectById(id);
			Response<Dictionary> response=new Response<Dictionary>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
			response.setData(dictionary);
			return response;
			
		}catch(Exception e){
			e.printStackTrace();
			return new Response<Dictionary>(Response.ERROR,Response.ERROR_MESSAGE);
		}
	}
	@RequestMapping(value="/manage/dictionary/selectAll",method=RequestMethod.GET)
	public List<Dictionary> selectAll(){
		return dictionaryService.selectAll();
		
	}
	@RequestMapping(value="/manage/dictionary/updateInfo",method=RequestMethod.POST)
	public Response<Dictionary> updateInfo(@RequestBody Dictionary dictionary){
		try{
		 dictionary = dictionaryService.updateInfo(dictionary);
		 Response<Dictionary> res =new  Response<Dictionary>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
		 res.setData(dictionary);
		 return res;
		}catch(Exception e){
			e.printStackTrace();
			return new  Response<Dictionary>(Response.ERROR,Response.ERROR_MESSAGE);
		}
	}
	/**
	 * 插入或者修改一条数据
	 * @param dictionary
	 * @return
	 */
	@RequestMapping(value="/manage/dictionary/editOrInsert1",method=RequestMethod.POST)
	public Response<Dictionary> editOrUpdate(@RequestBody Dictionary dictionary){

		try{
		  Response<Dictionary> res = dictionaryService.editOrInsert(dictionary);
		  return res;
		}catch(Exception e){
			e.printStackTrace();
			
			Response<Dictionary> response= new  Response<Dictionary>(Response.ERROR,Response.ERROR_MESSAGE);
			response.setData(dictionary);
			return response;
		}
	}
	/**
	 * 查询出所有的子节点
	 * @param id
	 * @return
	 */

	/**
	 * 初始化字典Dictionary页面信息
	 */
	 @GetMapping(value = "/manage/dictionary/infoDictary")
	    public List<Dictionary> infoDictionary( ) {
	        try {
	            return dictionaryService.infoDictionary();
	        } catch (Exception e) {
	            throw e;
	        }

	    }

	@RequestMapping(value="/manage/dictionary/selectAllChild",method=RequestMethod.GET)
	public List<Dictionary> findAllChilds(Long id){
		return dictionaryService.selectByparentId(id);
	}
	
	@RequestMapping(value="/manage/dictionary/selectDictionaryById",method=RequestMethod.GET)
	public Dictionary selectDictionaryById(Long id){
		return dictionaryService.selectDictionaryById(id);
	}
	
	@GetMapping(value="/selcetListDictionary")
	public Dictionary selcetListDictionary(@RequestParam("code") String code){
		return dictionaryService.selcetListDictionary(code).get(0);
	}

	@GetMapping(value="/selcetChildListDictionary")
	public List<Dictionary> selcetChildListDictionary(@RequestParam("code") String code){
		return dictionaryService.selcetChildListDictionary(code);

	}
	
	@RequestMapping(value="/dictionary/selcetChildListDictionary",method=RequestMethod.GET)
	public List<Dictionary> selcetChildListOtherDictionary(@RequestParam("code") String code){
		return dictionaryService.selcetChildListDictionary(code);

	}
	
	
	@RequestMapping(value="/dictionary/selcetChildListOtherDictionary",method=RequestMethod.GET)
	public Response<List<Dictionary>> selcetChildListOtherDictionaryTwo(@RequestParam("code") String code){
		  List<Dictionary> dList = dictionaryService.selcetChildListDictionary(code);
		  Response <List<Dictionary>> response=new Response<List<Dictionary>>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
		  response.setData(dList);
		  return response;

	}
	
}





