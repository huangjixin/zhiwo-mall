package com.fulan.application.grouptag.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fulan.api.security.domain.TagTarget;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.service.GroupTagService;
import com.fulan.application.util.domain.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "GroupTagApi", description = "给用户组打标签接口")
@RestController
@RequestMapping("/customer/grouptag")
public class GroupTagController {

	private static final Logger logger = LoggerFactory.getLogger(GroupTagController.class);
	
	 @Autowired
	 GroupTagService groupTagService;
	 
	 @Autowired
	 IdGenerator idG;
	 
	@ApiOperation(value = "给用户组打标签", notes = "给用户组打标签", response = Response.class)
	@RequestMapping(value = "/addGroupTag", produces = { "application/json;charset=utf-8" }, method = RequestMethod.GET)
	public Response<String> addGroupTag(@RequestParam("hostId") Long hostId,@RequestParam("tagId") Long tagId){
		try {
			TagTarget tagtarget=new TagTarget();
			tagtarget.setId(idG.generate());
			tagtarget.setType(1);// 1:用户组2:公开课
			tagtarget.setHostId(hostId);
			tagtarget.setTagId(tagId);
			tagtarget.setCreateTime(new Date());
			return groupTagService.addGroupTag(tagtarget);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"给用户组打标签失败");
		}
	}
	
	
	@RequestMapping(value = "/listByHostIdAndType",  method = RequestMethod.GET)
	public List<TagTarget> listByHostIdAndType(@RequestParam(value="hostId", required=true) Long hostId, 
            @RequestParam(value="type", required=false) Integer type) {
	    try {
            List<TagTarget> ttList = groupTagService.listByHostIdAndType(hostId, type);
            return ttList;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
	}
	
	@RequestMapping(value = "/findByTagId",  method = RequestMethod.GET)
	public TagTarget findByTagId(@RequestParam(value="tagId", required=true) Long tagId) {
	    try {
            TagTarget tagTarget = groupTagService.findByTagId(tagId);
            return tagTarget;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
	}
	@RequestMapping(value = "/deleteByMap",  method = RequestMethod.POST)
	public int deleteByMap(@RequestParam(value="hostId", required=false) Long hostId) {
	    try {
	        return groupTagService.deleteByHostId(hostId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return 0;
        }
	}
}
