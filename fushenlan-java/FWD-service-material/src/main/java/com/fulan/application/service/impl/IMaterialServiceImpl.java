package com.fulan.application.service.impl;

import java.util.Date;
import java.util.List;

import com.fulan.api.material.domain.Material;
import com.fulan.api.material.vo.MaterialDTO;
import com.fulan.api.material.vo.MaterialDeleVO;
import com.fulan.api.material.vo.MaterialVO;
import com.fulan.application.mapper.MaterialMapper;
import com.fulan.application.service.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.fulan.application.util.domain.Response;


public class IMaterialServiceImpl extends ServiceImpl<MaterialMapper, Material> implements IMaterialService {

	@Autowired
    private MaterialMapper materialMapper;
	public Response<String> save(Material material) {
        Response<String> resp = new Response<String>(Response.SUCCESS, "添加账号成功");
        if (null != material
                && StringUtils.isNotEmpty(material.getName())
                && null != material.getType()) {
            //material.setId(idGenerator.generate());
            material.setGroupId(1l);
            material.setTagId(2l);
            material.setIsShare(0);
            material.setIsRelatePlan(0);
            material.setGmtCreate(new Date());
            material.setGmtModified(new Date());
            materialMapper.insert(material);
            return resp;
        } else {
            resp.setCode(Response.ERROR);
            resp.setMsg("新增异常");
            return resp;
        }
    }

    @Override
    public Response<String> updateElMaterialById(Material material) {
        Response<String> resp = new Response<String>(Response.SUCCESS, "更新账号成功");
        if (null != material
                && StringUtils.isNotEmpty(material.getName())
                && null != material.getType()) {

            materialMapper.updateById(material);
            return resp;
        } else {
            resp.setCode(Response.ERROR);
            resp.setMsg("更新异常");
            return resp;
        }
    }


    @Override
    public MaterialDTO findById(Long id) {
        return materialMapper.selectForId(id);
    }

    @Override
    public Response<String> deleteBatch(MaterialVO list) {
        Response<String> resp = new Response<String>(Response.SUCCESS, "批量删除成功");
        for (MaterialDeleVO materialDeleVO : list.getListM()) {
            if (materialDeleVO.getIsShare() == 0) {
                materialMapper.deleteBatch(materialDeleVO.getIds());
            }
        }
        return resp;


    }
        @Override
        public Page<MaterialDTO> findMaterialByPage (MaterialVO materialVO,int pageNo, int pageSize){
            Page<MaterialDTO> page = new Page<MaterialDTO>(pageNo, pageSize);
            List<MaterialDTO> plans = materialMapper.findMaterialByPage(page, materialVO, pageNo, pageSize);
            page.setRecords(plans);
            return page;
        }


}
