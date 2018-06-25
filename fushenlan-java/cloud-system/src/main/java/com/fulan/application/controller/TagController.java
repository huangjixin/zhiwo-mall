package com.fulan.application.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.security.domain.TagTarget;
import com.fulan.api.security.domain.UserGroup;
import com.fulan.api.security.service.GroupService;
import com.fulan.api.security.service.GroupTagService;
import com.fulan.api.system.Vo.PersonnelTagVo;
import com.fulan.api.system.Vo.SingleTagVo;
import com.fulan.api.system.Vo.TagTwoVo;
import com.fulan.api.system.Vo.TagVo;
import com.fulan.api.system.domain.Tag;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.service.TagService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@Controller
@RequestMapping("/manage/tag")
public class TagController {
	private static final Logger LOG = LoggerFactory.getLogger(DictionaryController.class);
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	 IdGenerator idG;

	@Autowired
	private GroupService groupService;
	
	@Autowired
	private GroupTagService groupTagService;
	/**
     * 新增标签
     *
     * @param dictionary
     * @return
     */
    @ApiOperation(value = "新增标签", notes = "新增一个标签")
    @PostMapping("/insert")
    public @ResponseBody Boolean insert(
            @ApiParam(name = "tag",value = "不能为空字段：</br>code")
            @RequestBody Tag tag) {
        try {
        	
            return tagService.insertDetail(tag);
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }
    
    /**
     * 删除单个标签
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除单个标签", notes = "删除单个标签")
    @PostMapping("/delete")
    public @ResponseBody String delete(@RequestParam("id") Long id) {
        try {
        	//查关联表，看看这个id的标签有没有人在用,如果没有，删除,如果有，提示不能删
        	Boolean flag=tagService.delete(id);
        	if(flag){
        		return "删除成功";
        	}else{
        		 return "删除失败";
        	}
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }
    
    /**
     * 修改标签
     *
     * @param id
     * @return
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Boolean update(@RequestBody Tag tag) {
        try {
            return tagService.updateById(tag);
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }
    
    /**
     * 查询标签
     *
     * @param id
     * @return
     */
    @RequestMapping(value="/findById",method=RequestMethod.GET)
    public @ResponseBody Tag findById(Long id) {
        try {
            return tagService.findById(id);
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }
    
    /**
     * 根据标签名查询二级标签
     *
     * @param id
     * @return
     */
    @RequestMapping(value="/findByTagName",method=RequestMethod.GET)
    public @ResponseBody PageInfo<SingleTagVo> findByTagName(String tagName,String parentTagName,int catagory,int pageNo,int pageSize) {
        try {
            Page<SingleTagVo> page = new Page<SingleTagVo>(pageNo,pageSize);
            PageInfo<SingleTagVo> pageInfo = tagService.findByTagName(page,tagName,parentTagName,catagory);
			return pageInfo;
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }
    
    /**
     * 根据标签名查询一级标签
     *
     * @param id
     * @return
     */
    @RequestMapping(value="/findByParentTagName",method=RequestMethod.GET)
    public @ResponseBody PageInfo<TagVo> findByParentTagName(String parentTagName,int catagory,int pageNo,int pageSize) {
        try {
        	Page<TagVo> page = new Page<TagVo>(pageNo,pageSize);
            PageInfo<TagVo> pageInfo = tagService.findByParentTagName(page,parentTagName, catagory);
			return pageInfo;
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }
    
    
    
    /**
     * 查询标签
     *
     * @param id
     * @return
     */
    @RequestMapping(value="/findBymoudle",method=RequestMethod.GET)
    @ResponseBody
    public Response<List<Tag>> findBytagName() {
        try {
        	Response<List<Tag>> res=	new Response<List<Tag>>(Response.SUCCESS,"查询标签信息成功！");
        	res.setCode("1");
        	res.setData(tagService.findBymoudle());
            return res;
        } catch (Exception e) {
        	LOG.error(e.getMessage());
            return new Response<List<Tag>>(Response.ERROR,"查询标签信息失败");
        }
    }
    
    
    
    
    /**
     * 查询标签给用户选择
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据parentId查询标签", notes = "根据parentId查询标签", response = Response.class)
    @RequestMapping(value="/findByparentId",method=RequestMethod.POST)
    public @ResponseBody Response<List<Tag>> findByparentId(@RequestParam(value="parentId",required=false)Long parentId,@RequestParam(value="catagory",required=false)Integer catagory) {
        try {
        	
			Response<List<Tag>> resp=new Response<List<Tag>>(Response.SUCCESS, "查询标签成功");
			
			List<Tag> taglist = tagService.findByparentId(parentId,catagory);
			resp.setData(taglist);
			resp.setCode("1");
			return resp;
			
        } catch (Exception e) {
            LOG.error("", e);
            return new Response<List<Tag>>(Response.ERROR,"查询标签失败");
        }
    } 
    
    
    
    /**
     * 查询标签给用户选择
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查询标签给用户选择", notes = "查询标签给用户选择", response = Response.class)
    @RequestMapping(value="/findByCatagory",method=RequestMethod.POST)
    public @ResponseBody Response<List<TagVo>> findByCatagory(@RequestParam(value="catagory",required=false)Integer catagory) {
        try {
        	List<Tag> taglist=tagService.findByCatagory(catagory);
        	
        	List<TagVo> volist=new ArrayList<TagVo>();
			if(taglist!=null && taglist.size()>0){
				for(Tag tag:taglist){
					TagVo tagVo =copyTag(tag);
					volist.add(tagVo);
				}
			}
        	
        	//查找一级标签
			List<TagVo> list1=new ArrayList<TagVo>();
			List<TagVo> list2=new ArrayList<TagVo>();
			if(volist!=null && volist.size()>0){
				for(TagVo tagVo:volist){
					if(tagVo.getParentId()==null || "".equals(tagVo.getParentId())){//一级标签
						list1.add(tagVo);
					}else{//二级标签
						list2.add(tagVo);
					}
				}
			}
			//将二级标签与一级标签绑定
			if(list1!=null && list1.size()>0){
				for(TagVo tagVo1:list1){
					Long id1=tagVo1.getId();
					if(list2!=null && list2.size()>0){
						for(TagVo tagVo2:list2){
							Long id2=tagVo2.getParentId();
							if(id1.equals(id2)){//二级标签的父标签id==一级标签的id
								List<TagTwoVo> twoList=tagVo1.getTagTwoList();
								if(twoList==null)twoList=new ArrayList<TagTwoVo>();
								TagTwoVo tagTwoVo=getTagTwo(tagVo2);
								twoList.add(tagTwoVo);
								tagVo1.setTagTwoList(twoList);
							}
						}
					}
				}
			}
			Response<List<TagVo>> resp=new Response<List<TagVo>>(Response.SUCCESS, "查询标签成功");
			resp.setData(list1);
			return resp;
			
        } catch (Exception e) {
            LOG.error("", e);
            return new Response<List<TagVo>>(Response.ERROR,"查询标签失败");
        }
    }
    
    /**
     * 通过personnelId 获取对应人员的tag
     * @param personnelVoList
     * @return
     */
    @RequestMapping(value="/getPersonnelVoListByList",method=RequestMethod.POST)
    public @ResponseBody List<PersonnelTagVo> getPersonnelVoListByList(@RequestBody List<PersonnelTagVo> listPersonnelVo) {
        try {
            return tagService.getPersonnelVoListByList(listPersonnelVo);
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }
    public TagVo copyTag(Tag tag){
    	TagVo tagVo=new TagVo();
    	tagVo.setId(tag.getId());
    	tagVo.setTagName(tag.getTagName());
    	tagVo.setParentId(tag.getParentId());
    	tagVo.setCatagory(tag.getCatagory());
    	tagVo.setMoudle(tag.getMoudle());
    	tagVo.setCreateUser(tag.getCreateUser());
    	tagVo.setCreateTime(tag.getCreateTime());
    	tagVo.setModifyUser(tag.getModifyUser());
    	tagVo.setModifyTime(tag.getModifyTime());
		return tagVo;
	}
    public TagTwoVo getTagTwo(TagVo tagVo2){
    	TagTwoVo tagTwoVo=new TagTwoVo();
    	tagTwoVo.setId(tagVo2.getId());
    	tagTwoVo.setTagName(tagVo2.getTagName());
    	tagTwoVo.setParentId(tagVo2.getParentId());
    	tagTwoVo.setCatagory(tagVo2.getCatagory());
    	tagTwoVo.setMoudle(tagVo2.getMoudle());
    	tagTwoVo.setCreateUser(tagVo2.getCreateUser());
    	tagTwoVo.setCreateTime(tagVo2.getCreateTime());
    	tagTwoVo.setModifyUser(tagVo2.getModifyUser());
    	tagTwoVo.setModifyTime(tagVo2.getModifyTime());
		return tagTwoVo;
	}
    
    @GetMapping("/listByAccountId")
    public @ResponseBody List<Tag> listByAccountId(@RequestParam(name = "accountId" , required = false) Long accountId) {
        try {
            UserGroup ug = groupService.selectByAccountId(accountId);
            if (ug != null) {
                List <TagTarget> ttList = groupTagService.listByHostIdAndType(ug.getId(), null);
                if (ttList != null && ttList.size() >0) {
                    List<Long> idList = new ArrayList<Long>();
                    for (TagTarget tt : ttList) {
                        idList.add(tt.getTagId());
                    }
                    List<Tag> tagList = tagService.listByIdList(idList);
                    return tagList;
                }
            }
        } catch (Exception e) {
            LOG.error("查询异常", e);
            return null;
        }
        return null;
    }
}
