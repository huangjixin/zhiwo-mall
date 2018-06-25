package com.fulan.api.material.service;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.material.domain.Material;
import com.fulan.api.material.domain.MaterialShare;
import com.fulan.api.material.vo.MaterialCourseVo;
import com.fulan.api.material.vo.MaterialVoCMS;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

@FeignClient(name = "material")
public interface MaterialService {

	@GetMapping(value = "/manage/material/materialListByPage")
	PageInfo<Material> materialListByPage(@RequestParam(value="keyWord",required=false) String keyWord,
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
            @RequestParam(value="pageSize",defaultValue="10") int pageSize);

	@GetMapping(value = "/manage/material/checkMaterialInfo")
	MaterialCourseVo viewMaterial(@RequestParam(value="id",required=false) String id);

	@PostMapping(value = "/manage/material/delete")
	List<String> deleteMaterials(@RequestParam(value="materialId")String[] materialId);

	@GetMapping(value = "/manage/material/getMaterialById")
	Material getMaterialById(@RequestParam(value="id")String id);

	@PostMapping(value = "/manage/material/handData")
	String insertAndUpdateMaterial(@RequestBody Material material,@RequestParam(value="fileId", required=false)String fileId);

	@PostMapping(value = "/manage/material/share")
	Response<Boolean> share(@RequestParam(value="groupIds", required=false)String[] groupIds, 
			 @RequestParam(value="materials", required=false)String[] materials, @RequestParam(value="noShare", required=false)String noShare);

	@GetMapping(value = "/manage/material/getAllShared")	
	List<String> getAllShared(@RequestParam(value="materialId")String materialId);
	
	/**
	 * 关联中间表根据courseId查询资料
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/manage/material/listMaterialByCourseId")
    List<Material> listMaterialByCourseId(@RequestParam(value="courseId", required=false) Long courseId);
	
	@GetMapping(value = "/manage/material/listMaterialShareByMaterialId")
	List<MaterialShare> listMaterialShareByMaterialId(@RequestParam(value="materialIds", required=false)String materialIds);
	
	/**
	 * 资料列表查询 （分页）
	 * @param paramMap
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@PostMapping(value = "/manage/material/listMaterialByPage")
    PageInfo<MaterialVoCMS> listMaterialByPage(@RequestBody Map<String, Object> paramMap,
            @RequestParam(value="pageNo", required=false) int pageNo, 
            @RequestParam(value="pageSize", required=false) int pageSize);
}