package com.fulan.application.handle.quartz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.fulan.application.util.file.FileUtils;

/**
 * Quartz配置类
 */
@Configuration
@EnableScheduling
public class QuartzConfigration {

	private static Logger logger = LoggerFactory.getLogger(QuartzConfigration.class);

	public static final String PACKAGE_BASE = "com.fulan.application.handle.quartz.job";
	// public static final String JOB_GROUP = "group";
	// public static final String TIGGERNAME = "tigger";
	private Map<String, List<String>> jobClassesMap = new HashMap<String, List<String>>();

	@Value("${spring.datasource.url}")
	private String qzDS_URL;
	@Value("${spring.datasource.username}")
	private String qzDS_user;
	@Value("${spring.datasource.password}")
	private String qzDS_password;

	/**
	 * 定义quartz调度工厂
	 */
	@Bean(name = "scheduler")
	public SchedulerFactoryBean schedulerFactory() {
		SchedulerFactoryBean schedulerFactory = null;
		try {
			schedulerFactory = new SchedulerFactoryBean();
			schedulerFactory.setQuartzProperties(quartzProperties());
			schedulerFactory.afterPropertiesSet();
			// 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
			schedulerFactory.setOverwriteExistingJobs(true);
			schedulerFactory.setStartupDelay(1);

			if (jobClassesMap == null) {
				jobClassesMap = new HashMap<String, List<String>>();
			}
			getAllJobClasses();
			// initJobs(schedulerFactory);
		} catch (Exception e) {
			logger.error("schedulerFactory", e);
		}
		return schedulerFactory;
	}

	/**
	 * 初始化任务
	 */
	public void initJobs(SchedulerFactoryBean schedulerFactory) throws SchedulerException, ClassNotFoundException {
		// Scheduler sched = schedulerFactory.getScheduler();
		// TriggerKey triggerKey = TriggerKey.triggerKey("OneScheduleJobImpl",
		// "trigger1");
	}

	/**
	 * 从配置文件中获取数据源配置信息
	 */
	@Bean
	public Properties quartzProperties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		propertiesFactoryBean.setSingleton(false);
		Properties db = propertiesFactoryBean.getObject();
		db.setProperty("org.quartz.dataSource.qzDS.URL", qzDS_URL);
		db.setProperty("org.quartz.dataSource.qzDS.user", qzDS_user);
		db.setProperty("org.quartz.dataSource.qzDS.password", qzDS_password);
		return db;
	}

	/***
	 * 获取任务类 com.handle.quartz.job
	 */
	private void getAllJobClasses() {
		List<String> classesList = new ArrayList<String>();
		List<Class<?>> classs = FileUtils.getClasses(PACKAGE_BASE);
		logger.info("classs：" + classs);
		if (classs != null && classs.size() > 0) {
			for (Class<?> class1 : classs) {
				classesList.add(class1.getName());
			}
		}
		logger.info("classesList：" + classesList);
		if (classesList.size() > 0) {
			jobClassesMap.put(PACKAGE_BASE, classesList);
		}
		logger.info("jobClassesMap：" + jobClassesMap);
	}

	public Map<String, List<String>> getJobClassesMap() {
		return jobClassesMap;
	}

	public void setJobClassesMap(Map<String, List<String>> jobClassesMap) {
		this.jobClassesMap = jobClassesMap;
	}

}
