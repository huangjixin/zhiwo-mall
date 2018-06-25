package com.fulan.application.service.system.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.message.domain.SmsSystem;
import com.fulan.application.common.SmstemVO;
import com.fulan.application.mapper.SmsSystemMapper;
import com.fulan.application.service.system.SmsSystemService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 短信通道业务层
 * @author: guiyang
 * @date: 2018/3/5 11:02
 */
@Service
public class SmsSystemServiceImpl extends ServiceImpl<SmsSystemMapper, SmsSystem> implements SmsSystemService {

    /**
     * 查询短信业务系统
     */
    @Override
    public List<SmsSystem> selectSmsSystem(){
        List<SmsSystem> systems = SmstemVO.get();
        if (systems.size()==0){
            systems = selectList(new EntityWrapper<>(new SmsSystem()));
            SmstemVO.set(systems);
        }
        return systems;
    }

}