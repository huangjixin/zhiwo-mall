//package com.fulan.application.tagtarget.controller;
//
//import java.util.Date;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.fulan.api.plan.domain.TagTarget;
//import com.fulan.api.system.domain.Tag;
//import com.fulan.api.system.service.TagService;
//import com.fulan.application.orm.id.IdGenerator;
//import com.fulan.application.service.TagTargetService;
//import com.fulan.application.util.domain.Response;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//
//@Api(tags = "TagTargetApi", description = "给公开课打标签接口")
//@RestController
//@RequestMapping("/customer/tagtarget")
//public class PublicClassTagController {
//
//	private static final Logger logger = LoggerFactory.getLogger(PublicClassTagController.class);
//
//	@Autowired
//	TagTargetService tagTargetService;
//
//	@Autowired
//	TagService tagService;
//
//	 @Autowired
//	 IdGenerator idG;
//
//	@ApiOperation(value = "给公开课打标签", notes = "给公开课打标签", response = Response.class)
//	@RequestMapping(value = "/addTagTarget", produces = { "application/json;charset=utf-8" }, method = RequestMethod.GET)
//	public Response<String> addTagTarget(@RequestParam("hostId") Long hostId,@RequestParam("tagId") Long tagId){
//		try {
//			Tag tag=tagService.findById(tagId);
//			TagTarget tagtarget=new TagTarget();
//			tagtarget.setId(idG.generate());
//			tagtarget.setType(2);// 1:用户组2:公开课
//			tagtarget.setHostId(hostId);
//			tagtarget.setTagId(tagId);
//			tagtarget.setCreateTime(new Date());
//			return tagTargetService.addTagTarget(tagtarget);
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//			return new Response<String>(Response.ERROR,"给公开课打标签失败");
//		}
//	}
//}
