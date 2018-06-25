package com.fulan.application.ertag.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fulan.api.personnel.domain.ErTag;
import com.fulan.api.system.domain.Tag;
import com.fulan.api.system.service.TagService;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.service.ErTagService;
import com.fulan.application.util.domain.Response;
import com.fulan.core.monitoring.log.annotation.NoLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@NoLog
@Api(tags = "TagApi", description = "给人打标签")
@RestController
@RequestMapping(value ="/customer/ertag")
public class ErTagController {
	
	private static final Logger logger = LoggerFactory.getLogger(ErTagController.class);
	
	 @Autowired
	 ErTagService erTagService;
	 
	 @Autowired
	 TagService tagService;
	 
	 @Autowired
	 IdGenerator idG;
	 
	@ApiOperation(value = "给人打标签", notes = "给人打标签", response = Response.class)
	//@RequestMapping(value = "/addErTag", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	@PostMapping(value = "/addErTag")
	public Response<String> addErTag(@RequestParam(value="personnelId",required=false)Long personnelId,@RequestParam(value="tagId",required=false)Long tagId,@RequestParam(name="seq", required = false) Integer seq){
		try {
			Tag tag=tagService.findById(tagId);
			ErTag ertag=new ErTag();
			if(tag!=null){
				ertag.setTagName(tag.getTagName());
			}
			ertag.setId(idG.generate());
			ertag.setPersonnelId(personnelId);
			ertag.setTagId(tagId);
			ertag.setSeq(seq);
			ertag.setCreateUser(Long.parseLong("0"));
			ertag.setCreateTime(new Date());
			return erTagService.addErTag(ertag);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"给人打标签失败");
		}
	}
	@ApiOperation(value = "删除某人的某个标签", notes = "删除某人的某个标签", response = Response.class)
	//@RequestMapping(value = "/removeErTag", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	@PostMapping(value = "/removeErTag")
	public Response<String> removeErTag(@RequestParam(value="personnelId",required=false)Long personnelId,@RequestParam(value="tagId",required=false) Long tagId){
		try {
			return erTagService.removeErTag(personnelId,tagId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"删除失败");
		}
	}
	
	 @ApiOperation(value = "根据人才ID查找标签", notes = "根据人才ID查找标签", response = Response.class)
	    @RequestMapping(value="/findBypersonnelId",method=RequestMethod.POST)
	    public @ResponseBody Response<List<ErTag>> findBypersonnelId(@RequestParam(value="personnelId",required=false)Integer personnelId) {
	   
		 try {
			 Response<List<ErTag>> resp = new Response<List<ErTag>>(Response.SUCCESS, "根据人才ID查找标签成功");
				
	    	List<ErTag> ertaglist=erTagService.findBypersonnelId(personnelId);
	    	resp.setCode("1");
	    	resp.setData(ertaglist);
	    	return resp;
	    	
		 } catch (Exception e) {
				logger.error(e.getMessage());
				return new Response<List<ErTag>>(Response.ERROR,"根据人才ID查找标签失败");
			}
	    
	    }
	
	
	
	
	
}
