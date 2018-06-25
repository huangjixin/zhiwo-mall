package com.fulan.api.plan.service;

import com.fulan.api.plan.domain.PostDevelopment;
import com.fulan.api.plan.vo.DevelopmentFwVo;
import com.fulan.api.plan.vo.PostDevelopmentVo;
import com.fulan.application.util.domain.Response;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 岗位编辑管理 服务类
 * </p>
 *
 * @author wh
 * @since 2018-01-31
 */
@FeignClient(name = "plan")
public interface PostDevelopmentService {

    @PostMapping("/manage/postDevelopment/insert")
    String insertPostDevelopment(PostDevelopmentVo PostDevelopment);

    @GetMapping("/manage/postDevelopment/getOnePostDevelopment")
    PostDevelopmentVo getOnePostDevelopment(@RequestParam (name="id",required = false) Long id);
    
    @GetMapping("/manage/postDevelopment/getPostDevelopmentById")
    PostDevelopmentVo getPostDevelopmentById(@RequestParam (name="id",required = false) Long id);

    @GetMapping("/manage/postDevelopment/selectListDevelopment")
	List<PostDevelopment> selectListDevelopment();

    @PostMapping("manage/postDevelopment/insertDevelopmentOrupdate")
	Response<String> insertDevelopmentOrupdate(@RequestBody DevelopmentFwVo developmentFwVo,
	        @RequestParam (name="userId",required = false)Long userId, 
	        @RequestParam (name="fileId",required = false)Long fileId);

    
    

    @PostMapping("manage/postDevelopment/findOneDevelopmentFW")
	Map<String, Object> findOneDevelopmentFW(@RequestParam("id") String id);

  
    
   
    
}
