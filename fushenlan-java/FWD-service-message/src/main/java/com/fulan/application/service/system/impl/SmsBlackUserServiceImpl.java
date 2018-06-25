package com.fulan.application.service.system.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.message.domain.SmsBlackUser;
import com.fulan.api.message.vo.SmsBlackUserVO;
import com.fulan.application.common.Consts;
import com.fulan.application.mapper.SmsBlackUserMapper;
import com.fulan.application.orm.id.GenerateVOUtil;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.system.SmsBlackUserService;
import com.fulan.application.task.BlackUserTask;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.page.PageUtil;
import com.fulan.application.util.util.StringUtils;
import com.fulan.core.monitoring.cat.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 短信手机号码黑名单业务层
 * @author: guiyang
 * @date: 2018/3/5 11:02
 */
@Service
public class SmsBlackUserServiceImpl extends ServiceImpl<SmsBlackUserMapper, SmsBlackUser> implements SmsBlackUserService{

    private static final Logger logger = LoggerFactory.getLogger(SmsBlackUserServiceImpl.class);

    private ExecutorService executorService = Executors.newFixedThreadPool(Consts.THREAD_COUNT_BASE*2);

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SmsBlackUserMapper smsBlackUserMapper;

    @Override
    public Page<SmsBlackUser> selectSmsBlackUser(SmsBlackUserVO smsBlackUserVO) {

        Page<SmsBlackUser> page = new Page<>(smsBlackUserVO.getPageIndex(), smsBlackUserVO.getPageSize());
        page.setRecords(smsBlackUserMapper.selectUserByPage(smsBlackUserVO));
        return page;
    }

    @Override
    public List<SmsBlackUser> selectBlackUsers() {
        return selectList(new EntityWrapper<>(new SmsBlackUser()));
    }

    @Override
    public List<SmsBlackUser> selectBlackUsers(List<Long> ids) {
        return selectBatchIds(ids);
    }

    @Override
    public List<String> selectPhone(String phones,String type) {
        //查询redis缓存排除手机号码黑名单
        List<String> phoneList = StringUtils.stringToList(phones);
        ListIterator<String> iterator = phoneList.listIterator();
        while (iterator.hasNext()){
            try {
                String phone  = iterator.next();
                if (redisUtil.exists(Constant.SMS_ALL + phone)){
                    iterator.remove();
                }else if (redisUtil.exists(type + phone)){
                    iterator.remove();
                }
            }catch (Exception e){
                logger.error("-------查询缓存异常-------");
                e.printStackTrace();
            }
        }
        return phoneList;
    }

    /**
     * 批量插入手机号码黑名单
     */
    @Override
    public void saveSmsBlackUser(List<SmsBlackUser> smsBlackUserList) {
        Iterator<SmsBlackUser> iterator = smsBlackUserList.iterator();
        while (iterator.hasNext()){
            SmsBlackUser blackUser = iterator.next();
            if (redisUtil.exists(blackUser.getType() + blackUser.getPhone())){
                iterator.remove();
            }
            if (redisUtil.exists(Constant.SMS_ALL + blackUser.getPhone())){
                iterator.remove();
            }
        }
        if (smsBlackUserList.size()>0){
            GenerateVOUtil.generate(smsBlackUserList);
            insertBatch(smsBlackUserList);
            executorService.execute(new BlackUserTask(BlackUserTask.Type.SAVE,smsBlackUserList,null));
        }else {
            throw new RuntimeException("-------手机号码黑名单已存在--------");
        }
    }

    /**
     * 批量删除手机号码黑名单
     */
    @Override
    public void deleteBatchSmsBlackUser(List<Long> ids) {
        deleteBatchIds(ids);
        executorService.execute(new BlackUserTask(BlackUserTask.Type.DELETE,null,ids));
    }

    /**
     * 根据id删除手机号码黑名单
     */
    @Override
    public void deleteSmsBlackUserById(Long id) {
        SmsBlackUser smsBlackUser = selectById(id);
        if (smsBlackUser!= null){
            deleteById(id);
            redisUtil.remove(smsBlackUser.getType() + smsBlackUser.getPhone());
        }
    }

	@Override
    public PageInfo<SmsBlackUser> selectSmsList(Page<SmsBlackUser> page, String phone,String type, int pageNo, int pageSize) {
        PageInfo<SmsBlackUser> pageInfo = new PageInfo<>();
        int total = smsBlackUserMapper.listForManageCount(phone,type);
        pageInfo.setRecords(smsBlackUserMapper.listForManage(page,phone,type, pageNo, pageSize));
        pageInfo.setPageNo(page.getCurrent());
        pageInfo.setPageSize(page.getSize());
        pageInfo.setPageTotal(PageUtil.getPages(pageSize,total));
        pageInfo.setPageRecords(page.getTotal());
        return pageInfo;
    }
    
    

}