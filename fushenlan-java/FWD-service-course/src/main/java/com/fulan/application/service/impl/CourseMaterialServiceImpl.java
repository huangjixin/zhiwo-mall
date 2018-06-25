package com.fulan.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.course.domain.CourseMaterial;
import com.fulan.application.mapper.CourseMaterialMapper;
import com.fulan.application.service.CourseMaterialService;
import com.fulan.application.util.domain.Response;

@Service
@Transactional
public class CourseMaterialServiceImpl extends ServiceImpl<CourseMaterialMapper,CourseMaterial> implements CourseMaterialService{
    @Autowired
    private CourseMaterialMapper courseMaterialMapper;

    @Override
    public Response<Boolean> updateCourseMaterial(CourseMaterial courseMaterial) {
        Response<Boolean> res =new Response<Boolean>();
        try {
            if (courseMaterial.getId()!=null&&courseMaterial.getId()>0){
                courseMaterialMapper.updateById(courseMaterial);
            }else{
                res.setCode(Response.ERROR);
                res.setMsg("更新课程资料中间表缺少id");
            }
        } catch (Exception e) {
            res.setCode(Response.ERROR);
            res.setMsg(Response.ERROR_MESSAGE);
        }
        return res;
    }


    @Override
    public Response<Boolean> deleteCourseMaterial(Long id) {
        Response<Boolean> res = new Response<Boolean>();
        try {
            if (id != null && id > 0) {
                courseMaterialMapper.deleteById(id);
            } else {
                res.setCode(Response.ERROR);
                res.setMsg("删除课程资料中间表缺少id");
            }
        } catch (Exception e) {
            res.setCode(Response.ERROR);
            res.setMsg(Response.ERROR_MESSAGE);
        }
        return res;
    }

}
