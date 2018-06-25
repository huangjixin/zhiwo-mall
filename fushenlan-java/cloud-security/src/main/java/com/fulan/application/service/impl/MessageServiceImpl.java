package com.fulan.application.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.security.domain.Message;
import com.fulan.application.mapper.MessageMapper;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.service.MessageService;
import com.fulan.application.util.domain.Response;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenzhuang123
 * @since 2018-01-23
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

	
	@Autowired
	IdGenerator idGenerator;
	
	@Autowired
	MessageMapper messageMapper;
	
	public Page<Message> findbyaccountId(Long accountId, int pageNo, int pageSize){
		
		// 组装page，页数、条数、排序字段、排序方式
        Page<Message> page = new Page<Message>(pageNo, pageSize);
       
        Message message = new Message();
        if(accountId!=0) {
        	message.setAccountId(accountId);
        }
        EntityWrapper<Message> ew = new EntityWrapper<>(message);
        Page<Message> pp =  this.selectPage(page, ew);
        
        return  pp;
        
        
	}
	
	
	
	public List<Message> findbyaccountId(Long accountId){
		List<Message> messagelist=messageMapper.selectByAccountId(accountId);
		return  messagelist;


}



	@Override
	public Response<Integer> addMessage(long accountId,String content,String type,long personnelId) {
		// TODO Auto-generated method stub
		Response<Integer> resp = new Response<Integer>();
		try {
			Message message = new Message();
			message.setId(idGenerator.generate());
			message.setAccountId(accountId);
			message.setContent(content);
			message.setType(type);
			message.setPersonnelId(personnelId);
			message.setCreateTime(new Date());
			message.setUpdateTime(new Date());;
			if(this.insert(message)){
				resp.setCode(Response.SUCCESS);
				resp.setData(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			resp.setCode(Response.ERROR);
		}
		return resp;
	}
	
	
	
}
