package com.fulan.api.agent.service;

import com.fulan.api.agent.vo.CustomerSearchParm;
import com.fulan.api.agent.vo.CustomerVo;
import com.fulan.api.agent.vo.Req;
import com.fulan.application.util.domain.Response;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName:CustomerService
 * Date:   2018年04月13日 15:15
 * Reason :客户服务
 * @author Lycol
 * @version 1.0
 * @since JDK 1.8
 */
@FeignClient(name = "WebService")
public interface CustomerService {

    /**
     * 列表模式获取客户信息
    * 直接读取JSON 文件里的JSON串，待后续第三方接口提供出来再做调整
     * @param customerType 客户类型
     * @param key 搜索关键字
     * @param flags 标签
     * @return
     */
    @GetMapping("/customer/customerGroupList")
    String customerGroupList(@RequestParam(name = "customerType",required = false,defaultValue ="0")Integer customerType,
                                       @RequestParam(name = "key",required = false) String key,
                                       @RequestParam(name = "flags",required = false) List<String> flags);

    /**
     * 地图模式获取客户信息
     * 直接读取JSON 文件里的JSON串，待后续第三方接口提供出来再做调整
     * @param key 搜索关键字
     * @param scope 搜索范围
     * @return
     */
    @GetMapping("/customer/customerMapList")
    String customerMapList(@RequestParam(name = "key",required = false) String key,
                                     @RequestParam(name = "scope",required = true,defaultValue = "1")Integer scope);



    @PostMapping("/customer/create")
    Response<String> create(@RequestParam("userId")String userId,
                           @RequestParam("loginType")String loginType,
                           @RequestParam("token")String token,
                           @RequestBody CustomerVo req
                           );
    
    
    @PostMapping("/customer/update")
    Response<String> update(@RequestParam("userId")String userId,
                           @RequestParam("systemId")String systemId,
                           @RequestParam("token")String token,
                           @RequestBody CustomerVo req
                           );
    
    //String userId,String token, String systemId,  @RequestBody CustomerSearchParm req
    @PostMapping("/customer/search")
    Response<String> search(@RequestParam("userId")String userId,
                           @RequestParam("systemId")String systemId,
                           @RequestParam("token")String token,
                           @RequestBody CustomerSearchParm req
                           );

}
