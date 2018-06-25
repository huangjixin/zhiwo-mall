package com.fulan.api.elearning.course.Service;


import com.fulan.api.elearning.course.domain.Course;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * <p>
 * 基础课程 服务类
 * </p>
 *
 * @author Hedge
 * @since 2018-01-19
 */
@FeignClient(name="elearning")
public interface CourseService {
    @PostMapping("/course/insert")
    int insert(@RequestBody Course course);

    @PostMapping("/course/delete")
    int delete(@RequestParam("id") Long id);

    @PostMapping("/course/batch/delete")
    int deleteBacth(Long[] id);

    @PostMapping(value = "/course/update")
    int update(@RequestBody Course course);

    @GetMapping(value = "/course/find")
    Course findById(@RequestParam("id") Long id);
}
