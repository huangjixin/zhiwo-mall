package com.fulan.api.agent.service;

import com.fulan.api.agent.vo.VTag;
import com.fulan.application.util.domain.Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:FlagService
 * Date:   2018年04月13日 15:15
 * Reason :标签服务
 * @author Lycol
 * @version 1.0
 * @since JDK 1.8
 */
@FeignClient("flagService")
public interface FlagService {

    /**
     * 获取全部标签列表
     * <p>
     * 直接返回外部接口Response JSON串
     * 网络异常返回自定义JSON串{"code":-1,"message":"失败",data:[]}
     * </p>
     * @return
     */
    @RequestMapping("agentServiceFromDms/getFlags")
    String getFlags();

    /**
     * 回写标签
     * <p>
     * 直接返回外部接口Response JSON串
     * 网络异常返回自定义JSON串{"code":-1,"message":"失败",data:[]}
     * </p>
     * @param vTag
     * @return
     */
    @RequestMapping("agentServiceFromDms/addFlag")
    String addFlag(@RequestBody VTag vTag);

}
