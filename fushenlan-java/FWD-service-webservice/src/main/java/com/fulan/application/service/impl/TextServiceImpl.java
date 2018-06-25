package com.fulan.application.service.impl;

import com.fulan.api.agent.vo.PerformVo;
import com.fulan.application.mapper.TextMapper;
import com.fulan.application.service.TextService;
import com.fulan.application.util.json.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TextServiceImpl implements TextService{

    @Autowired
    private TextMapper textMapper;

    @Override
    public String getPerform(String agentCode, String month, String type) {

        List<PerformVo> list =  textMapper.getPerform(agentCode,month,type);
        Map<String,Object> map = new HashMap<>();
        for (PerformVo vo :list) {
            map.put(vo.getCode(),vo.getActualVal());
        }
        String json = JsonUtil.object2Json(map);
        return json;
    }
}
