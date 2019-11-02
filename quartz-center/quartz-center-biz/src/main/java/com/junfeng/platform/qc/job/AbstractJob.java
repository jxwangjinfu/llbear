package com.junfeng.platform.qc.job;

import java.time.LocalDateTime;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.junfeng.platform.qc.constant.QuartzCenterConstant;
import com.junfeng.platform.qc.entity.QuartzLog;
import com.junfeng.platform.qc.service.QuartzLogService;

/**
 * 抽象类，定义逻辑骨架，跟业务无关
 * 
 * @projectName:quartz-center-biz
 * @author:Wangjf
 * @date:2019年9月17日 上午9:10:12
 * @version 1.0
 */
public abstract class AbstractJob implements Job {

    @Autowired
    private QuartzLogService quartzLogService;

    protected String param;

    /**
     * 任务运行
     * 
     * @param context
     * @throws JobExecutionException
     * @author:Wangjf
     * @createTime:2019年9月17日 上午9:10:12
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        QuartzLog quartzLog = new QuartzLog();
        long timeline = System.currentTimeMillis();
        quartzLog.setStartTime(LocalDateTime.now());
        quartzLog.setClassName(context.getJobDetail().getJobClass().toString());
        quartzLog.setParams(context.getJobDetail().getJobDataMap().getString(QuartzCenterConstant.JOB_PARAM));
        quartzLog.setJobGroup(context.getJobDetail().getKey().getGroup());
        quartzLog.setJobName(context.getJobDetail().getKey().getName());
        // 获取返回值
        try {
            String executeLog = run(context);
            quartzLog.setExecuteLog(executeLog);
            quartzLog.setExecuteStatus("成功");
        } catch (JobExecutionException e) {
            quartzLog.setExecuteStatus("失败");
            quartzLog.setExecuteLog("调用失败");
            quartzLog.setExceptionLog(e.getMessage());
        }
        quartzLog.setExecuteTime((int)(System.currentTimeMillis() - timeline));
        saveLog(quartzLog);
    }

    /**
     * 保存日志
     * 
     * @param quartzLog
     * @return
     * @author:Wangjf
     * @createTime:2019年9月18日 下午1:58:58
     */
    private Boolean saveLog(QuartzLog quartzLog) {

        return quartzLogService.save(quartzLog);
    }

    /**
     * 返回值为执行日志
     * 
     * @param context
     * @return
     * @throws JobExecutionException
     * @author:Wangjf
     * @createTime:2019年9月18日 下午1:51:25
     */
    abstract String run(JobExecutionContext context) throws JobExecutionException;

}
