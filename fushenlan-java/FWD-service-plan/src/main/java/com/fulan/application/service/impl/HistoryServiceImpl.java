package com.fulan.application.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.plan.domain.History;
import com.fulan.application.mapper.HistoryMapper;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * 浏览记录 服务实现类
 * </p>
 *
 * @author Hedge
 * @since 2018-01-30
 */
@Service
@Transactional
public class HistoryServiceImpl extends ServiceImpl<HistoryMapper, History> implements HistoryService {

    @Autowired
    private HistoryMapper historyMapper;
    @Autowired
    private IdGenerator idGenerator;
    @Override
    public int insertHistory(History history) {
        History example = historyMapper.selectOne(history);
        if(example==null||example.getId()==null||example.getId()==0){
            history.setId(idGenerator.generate());
            history.setGmtCreate(new Date());
            return historyMapper.insert(history);
        }else{
            return 0;
        }

    }
}
