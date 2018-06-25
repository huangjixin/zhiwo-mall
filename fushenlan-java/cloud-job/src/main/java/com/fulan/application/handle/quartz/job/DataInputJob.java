//package com.fulan.application.handle.quartz.job;
//
//import org.quartz.InterruptableJob;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.quartz.JobKey;
//import org.quartz.UnableToInterruptJobException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.fulan.application.handle.quartz.BaseJob;
//import com.fulan.application.util.spring.JobUtil;
//
///**
// * 数据下发任务
// *
// * @author c_liziqing
// */
//public class DataInputJob implements BaseJob, InterruptableJob {
//
//	private final Logger logger = LoggerFactory.getLogger(DataInputJob.class);
//
//	private boolean _interrupted = false;
//
//	private JobKey jobKey = null;
//
//	@Override
//	public void execute(JobExecutionContext context) throws JobExecutionException {
//		logger.info("定时任务YxImgUploadJobImpl------>开始");
//		jobKey = context.getJobDetail().getKey();
//		// 执行job服务地址
//		if (context.getMergedJobDataMap().get("host") != null) {
//			String execJobHost = (String) context.getMergedJobDataMap().get("host");
//			if (!"".equals(execJobHost) && !JobUtil.isCurrentServer(execJobHost)) {
//				logger.info("当前服务不能执行任务！");
//				return;
//			}
//		}
//		try {
//			if (_interrupted) {
//				logger.info("被外界因素停止了这个任务key: " + jobKey + "");
//				return;
//			}
//
//		} catch (Exception e) {
//			JobExecutionException exception = new JobExecutionException(e);
//			exception.setUnscheduleAllTriggers(true);
//			logger.error("job定时任务异常：", exception);
//		}
//
//	}
//
//	@Override
//	public void interrupt() throws UnableToInterruptJobException {
//		logger.info("外界正在调用调度器停止这个任务key: " + jobKey);
//		_interrupted = true;
//	}
//
//}
