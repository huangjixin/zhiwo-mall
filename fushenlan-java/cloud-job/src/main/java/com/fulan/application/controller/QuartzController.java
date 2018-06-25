package com.fulan.application.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fulan.application.handle.quartz.QuartzConfigration;
import com.fulan.application.util.date.DateUtil;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.str.StringUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.matchers.StringMatcher;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@RestController
@RequestMapping("/job")
public class QuartzController {

    @Resource
    private Scheduler scheduler;
    @Autowired
    private QuartzConfigration quartzConfigration;


    private static Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    /**
     *  定时任务查询
     *
     * @param  queryJobName 任务名
     * @return
     */
    @ApiOperation(value = "定时任务查询", notes = "定时任务列表查询", response = Response.class)
    @GetMapping(value = "/list")
    public Response<Map<String, Object>> list(
            @ApiParam(name = "queryJobName", value = "根据任务名称模糊查询" ,required = false) @RequestParam(value = "queryJobName",required = false) String queryJobName) {
        try {
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
                Set<JobKey> jobKeys = scheduler.getJobKeys(new GroupMatcher<JobKey>(group, StringMatcher.StringOperatorName.EQUALS) {
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
                        Trigger.TriggerState state = scheduler.getTriggerState(trigger.getKey());
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
            Response<Map<String, Object>> resp = new Response<Map<String, Object>>();
            Map<String, Object> jobListMap = new LinkedHashMap<>();
            jobListMap.put("executingJobCount", executingJobCount);
            jobListMap.put("count", count);
            jobListMap.put("jobList", JSON.toJSONString(jobList));
            jobListMap.put("scheduler", scheduler);
            jobListMap.put("groups", groups);
            jobListMap.put("queryJobName", queryJobName);
            jobListMap.put("isValidScheduleServer", 1);
            resp.setData(jobListMap);
            resp.setMsg(Response.SUCCESS_MESSAGE);
            resp.setCode(Response.SUCCESS);
            return resp;

        } catch (Exception e) {

            logger.error("", e);
            return new Response<>(Response.ERROR, e.getLocalizedMessage());

        }

    }


    /**
     * 添加任务的处理
     */
    @ApiOperation(value = "定时任务新增", notes = "新增定时任务", response = Response.class)
    @PostMapping(value = "/insert")
    public Response<String> insert(
            @ApiParam(name = "jobGroup", value = "任务分组",required = true) @RequestParam(value = "jobGroup") String group,
            @ApiParam(name = "jobName", value = "任务名称",required = true) @RequestParam(value = "jobName") String jobName,
            @ApiParam(name = "jobClassName", value = "任务job的class名",required = true) @RequestParam(value = "jobClassName") String jobClassName,
//            @ApiParam(name = "triggerType", value = "任务触发类型",required = true) @RequestParam(value = "triggerType") String triggerType,
//            @ApiParam(name = "rate", value = "频率") @RequestParam(value = "rate",required = false) String rate,
//            @ApiParam(name = "times", value = "次数") @RequestParam(value = "times",required = false) String times,
            @ApiParam(name = "secondField", value = "秒") @RequestParam(value = "secondField",required = false,defaultValue = "0") String second,
            @ApiParam(name = "minutesField", value = "分") @RequestParam(value = "minutesField",required = false,defaultValue = "*") String minute,
            @ApiParam(name = "hourField", value = "时") @RequestParam(value = "hourField",required = false,defaultValue = "*") String hour,
            @ApiParam(name = "dayField", value = "日") @RequestParam(value = "dayField",required = false,defaultValue = "*") String day,
            @ApiParam(name = "monthField", value = "月") @RequestParam(value = "monthField",required = false,defaultValue = "*") String month,
            @ApiParam(name = "weekField", value = "周") @RequestParam(value = "weekField",required = false,defaultValue = "?") String week,
            @ApiParam(name = "yearField", value = "年") @RequestParam(value = "yearField",required = false,defaultValue = "2099") String year
            ) throws SchedulerException, IOException {
        Response<String> resp = new Response<>();

        try {
            // 验证任务是否存在
            boolean flag = scheduler.checkExists(JobKey.jobKey(jobName, group));
            if (flag) {
                resp.setCode(Response.ERROR);
                resp.setMsg("任务重名，已经存在标识为：\\\"\" + group + \".\" + jobName + \"\\\" 的任务!");
                return resp;
            }

            Trigger trigger = null;
//            if ("1".equals(triggerType)) {
//
////                Integer rateInt = new Integer(rate);
////                Integer timesInt = new Integer(times);
////                trigger = newTrigger().withIdentity(jobName, group).withSchedule(simpleSchedule().withIntervalInMinutes(rateInt).withRepeatCount(timesInt).withMisfireHandlingInstructionFireNow()).build();
//            } else if ("2".equals(triggerType)) {
                String cronExpression = String.format("%s %s %s %s %s %s %s", second, minute, hour, day, month, week, year);
                boolean isValid = CronExpression.isValidExpression(cronExpression);
                if (!isValid) {

                    resp.setCode(Response.ERROR);
                    resp.setMsg("cron表达式填写错误,您的表达式是：" + cronExpression);
                    return resp;
                }
//                try {
//                    trigger = newTrigger().withIdentity(jobName, group).withSchedule(cronSchedule(cronExpression).withMisfireHandlingInstructionFireAndProceed()).build();
//                } catch (Exception e) {
//                    logger.error("addJob:", e);
//                    return new Response<>(Response.ERROR, e.getLocalizedMessage());
//                }
//            } else {
//                String cronExpression = String.format("%s %s %s %s %s %s %s", "0", "*", "*", "*", "*", "?", "2099");
//                boolean isValid = CronExpression.isValidExpression(cronExpression);
//                if (!isValid) {
//                    resp.setCode(Response.ERROR);
//                    resp.setMsg("cron表达式填写错误,您的表达式是：" + cronExpression);
//                    return resp;
//                }
                try {
                    trigger = newTrigger().withIdentity(jobName, group).withSchedule(cronSchedule(cronExpression).withMisfireHandlingInstructionFireAndProceed()).build();
                } catch (Exception e) {
                    logger.error("addJob:", e);
                    return new Response<>(Response.ERROR, e.getLocalizedMessage());
                }
//            }

            @SuppressWarnings("rawtypes")
            Class jobClass = null;
            try {
                jobClass = Class.forName(jobClassName);
            } catch (ClassNotFoundException e1) {
                logger.error("addJob:", e1);
            }
            if (jobClass == null) {
                resp.setCode(Response.ERROR);
                resp.setMsg("类不存在,您的类是：" + jobClassName);
                return resp;

            }
            JobDetail job = newJob(jobClass).withIdentity(jobName, group).build();
            JobDataMap map = job.getJobDataMap();
            scheduler.scheduleJob(job, trigger);
        } catch (Exception e) {
            logger.error("addJob:", e);
            return new Response<>(Response.ERROR, e.getLocalizedMessage());
        }
        resp.setCode(Response.SUCCESS);
        resp.setMsg(Response.SUCCESS_MESSAGE);
        return resp;
    }


    /**
     * 中断TASK执行
     */
    @ApiOperation(value = "定时任务中断", notes = "定时任务中断", response = Response.class)
    @PostMapping(value = "/interrupt")
    public Response<String> interrupt(
            @ApiParam(name = "jobKey", value = "任务key,即任务组group+'.'+任务job名",required = true) @RequestParam("jobKey") String jobKey) throws SchedulerException {
        Response<String> resp = new Response<>();
        StringUtil.trim(jobKey);
        try {
            String group = jobKey.substring(0, jobKey.indexOf("."));
            String jobName = jobKey.substring(jobKey.indexOf(".") + 1);
            scheduler.interrupt(JobKey.jobKey(jobName, group));

            resp.setCode(Response.SUCCESS);
            resp.setMsg(Response.SUCCESS_MESSAGE);
            return resp;
        } catch (Exception e) {
            logger.error("interruptJob:", e);
            return new Response<>(Response.ERROR, e.getLocalizedMessage());
        }
    }


    /**
     * 修改处理
     */
    @ApiOperation(value = "定时任务修改", notes = "修改定时任务", response = Response.class)
    @PostMapping(value = "/update")
    public Response<String> update(
            @ApiParam(name = "jobGroup", value = "任务分组",required = true) @RequestParam(value = "jobGroup") String group,
            @ApiParam(name = "jobName", value = "任务名称",required = true) @RequestParam(value = "jobName") String jobName,
            @ApiParam(name = "jobClassName", value = "任务job的class名",required = true) @RequestParam(value = "jobClassName") String jobClassName,
//            @ApiParam(name = "triggerType", value = "任务触发类型",required = true) @RequestParam(value = "triggerType") String triggerType,
//            @ApiParam(name = "rate", value = "频率") @RequestParam(value = "rate",required = false) String rate,
//            @ApiParam(name = "times", value = "次数") @RequestParam(value = "times",required = false) String times,
            @ApiParam(name = "secondField", value = "秒") @RequestParam(value = "secondField",required = false,defaultValue = "0") String second,
            @ApiParam(name = "minutesField", value = "分") @RequestParam(value = "minutesField",required = false,defaultValue = "*") String minute,
            @ApiParam(name = "hourField", value = "时") @RequestParam(value = "hourField",required = false,defaultValue = "*") String hour,
            @ApiParam(name = "dayField", value = "日") @RequestParam(value = "dayField",required = false,defaultValue = "*") String day,
            @ApiParam(name = "monthField", value = "月") @RequestParam(value = "monthField",required = false,defaultValue = "*") String month,
            @ApiParam(name = "weekField", value = "周") @RequestParam(value = "weekField",required = false,defaultValue = "?") String week,
            @ApiParam(name = "yearField", value = "年") @RequestParam(value = "yearField",required = false,defaultValue = "2099") String year)
            throws SchedulerException {
        Response<String> resp = new Response<>();
        try {

            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(JobKey.jobKey(jobName, group));
            jobName = triggers.get(0).getKey().getName();
            group = triggers.get(0).getKey().getGroup();
            Trigger trigger = null;
//            if ("1".equals(triggerType)) {
//
//                Integer rateInt = new Integer(rate);
//                Integer timesInt = new Integer(times);
//                trigger = newTrigger().withIdentity(jobName, group).withSchedule(simpleSchedule().withIntervalInMinutes(rateInt).withRepeatCount(timesInt).withMisfireHandlingInstructionFireNow()).build();
//            } else if ("2".equals(triggerType)) {
                String cronExpression = String.format("%s %s %s %s %s %s", second, minute, hour, day, month, week);
                boolean isValid = CronExpression.isValidExpression(cronExpression);
                if (!isValid) {
                    resp.setCode(Response.ERROR);
                    resp.setMsg("cron表达式填写错误,您的表达式是：" + cronExpression);
                    return resp;
                }
//                try {
//                    trigger = newTrigger().withIdentity(jobName, group).withSchedule(cronSchedule(cronExpression).withMisfireHandlingInstructionFireAndProceed()).build();
//                } catch (Exception e) {
//                    logger.error("newTrigger Exception",e);
//
//                    return new Response<>(Response.ERROR, e.getLocalizedMessage());
//
//                }
//            } else {
//                String cronExpression = String.format("%s %s %s %s %s %s %s", "0", "*", "*", "*", "*", "?", "2099");
//                boolean isValid = CronExpression.isValidExpression(cronExpression);
//                if (!isValid) {
//
//                    resp.setCode(Response.ERROR);
//                    resp.setMsg("cron表达式填写错误,您的表达式是：" + cronExpression);
//                    return resp;
//
//                }
                try {
                    trigger = newTrigger().withIdentity(jobName, group).withSchedule(cronSchedule(cronExpression).withMisfireHandlingInstructionFireAndProceed()).build();
                } catch (Exception e) {
                    logger.error("newTrigger Exception",e);

                    return new Response<>(Response.ERROR, e.getLocalizedMessage());

                }
//            }
            scheduler.rescheduleJob(trigger.getKey(), trigger);
        } catch (Exception e) {
            logger.error("newTrigger Exception",e);
            return new Response<>(Response.ERROR, e.getLocalizedMessage());
        }
        resp.setCode(Response.SUCCESS);
        resp.setMsg(Response.SUCCESS_MESSAGE);
        return resp;
    }

    /**
     * 删除TASK
     */
    @ApiOperation(value = "定时任务删除", notes = "删除定时任务", response = Response.class)
    @PostMapping(value = "/delete")
    public Response<String> delete(
            @ApiParam(name = "jobKey", value = "任务key,即任务组group+'.'+任务job名",required = true) @RequestParam(value = "jobKey",required = true) String jobKey) throws SchedulerException {
        Response<String> resp = new Response<>();
        try {

            jobKey = jobKey.trim();
            String group = jobKey.substring(0, jobKey.indexOf("."));
            String jobName = jobKey.substring(jobKey.indexOf(".")+1);
            scheduler.deleteJob(JobKey.jobKey(jobName, group));

            resp.setCode(Response.SUCCESS);
            resp.setMsg(Response.SUCCESS_MESSAGE);
            return resp;
        } catch (Exception e) {
            logger.error("deleteJob:", e);
            return new Response<>(Response.ERROR, e.getLocalizedMessage());
        }

    }

    /**
     * 执行某个TASK一次
     */
    @ApiOperation(value = "定时任务执行", notes = "将一个定时任务执行一次", response = Response.class)
    @PostMapping(value = "/executeOnce")
    public Response<String> executeOnce(@ApiParam(name = "jobKey", value = "任务key,即任务组group+'.'+任务job名",required = true) @RequestParam(value = "jobKey",required = true) String jobKey) throws SchedulerException {

        Response<String> resp = new Response<>();
        StringUtil.trim(jobKey);
        try {
            String group = jobKey.substring(0, jobKey.indexOf("."));
            String jobName = jobKey.substring(jobKey.indexOf(".")+1);
            Trigger trigger = newTrigger().withIdentity(jobName + UUID.randomUUID().toString() + "MANUALLY", group).withPriority(100).forJob(JobKey.jobKey(jobName, group)).build();
            scheduler.scheduleJob(trigger);

            resp.setCode(Response.SUCCESS);
            resp.setMsg(Response.SUCCESS_MESSAGE);
            return resp;
        } catch (SchedulerException e) {
            logger.error("executeOnce:", e);
            return new Response<>(Response.ERROR, e.getLocalizedMessage());
        }
    }


    /**
     * 定时任务jobClassList
     */
    @ApiOperation(value = "定时任务jobClassList", notes = "定时任务jobClassList", response = Response.class)
    @GetMapping(value = "/class/list")
    public Response<Map<String, List<String>>> classList() throws SchedulerException {

        Response<Map<String, List<String>>> response = new Response<>();
        try {
            // 存在的任务类
            Map<String, List<String>> jobClassesMap = quartzConfigration.getJobClassesMap();
            response.setData(jobClassesMap);
            response.setCode(Response.SUCCESS);
            response.setCode(Response.SUCCESS_MESSAGE);
            return response;
        } catch (Exception e) {
            logger.error("jobClassList:", e);
            return new Response<>(Response.ERROR, e.getLocalizedMessage());
        }



    }


}
