package com.fulan.application.controller;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronExpression;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.matchers.StringMatcher.StringOperatorName;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fulan.application.handle.quartz.QuartzConfigration;
import com.fulan.application.util.date.DateUtil;
import com.fulan.application.util.str.StringUtil;

/**
 * schedeule任务类
 */
@Controller
@RequestMapping("/scheduleman")
public class ScheduleController {

	private static Logger logger = LoggerFactory.getLogger(ScheduleController.class);

	@Resource
	private Scheduler scheduler;
	@Autowired
	private QuartzConfigration quartzConfigration;

	/**
	 * 任务列表
	 */
	@RequestMapping("/list")
	public ModelAndView listJobs(HttpServletRequest request, String queryJobName) throws SchedulerException {
		ModelAndView view = new ModelAndView();
		if (StringUtils.isNotBlank(queryJobName)) {
			queryJobName = queryJobName.trim();
		}
		Integer count = 0;
		List<String> groups = scheduler.getJobGroupNames();

		// 获取正在执行的job
		Integer executingJobCount = scheduler.getCurrentlyExecutingJobs().size();
		List<HashMap<String, Object>> jobList = new ArrayList<HashMap<String, Object>>();
		Map<String, String> executingJobsMap = new HashMap<String, String>();
		for (JobExecutionContext context : scheduler.getCurrentlyExecutingJobs()) {
			executingJobsMap.put(context.getJobDetail().getKey().getGroup() + context.getJobDetail().getKey().getName(), "1");
		}

		for (String group : groups) {
			@SuppressWarnings("serial")
			Set<JobKey> jobKeys = scheduler.getJobKeys(new GroupMatcher<JobKey>(group, StringOperatorName.EQUALS) {
			});

			for (JobKey jobKey : jobKeys) {
				if (StringUtils.isNotBlank(queryJobName) && !jobKey.toString().contains(queryJobName)) {
					continue;
				}
				JobDetail jobDetail = scheduler.getJobDetail(jobKey);
				HashMap<String, Object> jobInfoMap = new HashMap<String, Object>();
				// 触发器
				List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
				Map<String, Object> triggerStatusMap = new HashMap<String, Object>();
				for (Trigger trigger : triggers) {
					TriggerState state = scheduler.getTriggerState(trigger.getKey());
					triggerStatusMap.put(trigger.getKey().toString().replace(".", ""), state.name() + ":" + state.ordinal());
					if (trigger instanceof CronTriggerImpl) {
						jobInfoMap.put("crondesc", "cron表达式：" + ((CronTriggerImpl) trigger).getCronExpression());
						jobInfoMap.put("preFire", DateUtil.format(trigger.getPreviousFireTime(), "yyyy-MM-dd HH:mm:ss"));
						jobInfoMap.put("nextFire", DateUtil.format(trigger.getNextFireTime(), "yyyy-MM-dd HH:mm:ss"));
					} else {
						jobInfoMap.put("crondesc", "执行频率：" + ((SimpleTriggerImpl) trigger).getRepeatInterval() / 1000 + "秒");
						jobInfoMap.put("preFire", DateUtil.format(trigger.getPreviousFireTime(), "yyyy-MM-dd HH:mm:ss"));
						jobInfoMap.put("nextFire", DateUtil.format(trigger.getNextFireTime(), "yyyy-MM-dd HH:mm:ss"));
					}
				}

				jobInfoMap.put("triggers", triggers);
				jobInfoMap.put("jobDetail", jobDetail);
				if (executingJobsMap.containsKey(jobDetail.getKey().getGroup() + jobDetail.getKey().getName())) {
					jobInfoMap.put("isRunning", "正在运行");
				} else {
					jobInfoMap.put("isRunning", "停止");
				}
				jobInfoMap.put("triggerStatusMap", triggerStatusMap);
				jobList.add(jobInfoMap);
				count++;
			}
		}
		view.addObject("executingJobCount", executingJobCount);
		view.addObject("count", count);
		view.addObject("jobList", jobList);
		view.addObject("scheduler", scheduler);
		view.addObject("groups", groups);
		view.addObject("queryJobName", queryJobName);
		view.addObject("isValidScheduleServer", 1);
		view.setViewName("jobList");
		return view;
	}

	/**
	 * 添加任务页面
	 */
	@RequestMapping(value = "/showadd")
	public ModelAndView showAddJob(HttpServletRequest request, String queryJobName) throws SchedulerException {
		ModelAndView view = new ModelAndView();
		// 任务所属组
		List<String> jobGroups = scheduler.getJobGroupNames();
		view.addObject("groups", jobGroups);
		view.addObject("queryJobName", queryJobName);
		// 存在的任务类
		Map<String, List<String>> jobClassesMap = quartzConfigration.getJobClassesMap();
		view.addObject("jobClassesMap", jobClassesMap);
		view.setViewName("jobAdd");
		return view;
	}

	/**
	 * 添加任务的处理，处理完成后返回任务列表
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addJob")
	@ResponseBody
	public Map<String, String> addJob(HttpServletRequest request, HttpServletResponse response, String queryJobName)
			throws SchedulerException, IOException {
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			if (queryJobName != null && !"".equals(queryJobName)) {
				try {
					queryJobName = URLEncoder.encode(queryJobName, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					logger.error("ConfigHelp.getUrl", e);
				}
			}
			String group = request.getParameter("jobGroup").trim();
			String jobName = request.getParameter("jobName").trim();
			String jobClassName = request.getParameter("jobClassName").trim();
			String[] argsNames = StringUtil.trimArray(request.getParameterValues("argsNames"));
			String[] argsValues = StringUtil.trimArray(request.getParameterValues("argsValues"));
			String triggerType = request.getParameter("triggerType").trim();

			// 验证任务是否存在
			boolean flag = scheduler.checkExists(JobKey.jobKey(jobName, group));
			if (flag) {
				resultMap.put("success", "0");
				resultMap.put("msg", "任务重名，已经存在标识为：\"" + group + "." + jobName + "\" 的任务!");
				return resultMap;
			}

			Trigger trigger = null;
			if ("1".equals(triggerType)) {
				String rate = request.getParameter("rate").trim();
				String times = request.getParameter("times").trim();
				Integer rateInt = new Integer(rate);
				Integer timesInt = new Integer(times);
				trigger = newTrigger().withIdentity(jobName, group).withSchedule(simpleSchedule().withIntervalInMinutes(rateInt).withRepeatCount(timesInt).withMisfireHandlingInstructionFireNow()).build();
			} else if ("2".equals(triggerType)) {
				String second = request.getParameter("secondField");
				String minute = request.getParameter("minutesField");
				String hour = request.getParameter("hourField");
				String day = request.getParameter("dayField");
				String month = request.getParameter("monthField");
				String week = request.getParameter("weekField");
				String year = request.getParameter("yearField");
				String cronExpression = String.format("%s %s %s %s %s %s %s", second, minute, hour, day, month, week, year);
				boolean isValid = CronExpression.isValidExpression(cronExpression);
				if (!isValid) {
					resultMap.put("success", "0");
					resultMap.put("msg", "cron表达式填写错误,您的表达式是：" + cronExpression);
					return resultMap;
				}
				try {
					trigger = newTrigger().withIdentity(jobName, group).withSchedule(cronSchedule(cronExpression).withMisfireHandlingInstructionFireAndProceed()).build();
				} catch (Exception e) {
					logger.error("addJob:", e);
				}
			} else {
				String cronExpression = String.format("%s %s %s %s %s %s %s", "0", "*", "*", "*", "*", "?", "2099");
				boolean isValid = CronExpression.isValidExpression(cronExpression);
				if (!isValid) {
					resultMap.put("success", "0");
					resultMap.put("msg", "cron表达式填写错误,您的表达式是：" + cronExpression);
					return resultMap;
				}
				try {
					trigger = newTrigger().withIdentity(jobName, group).withSchedule(cronSchedule(cronExpression).withMisfireHandlingInstructionFireAndProceed()).build();
				} catch (Exception e) {
					logger.error("addJob:", e);
				}
			}

			@SuppressWarnings("rawtypes")
			Class jobClass = null;
			try {
				jobClass = Class.forName(jobClassName);
			} catch (ClassNotFoundException e1) {
				logger.error("addJob:", e1);
			}
			if (jobClass == null) {
				resultMap.put("success", "0");
				resultMap.put("msg", "类不存在,您的类是：" + jobClassName);
				return resultMap;
			}
			JobDetail job = newJob(jobClass).withIdentity(jobName, group).build();
			JobDataMap map = job.getJobDataMap();
			for (int i = 0; i < argsNames.length; i++) {
				if (!argsNames[i].trim().equals("参数名")) {
					map.put(argsNames[i], argsValues[i]);
				}
			}
			scheduler.scheduleJob(job, trigger);
		} catch (Exception e) {
			logger.error("addJob:", e);
			resultMap.put("success", "0");
			resultMap.put("msg", "添加失败");
			return resultMap;
		}
		resultMap.put("success", "1");
		resultMap.put("msg", "添加成功");
		return resultMap;
	}

	/**
	 * 执行某个TASK一次
	 */
	@RequestMapping(value = "/executeOnce")
	public void executeOnce(HttpServletRequest request, HttpServletResponse response, String queryJobName,
			String jobKey) throws SchedulerException {
		StringUtil.trim(queryJobName);
		StringUtil.trim(jobKey);
		try {
			String group = jobKey.substring(0, jobKey.indexOf("."));
			String jobName = jobKey.substring(jobKey.indexOf(".")+1);
			Trigger trigger = newTrigger().withIdentity(jobName + UUID.randomUUID().toString() + "MANUALLY", group).withPriority(100).forJob(JobKey.jobKey(jobName, group)).build();
			scheduler.scheduleJob(trigger);
			response.sendRedirect(request.getContextPath() + "/scheduleman/list.do?queryJobName=" + queryJobName);
		} catch (IOException e) {
			logger.error("executeOnce:", e);
		}
	}

	/**
	 * 中断TASK执行
	 */
	@RequestMapping(value = "/interruptJob")
	public void interruptJob(HttpServletRequest request, HttpServletResponse response, String queryJobName,
			String jobKey) throws SchedulerException {
		StringUtil.trim(queryJobName);
		StringUtil.trim(jobKey);
		try {
			String group = jobKey.substring(0, jobKey.indexOf("."));
			String jobName = jobKey.substring(jobKey.indexOf(".") + 1);
			scheduler.interrupt(JobKey.jobKey(jobName, group));
			response.sendRedirect(request.getContextPath() + "/scheduleman/list.do?queryJobName=" + queryJobName);
		} catch (Exception e) {
			logger.error("interruptJob:", e);
		}
	}

	/**
	 * 显示修改页面
	 */
	@RequestMapping(value = "/showEditJob")
	public ModelAndView showEditJob(HttpServletRequest request, HttpServletResponse response, String queryJobName,
			String jobKey) throws SchedulerException {
		ModelAndView view = new ModelAndView();
		StringUtil.trim(queryJobName);
		StringUtil.trim(jobKey);
		String group = jobKey.substring(0, jobKey.indexOf("."));
		String jobName = jobKey.substring(jobKey.indexOf(".") + 1);

		// 任务信息
		JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(jobName, group));
		Trigger trigger = scheduler.getTrigger(TriggerKey.triggerKey(jobName, group));

		view.addObject("jobDetail", jobDetail);
		view.addObject("trigger", trigger);
		view.addObject("queryJobName", queryJobName);
		if (trigger instanceof SimpleTrigger) {
			view.addObject("triggerType", 1);
		} else {
			view.addObject("triggerType", 2);
		}
		List<String> jobGroups = scheduler.getJobGroupNames();
		view.addObject("jobGroups", jobGroups);
		view.setViewName("edit");
		return view;
	}

	/**
	 * 修改处理
	 */
	@RequestMapping(value = "/editJob")
	@ResponseBody
	public Map<String, String> editJob(HttpServletRequest request, HttpServletResponse response, String queryJobName)
			throws SchedulerException {
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			if (queryJobName != null && !"".equals(queryJobName)) {
				try {
					queryJobName = URLEncoder.encode(queryJobName, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					logger.error("editJob", e);
				}
			}
			String jobKey = request.getParameter("jobKey").trim();
			String triggerType = request.getParameter("triggerType").trim();
			String group = jobKey.substring(0, jobKey.indexOf("."));
			String jobName = jobKey.substring(jobKey.indexOf(".") + 1);
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(JobKey.jobKey(jobName, group));
			jobName = triggers.get(0).getKey().getName();
			group = triggers.get(0).getKey().getGroup();
			Trigger trigger = null;
			if ("1".equals(triggerType)) {
				String rate = request.getParameter("rate").trim();
				String times = request.getParameter("times").trim();
				Integer rateInt = new Integer(rate);
				Integer timesInt = new Integer(times);
				trigger = newTrigger().withIdentity(jobName, group).withSchedule(simpleSchedule().withIntervalInMinutes(rateInt).withRepeatCount(timesInt).withMisfireHandlingInstructionFireNow()).build();
			} else if ("2".equals(triggerType)) {
				String second = request.getParameter("secondField");
				String minute = request.getParameter("minutesField");
				String hour = request.getParameter("hourField");
				String day = request.getParameter("dayField");
				String month = request.getParameter("monthField");
				String week = request.getParameter("weekField");
				String cronExpression = String.format("%s %s %s %s %s %s", second, minute, hour, day, month, week);
				boolean isValid = CronExpression.isValidExpression(cronExpression);
				if (!isValid) {
					resultMap.put("success", "0");
					resultMap.put("msg", "cron表达式填写错误,您的表达式是：" + cronExpression);
					return resultMap;
				}
				try {
					trigger = newTrigger().withIdentity(jobName, group).withSchedule(cronSchedule(cronExpression).withMisfireHandlingInstructionFireAndProceed()).build();
				} catch (Exception e) {
					logger.error("newTrigger Exception",e);
					resultMap.put("success", "0");
					resultMap.put("msg", "修改失败");
					return resultMap;
				}
			} else {
				String cronExpression = String.format("%s %s %s %s %s %s %s", "0", "*", "*", "*", "*", "?", "2099");
				boolean isValid = CronExpression.isValidExpression(cronExpression);
				if (!isValid) {
					resultMap.put("success", "0");
					resultMap.put("msg", "cron表达式填写错误,您的表达式是：" + cronExpression);
					return resultMap;
				}
				try {
					trigger = newTrigger().withIdentity(jobName, group).withSchedule(cronSchedule(cronExpression).withMisfireHandlingInstructionFireAndProceed()).build();
				} catch (Exception e) {
					logger.error("newTrigger Exception",e);
					resultMap.put("success", "0");
					resultMap.put("msg", "修改失败");
					return resultMap;
				}
			}
			scheduler.rescheduleJob(trigger.getKey(), trigger);
		} catch (Exception e) {
			logger.error("newTrigger Exception",e);
			resultMap.put("success", "0");
			resultMap.put("msg", "修改失败");
			return resultMap;
		}
		resultMap.put("success", "1");
		resultMap.put("msg", "修改成功");
		return resultMap;
	}

	/**
	 * 删除TASK
	 */
	@RequestMapping(value = "/deleteJob")
	public void deleteJob(HttpServletRequest request, HttpServletResponse response, String queryJobName) throws SchedulerException {
		try {
			if (queryJobName != null && !"".equals(queryJobName)) {
				try {
					queryJobName = URLEncoder.encode(queryJobName, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					logger.error("deleteJob:", e);
				}
			}
			String jobKey = request.getParameter("jobKey").trim();
			String group = jobKey.substring(0, jobKey.indexOf("."));
			String jobName = jobKey.substring(jobKey.indexOf(".")+1);
			scheduler.deleteJob(JobKey.jobKey(jobName, group));
		} catch (Exception e) {
			logger.error("deleteJob:", e);
		}
		try {
			response.sendRedirect(request.getContextPath() + "/scheduleman/list.do?queryJobName=" + queryJobName);
		} catch (IOException e) {
			logger.error("deleteJob:", e);
		}
	}
}
