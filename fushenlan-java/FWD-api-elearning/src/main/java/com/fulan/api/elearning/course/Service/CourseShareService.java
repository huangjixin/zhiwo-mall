package com.fulan.api.elearning.course.Service;

import com.fulan.api.elearning.course.domain.CourseShare;
import com.sun.corba.se.spi.presentation.rmi.IDLNameTranslator;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.management.relation.InvalidRelationServiceException;

/**
 * <p>
 * 基础课程分享关系 服务类
 * </p>
 *
 * @author Hedge
 * @since 2018-01-22
 */
@FeignClient(name="elearning")
public interface CourseShareService {
    @PostMapping("/share/insert")
    int insert(@RequestBody CourseShare courseShare);

    @PostMapping("/share/delete")
    int delete(@RequestParam("id") Long id);

    @PostMapping("/share/batch/delete")
    int batchDelete(Long[] id);

    @PostMapping(value = "/share/update")
    int update(@RequestBody CourseShare courseShare);

    @GetMapping(value = "/share/find")
    CourseShare findById(@RequestParam("id") Long id);
}
