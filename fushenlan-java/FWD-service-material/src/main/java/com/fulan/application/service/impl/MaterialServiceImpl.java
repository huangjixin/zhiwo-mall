package com.fulan.application.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.material.domain.Material;
import com.fulan.api.material.domain.MaterialShare;
import com.fulan.api.material.vo.MaterialCourseVo;
import com.fulan.api.material.vo.MaterialVoCMS;
import com.fulan.api.system.domain.Attachment;
import com.fulan.api.system.service.AttachmentService;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.mapper.MaterialMapper;
import com.fulan.application.orm.id.Idfactory;
import com.fulan.application.service.MaterialService;
import com.fulan.application.util.listUtil.ListUtils;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.page.PageUtil;

@Service
public class MaterialServiceImpl implements MaterialService{
	@Autowired
	MaterialMapper materialMapper;
	
	@Autowired
    AttachmentService attachmentService;
	
	/**
	 * @author kang
	 * 公共资料库   
	 * 私人资料库 
	 * pubType 管理员公共库/讲师私人库 1，讲师公共库2，	 
	 * submitter	管理员 登录 不传值 null
	 * 				讲师登录  传讲师的group_id 
	 */
	@Override
	public PageInfo<Material> listByPage(Page<Material> page,String keyWord, String type, String submitter,String pubType,String id, String groupId,
									 String tagId, String uploadTimeBegin, String uploadTimeEnd, String loginUser,
									 int pageNo, int pageSize) {
		PageInfo<Material> pageInfo = new PageInfo<Material>();
		int total = materialMapper.materialManageSearchCount(keyWord, type, submitter, pubType, id, groupId, tagId, uploadTimeBegin, uploadTimeEnd,loginUser);
		pageInfo.setRecords(materialMapper.materialManageSearch(page, keyWord, type, submitter,pubType,id, groupId, tagId, uploadTimeBegin, uploadTimeEnd, loginUser,pageNo, pageSize));
		pageInfo.setPageNo(page.getCurrent());
		pageInfo.setPageSize(page.getSize());
		pageInfo.setPageTotal(PageUtil.getPages(pageSize,total));
		pageInfo.setPageRecords(page.getTotal());
		return pageInfo;
	}

	@Override
	@Transactional
	public boolean share(String[] groupIds, String[] materials, String noShare) {
		try{
		  
		    
			List<String> mlis = Arrays.asList(materials);
			//取消分享
            if (noShare != null && !"".equals(noShare)) {
                if(!ListUtils.isEmpty(mlis)) {
                    for (String string : mlis) { 
                        materialMapper.deleteShar(string);
                        
                        Material material =new Material();
                        material.setId(Long.parseLong(string));
                        material.setIsShare(CommenConstant.VALUE_NO);
                        materialMapper.updateByPrimaryKeySelective(material);
                    }
                    return true;  
                }
            }
            List<String> lis =  Arrays.asList(groupIds);
            //分享
			if(!ListUtils.isEmpty(lis)&& !ListUtils.isEmpty(mlis)){
				for (String string : mlis) {
				    materialMapper.deleteShar(string);
					for (String strings : lis) {
						Map<String,Long> parms = new HashMap<String,Long>();
						parms.put("id", Idfactory.generate());
						parms.put("materialId", Long.parseLong(string));
						parms.put("groupId", Long.parseLong(strings));
						materialMapper.share(parms);
						
					}
					Material material =new Material();
					material.setId(Long.parseLong(string));
					material.setIsShare(CommenConstant.VALUE_YES);
					materialMapper.updateByPrimaryKeySelective(material);
				}
				return true;
			}
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	@Override
	@Transactional
	public List<String> delete(String[] materialId) {
		try{
			List<String> yl = Arrays.asList(materialId); 
			List<String> ml = new ArrayList<>();
			for (String string : yl) {
				int len = materialMapper.selectMaterAndCourseRe(string);
				if(len > 0){
					ml.add(string);
				}else{
					List<String> asList = new ArrayList<>();
					asList.add(string);
					materialMapper.deleteRelation(asList);
				}
			}
			 return ml;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	@Override
	@Transactional
	public String handData(Material material,String fileId) {
	    
	    Attachment am = null;
	    if (fileId != null && !"".equals(fileId)) {
	        am =  attachmentService.selectById(Long.parseLong(fileId));
	    }
		if(null != material){
		   try{	
			Long id = material.getId();
			if(null == id){
			   
				id = Idfactory.generate();
				material.setId(id);
				material.setIsShare(CommenConstant.VALUE_NO);
				materialMapper.insertSelective(material);
				 
			}else{
				material.setGmtModified(new Date());
				materialMapper.updateByPrimaryKeySelective(material);
			}
			//更新附件hostId字段
			if (am != null) {
                am.setHostId(id);
                attachmentService.updateById(am);
             }
			return id+"";
		   }catch(Exception e){
			   e.printStackTrace();
			   return null;
		   }
		}
		return null;
	}
	@Override
	public MaterialCourseVo checkMaterialInfo(String id) {
		return materialMapper.checkMaterialInfo(id);
	}

	@Override
	public Material getMaterialById(String id) {
		return materialMapper.selectById(id);
	}

	@Override
	public List<String> getAllShared(String materialId) {
		
		return materialMapper.getAllShared(materialId);
	}

    @Override
    public List<Material> listMaterialByCourseId(Long courseId) {
        return materialMapper.listMaterialByCourseId(courseId);
    }

    @Override
    public List<MaterialShare> listMaterialShareByMaterialId(
            String materialIds) {
        
        return materialMapper.listMaterialShareByMaterialId(materialIds);
    }

    @Override
    public PageInfo<MaterialVoCMS> listMaterialByPage(
            Map<String, Object> paramMap, Page<MaterialVoCMS> page) {
        PageInfo<MaterialVoCMS> pageInfo = new PageInfo<MaterialVoCMS>();
        int total = materialMapper.getMaterialCount(paramMap);
        pageInfo.setRecords(materialMapper.listMaterialByPage(page,paramMap));
        pageInfo.setPageNo(page.getCurrent());
        pageInfo.setPageSize(page.getSize());
        pageInfo.setPageTotal(PageUtil.getPages(page.getSize(),total));
        pageInfo.setPageRecords(page.getTotal());
        return pageInfo;
    }
}
