package com.fulan.application.handle;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.fulan.application.operatelog.constant.QueueConstant;
import com.fulan.application.operatelog.domain.OperateLog;
import com.fulan.application.service.OperateLogService;

@Configuration
@RabbitListener(queues = QueueConstant.OPERATE_LOG_QUEUE)
public class OperateLogListener {

	@Autowired
	OperateLogService operateLogService;

	@RabbitHandler
	public void saveLog(OperateLog systemLog) {
		
		operateLogService.addSystemLog(systemLog);
	}

}
