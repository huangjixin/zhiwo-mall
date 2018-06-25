package com.fulan.application.controller.systerm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.plan.vo.ClassPlanVo;
import com.fulan.api.security.domain.TagTarget;
import com.fulan.api.security.service.GroupTagService;
import com.fulan.api.system.Vo.CatagoryVo;
import com.fulan.api.system.Vo.SingleTagVo;
import com.fulan.api.system.Vo.TagTwoVo;
import com.fulan.api.system.Vo.TagVo;
import com.fulan.api.system.domain.Tag;
import com.fulan.api.system.service.TagService;
import com.fulan.application.shrio.SessionContextUtils;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

/**
 * @Author: xuanwugang
 * @Date: 2018/3/30 09:16
 */
@Controller
public class ManageTagController {

	@Autowired
    private TagService tagService;
	
	@Autowired
    private GroupTagService groupTagService;
	

    /**
     * 测试咨询
     */
    @RequestMapping(value ="/manage/testInformation")
    public String testInformation(){
    	return "/information/information_list";
    }
    
    /**
     * 测试咨询2
     */
    @RequestMapping(value ="/manage/testInformation2")
    public String testInformation2(){
    	return "/information/information_add";
    }
    		
    		
    /**
     * 标签查询页
     */
    @RequestMapping(value ="/manage/listByTagName")
    public String listByTagName(
    		Map<String, Object> map,
    		@RequestParam(value="parentTagName",required=false)String parentTagName,
    		@RequestParam(value="childTagName",required=false)String childTagName,
    		@RequestParam(value="catagory",required=false)String catagory,
    		@RequestParam(value="pageNo",required=false,defaultValue="1")String pageNo,
    		@RequestParam(value="pageSize",required=false,defaultValue="10")String pageSize){
    	int catagoryId = 2;
    	if(StringUtils.isNotEmpty(catagory)){
    		catagoryId = Integer.parseInt(catagory);
    	}
    	//根据二级标签名字模糊查询
    	if(StringUtils.isNotEmpty(childTagName)){
    		PageInfo<SingleTagVo> childPageInfo = tagService.findByTagName(childTagName,parentTagName,catagoryId,Integer.parseInt(pageNo),Integer.parseInt(pageSize));
    		map.put("childPageList", childPageInfo.getRecords());
    		map.put("parentTagName", parentTagName);
    		map.put("childTagName", childTagName);
    		map.put("childPageInfo", childPageInfo);
    		return "/tag/tag_list";
    	}
    	
    	//根据一级标签名字模糊查询
		PageInfo<TagVo> parentPageInfo = tagService.findByParentTagName(parentTagName,catagoryId,Integer.parseInt(pageNo),Integer.parseInt(pageSize));
		map.put("parentPageList", parentPageInfo.getRecords());
		map.put("parentTagName", parentTagName);
		map.put("childTagName", childTagName);
		map.put("parentPageInfo", parentPageInfo);
		return "/tag/tag_list";
    }
    
    /**
     * 分页标签查询页ajax
     */
    @RequestMapping(value ="/manage/listByTagNameAjax")
    public @ResponseBody PageInfo listByTagNameAjax(
    		Map<String, Object> map,
    		@RequestParam(value="parentTagName",required=false)String parentTagName,
    		@RequestParam(value="childTagName",required=false)String childTagName,
    		@RequestParam(value="catagory",required=false)String catagory,
    		@RequestParam(value="pageNo",required=false,defaultValue="1")String pageNo,
    		@RequestParam(value="pageSize",required=false,defaultValue="10")String pageSize){
    	int catagoryId = 2;
    	if(StringUtils.isNotEmpty(catagory)){
    		catagoryId = Integer.parseInt(catagory);
    	}
    	//根据二级标签名字模糊查询
    	if(StringUtils.isNotEmpty(childTagName)){
    		PageInfo<SingleTagVo> childPageInfo = tagService.findByTagName(childTagName,parentTagName,catagoryId,Integer.parseInt(pageNo),Integer.parseInt(pageSize));
    		map.put("childPageList", childPageInfo.getRecords());
    		return childPageInfo;
    	}
    	
    	//根据一级标签名字模糊查询
		PageInfo<TagVo> parentPageInfo = tagService.findByParentTagName(parentTagName,catagoryId,Integer.parseInt(pageNo),Integer.parseInt(pageSize));
		map.put("parentPageList", parentPageInfo.getRecords());
		return parentPageInfo;
    }
    
    /**
     * 跳转标签新增页
     */
    @RequestMapping(value ="/manage/toAddTagJsp")
    public String toAddTagJsp(
    		Map<String, Object> map,
    		@RequestParam(value="parentTagName",required=false)String parentTagName,
    		@RequestParam(value="childTagName",required=false)String childTagName,
    		@RequestParam(value="catagory",required=false)String catagory,
    		@RequestParam(value="pageNo",required=false,defaultValue="1")String pageNo,
    		@RequestParam(value="pageSize",required=false,defaultValue="10")String pageSize
    		){
    	int catagoryId = 0;
    	long parentId = 1L;
    	
    	if(StringUtils.isNotEmpty(catagory)){
    		catagoryId = Integer.parseInt(catagory);
    	}
    	
    	//根据一级标签查询子标签
    	PageInfo<SingleTagVo> childPageInfo = tagService.findByTagName(childTagName,parentTagName,catagoryId,Integer.parseInt(pageNo),Integer.parseInt(pageSize));
    	
		//获得所有一级标签
		List<TagVo> allParentTags = tagService.findByParentTagName("",catagoryId,1,100).getRecords();
		//获得所有分类
		List<CatagoryVo> catagorys  = new ArrayList<CatagoryVo>();
		catagorys.add(new CatagoryVo(1,"用户组"));
		catagorys.add(new CatagoryVo(2,"公开课"));
		catagorys.add(new CatagoryVo(3,"人"));
		
		if(StringUtils.isNotEmpty(parentTagName)){
			map.put("childTagList", childPageInfo.getRecords());	
		}

		map.put("childPageInfo", childPageInfo);
		map.put("allParentTags", allParentTags);
		map.put("parentTagName", parentTagName);
		map.put("childTagName", childTagName);
		map.put("catagoryId", catagoryId);
		map.put("catagorys", catagorys);
    	return "/tag/tag_add";
    }
    
    /**
     * 分页标签新增页ajax
     */
    @RequestMapping(value ="/manage/toAddTagJspAjax")
    public @ResponseBody PageInfo<SingleTagVo> toAddTagJspAjax(
    		Map<String, Object> map,
    		@RequestParam(value="parentTagName",required=false)String parentTagName,
    		@RequestParam(value="catagory",required=false)String catagory,
    		@RequestParam(value="pageNo",required=false,defaultValue="1")String pageNo,
    		@RequestParam(value="pageSize",required=false,defaultValue="10")String pageSize
    		){
    	int catagoryId = 0;
    	
    	if(StringUtils.isNotEmpty(catagory)){
    		catagoryId = Integer.parseInt(catagory);
    	}
    	//根据一级标签查询子标签
    	PageInfo<SingleTagVo> childPageInfo = tagService.findByTagName("",parentTagName,catagoryId,Integer.parseInt(pageNo),Integer.parseInt(pageSize));
		map.put("childPageInfo", childPageInfo);
		return childPageInfo;
    }
    
    /**
     * ajax下拉框查询一级标签列表
     */
    @RequestMapping(value ="/manage/findParentTags")
    public @ResponseBody List<TagVo> findParentTags(
    		@RequestParam(value="parentTagName",required=false)String parentTagName,
    		@RequestParam(value="catagory",required=false)String catagory
    		){
    	int catagoryId = 2;
    	if(StringUtils.isNotEmpty(catagory)){
    		catagoryId = Integer.parseInt(catagory);
    	}
    	List<TagVo> allParentTags = tagService.findByParentTagName(parentTagName,catagoryId,1,100).getRecords();
    	return allParentTags;
    }
    
    
    /**
     * 新增标签
     */
    @RequestMapping(value ="/manage/addTag")
    public @ResponseBody String addTag(
    		Map<String, Object> map,
    		@RequestParam(value="parentTagName",required=false)String parentTagName,
    		@RequestParam(value="childTagName",required=false)String childTagName,
    		@RequestParam(value="catagory",required=false)String catagory
    		){
    	int catagoryId = 2;
    	Boolean insertResult = false;
    	long parentId = 1L;
    	
    	if(StringUtils.isEmpty(catagory)){
    		//未选择分类
    		return "请选择分类";
    	}
    	catagoryId = Integer.parseInt(catagory);
    	if(StringUtils.isEmpty(parentTagName)){
    		//未选择或输入一级标签
    		return "请选择一级标签";
    		}
    	List<TagVo> parentTags = tagService.findByParentTagName(parentTagName, catagoryId,1,100).getRecords();
		if(parentTags!=null && parentTags.size()==1){
			//一级标签已存在 则获取parentId
			parentId = parentTags.get(0).getId();
		}else{
			//一级标签不存在 则新增一级标签
			Tag parentTag = new Tag();
			parentTag.setTagName(parentTagName);
			parentTag.setCatagory(catagoryId);
			parentTag.setCreateTime(new Date());
			Long userId=SessionContextUtils.getLoginUserId();
			parentTag.setCreateUser(userId);
//			parentTag.setParentId(null);
			//moudle写死
			parentTag.setMoudle(0);
			Boolean parentInsertResult = tagService.insertDetail(parentTag);
			System.out.println("一级标签插入"+parentInsertResult);
			//获取新增的parentId
			parentId = tagService.findByParentTagName(parentTagName, catagoryId,1,100).getRecords().get(0).getId();
		}
    	//新增二级标签
    	if(StringUtils.isNotEmpty(childTagName)){
    		List<SingleTagVo> childTag = tagService.findByTagName(childTagName, parentTagName, catagoryId,1,100).getRecords();
    		//判断是否已存在
    		if(childTag == null || childTag.size()!= 1){
    			Tag tag = new Tag();
    			tag.setTagName(childTagName);
    			tag.setCatagory(catagoryId);
    			tag.setCreateTime(new Date());
    			Long userId=SessionContextUtils.getLoginUserId();
    			tag.setCreateUser(userId);
    			tag.setParentId(parentId);
    			//moudle写死
    			tag.setMoudle(0);
    			insertResult = tagService.insertDetail(tag);
    			if(insertResult){
    				return "新增标签成功";
    			}else{
    				return "新增标签失败";
    			}
    		}else{
    			return "标签已存在";
    		}
    	}
    	
    	return null;
    }
    
    /**
     * 删除标签
     */
    @RequestMapping(value ="/manage/deleteTag")
    public @ResponseBody String deleteTag(
    		@RequestParam(value="tagId",required=true)String tagId){
    	String message = "标签删除成功";
    	long id = 1L;
    	if(tagId!=null){
    		id = Long.parseLong(tagId);
    	}
    	Tag tag = tagService.findById(id);
    	//查询是否有关联，若有关联则提示无法删除
    	Boolean isTargeted = false;
    	TagTarget tagTagert = groupTagService.findByTagId(id);
    	if(tagTagert != null){
    		isTargeted = true;
    		message = tag.getTagName()+"无法删除";
    	}
    	//删除标签
    	if(tag != null){
    		if(tag.getParentId()==null){
    			//一级标签先删除下属二级标签
    			List<Tag> childTags = tagService.findByparentId(id, tag.getCatagory()).getData();
    			if(childTags!=null && !childTags.isEmpty()){
    				for(Tag childTag :childTags ){
    					//查询二级标签是否有关联，若有关联则提示无法删除
    					TagTarget childTagTagert = groupTagService.findByTagId(childTag.getId());
    					if(childTagTagert != null){
    						isTargeted = true;
    						message = tag.getTagName()+"有子标签无法删除";
    			    		continue;
    					}
    					tagService.delete(childTag.getId());
    				}
    			}
    		}
    		if(!isTargeted){
    			tagService.delete(id);
    		}
    	}
    	return message;
    }
    
    @RequestMapping(value ="/manage/findByCatagoryAndParentId", method = RequestMethod.POST)
	@ResponseBody
    public Response<List<Tag>> getOrganizationListByParentId(Model model,HttpServletRequest request){
    	String catagor = request.getParameter("catagory");
    	String parent = request.getParameter("parentId");
    	Integer catagory=Integer.parseInt(catagor);
    	
    		Long parentId=Long.parseLong(parent);
    		Response<List<Tag>> res=tagService.findByparentId(parentId,catagory);
        	return  res;
    	
    	
    	
    }
}
