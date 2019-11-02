package com.junfeng.platform.qc.service.impl;

import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.qc.constant.QuartzCenterConstant;
import com.junfeng.platform.qc.entity.QuartzEntity;
import com.junfeng.platform.qc.entity.TypeBlob;
import com.junfeng.platform.qc.mapper.QuartzMapper;
import com.junfeng.platform.qc.service.QuartzService;

import cn.hutool.core.util.StrUtil;

/**
 * @projectName:quartz-center-biz
 * @author:Wangjf
 * @date:2019年9月16日 上午11:32:38
 * @version 1.0
 */
@Service
public class QuartzServiceImpl extends ServiceImpl<QuartzMapper, QuartzEntity> implements QuartzService {

    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    @Override
    public List<QuartzEntity> list(QuartzEntity quartzEntity) {
        return null;
    }

    @Override
    public IPage<QuartzEntity> getQuartzPage(Page page, QuartzEntity quartzDTO) {

        IPage<QuartzEntity> list = baseMapper.getQuartzPage(page, quartzDTO);

        return list;
    }

    @Override
    public TypeBlob getJobData(String jobName, String groupName) {
        return baseMapper.getJobData(jobName, groupName);
    }

    @Override
    public QuartzEntity get(String jobName, String groupName) {
        
        return baseMapper.getQuartz(jobName,groupName);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Boolean saveQuartz(QuartzEntity quartzEntity) throws Exception {

        if (StrUtil.isNotBlank(quartzEntity.getOldJobGroup())) {
            JobKey key = new JobKey(quartzEntity.getOldJobName(), quartzEntity.getOldJobGroup());
            scheduler.deleteJob(key);
        }

        // 设置任务参数
        JobDataMap param = new JobDataMap();
        param.put(QuartzCenterConstant.JOB_PARAM, quartzEntity.getParam());

        Class clazz = Class.forName(quartzEntity.getJobClassName());
        clazz.newInstance();
        // 构建job信息
        JobDetail jobDetail = JobBuilder.newJob(clazz)
                .withIdentity(quartzEntity.getJobName(), quartzEntity.getJobGroup()).setJobData(param)
                .withDescription(quartzEntity.getDescription()).build();
        // 触发时间点
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartzEntity.getCronExpression());
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(quartzEntity.getTriggerName(), quartzEntity.getJobGroup()).startNow()
                .withSchedule(cronScheduleBuilder).build();
        // 交由Scheduler安排触发
        scheduler.scheduleJob(jobDetail, trigger);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeQuartz(String jobName, String groupName) throws SchedulerException {
        JobKey key = new JobKey(jobName, groupName);
        return scheduler.deleteJob(key);
    }

    @Override
    public void trigger(String jobName, String groupName) throws SchedulerException {

        JobKey jobKey = new JobKey(jobName, groupName);
        scheduler.triggerJob(jobKey);
    }

    @Override
    public void pause(String jobName, String groupName) throws SchedulerException {

        JobKey jobKey = new JobKey(jobName, groupName);
        scheduler.pauseJob(jobKey);
    }

    @Override
    public void resume(String jobName, String groupName) throws SchedulerException {

        JobKey jobKey = new JobKey(jobName, groupName);
        scheduler.resumeJob(jobKey);
    }

}
