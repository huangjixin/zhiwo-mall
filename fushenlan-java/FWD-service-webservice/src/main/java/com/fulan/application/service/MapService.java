package com.fulan.application.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName:MapService
 * Date:    2018年04月11日 14:25
 * Reason :高德地图API Service
 *
 * @author Lycol
 * @version 1.0
 * @since JDK 1.8
 */
@Service
@RequestMapping(value = "/GaoDeApi", produces = {"application/json;charset=UTF-8"})
public class MapService {

    @Value("${gaode.api.web.key}")
    String key;


    @Autowired
    MapClient mapClient;
    /**
     *通过高德地图API获取某个地址的地理坐标
     * notification:需要考虑API单位时间内调用的次数上限问题
     * @param address 地理坐标
     * @return
     */
    public JSONObject getAddressPointUsingGaodeAPI(String address){

        String addressJsonInfo=mapClient.getPointJsonInfo(key,address);
        JSONObject jsonObject=JSON.parseObject(addressJsonInfo);
        JSONArray jsonArray=JSON.parseArray(jsonObject.getString("geocodes"));
        JSONObject pointJson=new JSONObject();
        if(null!=jsonArray&&jsonArray.size()>0){
            String location=JSON.parseObject(jsonArray.get(0).toString()).getString("location");
            //json key location有逗号隔开的话就代表得到了实际的地理坐标值
            if(location.contains(",")) {
                pointJson.put("x", location.split(",")[0]);
                pointJson.put("y", location.split(",")[1]);
            }
        }
        return pointJson;
    }
}
