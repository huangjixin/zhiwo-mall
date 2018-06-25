package com.fulan.application.manage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.material.domain.Material;
import com.fulan.api.material.domain.MaterialShare;
import com.fulan.api.material.vo.MaterialCourseVo;
import com.fulan.api.material.vo.MaterialVoCMS;
import com.fulan.application.service.MaterialService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

@RestController
@RequestMapping("/manage/material")
public class MaterialController {

	@Autowired
	MaterialService materialService;
	
	
	@RequestMapping(value="materialListByPage",method=RequestMethod.GET)
	public @ResponseBody PageInfo<Material> materialListByPage(@RequestParam(value="keyWord",required=false) String keyWord,
															            @RequestParam(value="type",required=false) String type,
															            @RequestParam(value="submitter",required=false) String submitter,
															            @RequestParam(value="pubType",required=false) String pubType,
															            @RequestParam(value="id",required=false) String id,
															            @RequestParam(value="groupId",required=false) String groupId,
															            @RequestParam(value="tagId",required=false) String tagId,
															            @RequestParam(value="uploadTimeBegin",required=false) String uploadTimeBegin,
															            @RequestParam(value="uploadTimeEnd",required=false) String uploadTimeEnd,
															            @RequestParam(value="loginUser",required=false) String loginUser,
															            @RequestParam(value="pageNo",defaultValue="1") int pageNo,
															            @RequestParam(value="pageSize",defaultValue="10") int pageSize){
	
		Page<Material> page = new Page<Material>(pageNo, pageSize);
		PageInfo<Material> pageInfo = materialService.listByPage(page, keyWord, type, submitter, pubType, id, groupId, tagId, uploadTimeBegin, uploadTimeEnd,loginUser, pageNo, pageSize);
		return pageInfo;
	}
	@RequestMapping(value="share",method=RequestMethod.POST)
	public @ResponseBody Response<Boolean> share(@RequestParam(value="groupIds", required=false)String[] groupIds, 
												 @RequestParam(value="materials", required=false)String[] materials,
												 @RequestParam(value="noShare", required=false)String noShare){
		Response<Boolean> res = new Response<>();
		res.setData(materialService.share(groupIds, materials,noShare));
		return res;
	} 
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public @ResponseBody List<String> delete(@RequestParam(value="materialId")String[] materialId){
		return materialService.delete(materialId);
	}
	@RequestMapping(value="handData",method=RequestMethod.POST)
	public @ResponseBody String handData(@RequestBody Material material,@RequestParam(value="fileId", required=false) String fileId){
		return materialService.handData(material,fileId);
	}
	@RequestMapping(value="checkMaterialInfo",method=RequestMethod.GET)
	public @ResponseBody MaterialCourseVo checkMaterialInfo(@RequestParam(value="id",required=false) String id){
		return materialService.checkMaterialInfo(id);
	}
	
	
	@RequestMapping(value="getMaterialById",method=RequestMethod.GET)
	public @ResponseBody Material getMaterialById(@RequestParam(value="id",required=false) String id){
		return materialService.getMaterialById(id);
	}
	
	
	@RequestMapping(value="getAllShared",method=RequestMethod.GET)
	public @ResponseBody List<String> getAllShared(@RequestParam(value="materialId",required=false) String materialId){
		return materialService.getAllShared(materialId);
	}
	
	@RequestMapping(value="listMaterialByCourseId",method=RequestMethod.GET)
    public @ResponseBody List<Material> listMaterialByCourseId(@RequestParam(value="courseId",required=false) Long courseId){
        return materialService.listMaterialByCourseId(courseId);
    }
	
	@RequestMapping(value="listMaterialShareByMaterialId",method=RequestMethod.GET)
    public @ResponseBody List<MaterialShare> listMaterialShareByMaterialId(@RequestParam(value="materialIds", required=false)String materialIds){
	    
        return materialService.listMaterialShareByMaterialId(materialIds);
    }
	
	/**
     * 资料列表查询 （分页）
     * @param paramMap
     * @param pageNo
     * @param pageSize
     * @return
     */
	@PostMapping(value = "/listMaterialByPage")
    public @ResponseBody PageInfo<MaterialVoCMS> listMaterialByPage(@RequestBody Map<String, Object> paramMap,
            @RequestParam(value="pageNo", required=false)int pageNo, @RequestParam(value="pageSize", required=false)int pageSize) {
        Page<MaterialVoCMS> page = new Page<MaterialVoCMS>(pageNo, pageSize);
        PageInfo<MaterialVoCMS> pageInfo = materialService.listMaterialByPage(paramMap, page);
        return pageInfo;
    }
}
