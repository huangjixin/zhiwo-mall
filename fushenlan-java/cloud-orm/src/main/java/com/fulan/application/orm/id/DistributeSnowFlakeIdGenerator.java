package com.fulan.application.orm.id;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import cn.ms.sequence.Sequence;

/**
 * 分布式snowflake Id生成器
 * @author scotthu
 *
 */
@Component
public class DistributeSnowFlakeIdGenerator implements IdGenerator{
	
	private Sequence sequence;
	
	
	@PostConstruct
	public void init() {
		Long workerId = 0L;
		
		Long dataCenterId = workerId/32;
		Long realWorkerId = workerId%32;
		sequence = new Sequence(realWorkerId,dataCenterId);
	}
	
	@Override
	public Long generate() {
		return sequence.nextId();
	}

}
