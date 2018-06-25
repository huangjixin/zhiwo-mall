package com.fulan.application.service.system.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.message.domain.SmsNews;
import com.fulan.api.message.domain.SmsNewsInfo;
import com.fulan.api.message.vo.SmsNewsResultVO;
import com.fulan.api.message.vo.SmsNewsStatisticsResultVO;
import com.fulan.api.message.vo.SmsNewsStatisticsVO;
import com.fulan.api.message.vo.SmsNewsVO;
import com.fulan.application.mapper.SmsNewsMapper;
import com.fulan.application.orm.id.GenerateVOUtil;
import com.fulan.application.service.system.SmsNewsInfoService;
import com.fulan.application.service.system.SmsNewsService;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.page.PageUtil;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 消息池业务层
 * @author: guiyang
 * @date: 2018/3/5 11:02
 */
@Service
public class SmsNewsServiceImpl extends ServiceImpl<SmsNewsMapper, SmsNews> implements SmsNewsService {

    private static final Logger logger = LoggerFactory.getLogger(SmsNewsServiceImpl.class);

    @Autowired
    private SmsNewsMapper smsNewsMapper;

    @Autowired
    private SmsNewsInfoService smsNewsInfoService;

    /**
     * 查询消息模板
     */
    @Override
    public Page<SmsNewsResultVO> selectSmsNews(SmsNewsVO smsNewsVO) {
        Page<SmsNewsResultVO> page = new Page<>(smsNewsVO.getPageIndex(), smsNewsVO.getPageSize());
        page.setRecords(smsNewsMapper.selectSmsNews(smsNewsVO));
        return page;
    }

    public SmsNewsResultVO selectSmsNewsById(Long id,Long status){
        SmsNewsResultVO resultVO = new SmsNewsResultVO();
        try {
            SmsNews smsNews = selectById(id);
            PropertyUtils.copyProperties(resultVO,smsNews);
            resultVO.setSmsNewsInfos(smsNewsInfoService.selectSmsNewsInfo(id,status));
        } catch (Exception e) {
            logger.error("----查询消息详情失败----");
            e.printStackTrace();
        }
        return resultVO;
    }


    /**
     * 插入消息
     */
    @Override
    public void saveSmsNews(SmsNewsResultVO smsNewsVO) {
        try {
            SmsNews smsNews = new SmsNews();
            PropertyUtils.copyProperties(smsNews, smsNewsVO);
            Long id = GenerateVOUtil.generate(smsNews);
            insert(smsNews);
            List<SmsNewsInfo> smsNewsInfos = smsNewsVO.getSmsNewsInfos();
            for (SmsNewsInfo smsNewsInfo :smsNewsInfos){
                smsNewsInfo.setSmsNewsId(id);
            }
            smsNewsInfoService.saveSmsNewsInfo(smsNewsInfos);
        } catch (Exception e) {
            logger.error("----插入数据异常----");
            e.printStackTrace();
        }

    }

    /**
     * 分页统计
     * @param smsNewsStatisticsVO
     * @return
     */
    @Override
    public Page<SmsNewsStatisticsResultVO> selectSmsNewsStatistics(SmsNewsStatisticsVO smsNewsStatisticsVO) {
        Page<SmsNewsStatisticsResultVO> page = new Page<>(smsNewsStatisticsVO.getPageIndex(), smsNewsStatisticsVO.getPageSize());
        page.setRecords(smsNewsMapper.selectSmsNewsStatistics(smsNewsStatisticsVO));
        return page;
    }

	@Override
	public PageInfo<SmsNewsResultVO> setlectSmsNewsList(Page<SmsNewsResultVO> page, String content, String phone, String status,
			String systemCode, String channelCode, int pageNo, int pageSize,String type) {
		PageInfo<SmsNewsResultVO> pageInfo = new PageInfo<SmsNewsResultVO>();
		int total = smsNewsMapper.listForManageCount(content,phone,status,systemCode,channelCode,type);
		pageInfo.setRecords(smsNewsMapper.listForManage(page,content,phone,status,systemCode,channelCode, pageNo, pageSize,type));
		pageInfo.setPageNo(page.getCurrent());
		pageInfo.setPageSize(page.getSize());
		pageInfo.setPageTotal(PageUtil.getPages(pageSize,total));
		pageInfo.setPageRecords(page.getTotal());
		return pageInfo;
	}
    
    
    
    
    
    
    
    
    
    
    
}