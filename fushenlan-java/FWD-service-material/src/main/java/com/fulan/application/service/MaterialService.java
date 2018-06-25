package com.fulan.application.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.material.domain.Material;
import com.fulan.api.material.domain.MaterialShare;
import com.fulan.api.material.vo.MaterialCourseVo;
import com.fulan.api.material.vo.MaterialVoCMS;
import com.fulan.application.util.page.PageInfo;

public interface MaterialService {
	PageInfo<Material> listByPage(Page<Material> page,String keyWord, String type, String submitter,String pubType, String id,
							  String groupId, String tagId, String uploadTimeBegin,
							  String uploadTimeEnd,String loginUser,
							  int pageNo, int pageSize);
	
	//批量/单个分享
	boolean share(String[] groupIds, String[] materials, String noShare);
	//批量/单个删除  同时删除关联关系 //返回不能被删除项的集合
	List<String> delete(String[] materialId);
	//新增/修改 
	String handData(Material material,String fileId);
	
	MaterialCourseVo checkMaterialInfo(String id);

	//根据ID查询
	Material getMaterialById(String id);

	//获取所有已分享的用户组
	List<String> getAllShared(String materialId);

	/**
	 * 关联课程资料中间表查询资料
	 * @param courseId
	 * @return
	 */
    List<Material> listMaterialByCourseId(Long courseId);
    
    
    List<MaterialShare> listMaterialShareByMaterialId(String materialIds);
    
    /**
     * 分页查询
     * @param paramMap
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<MaterialVoCMS> listMaterialByPage(Map<String, Object> paramMap,
            Page<MaterialVoCMS> page);
}
