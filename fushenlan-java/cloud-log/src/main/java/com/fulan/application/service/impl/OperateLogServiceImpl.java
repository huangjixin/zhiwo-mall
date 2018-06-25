package com.fulan.application.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.application.mapper.OperateLogMapper;
import com.fulan.application.operatelog.domain.OperateLog;
import com.fulan.application.service.OperateLogService;
import com.fulan.application.util.page.PageInfo;

@Service
public class OperateLogServiceImpl extends ServiceImpl<OperateLogMapper, OperateLog> implements OperateLogService{

	@Autowired
	OperateLogMapper operateLogMapper;
	
	private  int getPages(int size,int total) {
        int pages = 0;
		if (size == 0) {
            return 0;
        }
        pages =total / size;
        if (total % size != 0) {
            pages++;
        }
        return pages;
    }
	
	
	@Override
	public void addSystemLog(OperateLog systemLog) {
		operateLogMapper.insert(systemLog);
	}
	
	@Override
	public List<OperateLog> selectByMap(Map<String,Object> map) {
		return operateLogMapper.selectByMap(map);
	}


	@Override
	public PageInfo<OperateLog> listByPage(Page<OperateLog> page, String requestIp, String requestUrl, int pageNo,
			int pageSize) {
		PageInfo<OperateLog> pageInfo = new PageInfo<OperateLog>();
		pageInfo.setRecords(operateLogMapper.operateLogSerch(page, requestIp, requestUrl, pageNo, pageSize));
		int count = operateLogMapper.operateLogSerchCount(requestIp, requestUrl);
		pageInfo.setPageNo(page.getCurrent());
		pageInfo.setPageSize(page.getSize());
		pageInfo.setPageTotal(getPages(pageSize,count));
		pageInfo.setPageRecords(page.getTotal());
		return pageInfo;
	}
	
	
	/*@Override
	public PageInfo<OperateLog> listByPage(Page<OperateLog> page, String requestIp, String requestUrl,
			int pageNo, int pageSize) {
		PageInfo<OperateLog> pageInfo = new PageInfo<OperateLog>();
		OperateLog olg = new OperateLog();
		if(!StringUtils.isEmpty(requestIp)){
			olg.setRequestIp(requestIp);
		}
		if(!StringUtils.isEmpty(requestUrl)){
			olg.setRequestUrl(requestUrl);
		}
		Map<String,Object> map = new HashMap<>();
		if(!StringUtils.isEmpty(requestIp)){
			map.put("request_ip", requestIp);
		}
		if(!StringUtils.isEmpty(requestUrl)){
			map.put("request_url", requestUrl);
		}
		List<OperateLog> oList = operateLogMapper.selectByMap(map);
		pageInfo.setRecords(oList);
		EntityWrapper<OperateLog> ew = new EntityWrapper<>();
		ew.setEntity(olg);
		int count = operateLogMapper.selectCount(ew);
		pageInfo.setPageNo(page.getCurrent());
		pageInfo.setPageSize(page.getSize());
		pageInfo.setPageTotal(getPages(pageSize,count));
		pageInfo.setPageRecords(page.getTotal());
		return pageInfo;
	}*/

	/*@Override
	public Page<OperateLog> listByPage(String id , String requestIp , String requestUrl ,int pageNo, int pageSize) {
		Page<OperateLog> page = new Page<OperateLog>(pageNo, pageSize);
		OperateLog operateLog = new OperateLog();
		
		if(""!=id && null!=id ){
			operateLog.setId(Long.parseLong(id));
		}
		EntityWrapper<OperateLog> ew = new EntityWrapper<>(operateLog);
		if (StringUtils.isNotEmpty(requestIp)) {
            ew.like("request_ip", requestIp);
        }
		if (StringUtils.isNotEmpty(requestUrl)) {
            ew.like("request_url", requestUrl);
        }
		return this.selectPage(page, ew);
	}*/


}
