package com.fulan.application.handle.quartz.job;

import com.fulan.application.handle.quartz.BaseJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class TestJob implements BaseJob, InterruptableJob {

    private final Logger logger = LoggerFactory.getLogger(TestJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("测试定时任务 ！！！！！！！！！！！！！！！！");
        System.out.println("测试定时任务 ！！！！！！！！！！！！！！！！");
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {

    }
}
