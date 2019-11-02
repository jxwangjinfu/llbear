package com.junfeng.platform.qc.service;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.junfeng.platform.qc.api.vo.OutQuartVO;
import com.junfeng.platform.qc.constant.QuartzCenterConstant;
import com.junfeng.platform.qc.entity.QuartzEntity;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 提供定时任务基本操作业务类
 *
 * @projectName:quartz-center-biz
 * @author:Wangjf
 * @date:2019年9月25日 下午1:43:42
 * @version 1.0
 */
@Service
@Slf4j
public class SchedulerService {

    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    /**
     * 内部调用保存定时任务
     *
     * @param quartzDTO
     * @throws Exception
     * @author:Wangjf
     * @createTime:2019年9月25日 下午2:16:10
     */
    public void createJob(QuartzEntity quartzDTO) throws Exception {

        saveJob(quartzDTO);
    }

    /**
     * 保存外部定时任务
     *
     * @param outQuartVO
     * @throws Exception
     * @author:Wangjf
     * @createTime:2019年9月25日 下午2:17:20
     */
    public void createOutJob(OutQuartVO outQuartVO) throws Exception {

        QuartzEntity quartzEntity = new QuartzEntity();
        BeanUtil.copyProperties(outQuartVO, quartzEntity, CopyOptions.create().setIgnoreNullValue(true));
        quartzEntity.setDescription(outQuartVO.getOutSysName());
        saveJob(quartzEntity);
    }

    /**
     * 保存任务
     *
     * @param quartzEntity
     * @throws SchedulerException
     * @author:Wangjf
     * @throws Exception
     * @createTime:2019年9月25日 下午2:02:15
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void saveJob(QuartzEntity quartzEntity) throws Exception {

        log.info("quartzEntity={}",quartzEntity);
        if (StrUtil.isNotBlank(quartzEntity.getOldJobGroup()) && StrUtil.isNotBlank(quartzEntity.getOldJobName()) ) {
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

    }

    /**
     * 删除任务
     *
     * @param jobName
     * @param groupName
     * @throws SchedulerException
     * @author:Wangjf
     * @createTime:2019年9月25日 下午1:57:27
     */
    public void deleteJob(String jobName, String groupName) throws SchedulerException {

        scheduler.deleteJob(new JobKey(jobName, groupName));

    }

    /**
     * 单次运行任务
     *
     * @param jobName
     * @param groupName
     * @throws SchedulerException
     * @author:Wangjf
     * @createTime:2019年9月25日 下午1:57:36
     */
    public void triggerJob(String jobName, String groupName) throws SchedulerException {

        scheduler.triggerJob(new JobKey(jobName, groupName));

    }

    /**
     * 暂停任务
     *
     * @param jobName
     * @param groupName
     * @throws SchedulerException
     * @author:Wangjf
     * @createTime:2019年9月25日 下午1:59:50
     */
    public void pauseJob(String jobName, String groupName) throws SchedulerException {

        scheduler.pauseJob(new JobKey(jobName, groupName));

    }

    /**
     * 重启任务
     *
     * @param jobName
     * @param groupName
     * @throws SchedulerException
     * @author:Wangjf
     * @createTime:2019年9月25日 下午2:00:30
     */
    public void resumeJob(String jobName, String groupName) throws SchedulerException {

        scheduler.resumeJob(new JobKey(jobName, groupName));

    }

    /**
     * 重启所有任务
     *
     * @throws SchedulerException
     * @author:Wangjf
     * @createTime:2019年9月25日 下午2:00:52
     */
    public void resumeAll() throws SchedulerException {

        scheduler.resumeAll();

    }

    /**
     * 获取CRON执行表达式
     *
     * @param jobName
     * @param groupName
     * @return
     * @throws SchedulerException
     * @author:Wangjf
     * @createTime:2019年9月25日 下午2:11:19
     */
    public CronTrigger getCronTrigger(String jobName, String groupName) throws SchedulerException {

        return (CronTrigger)scheduler.getTrigger(new TriggerKey(jobName, groupName));
    }

    /**
     * 获取cron字符串表达式
     *
     * @param jobName
     * @param groupName
     * @return
     * @throws SchedulerException
     * @author:Wangjf
     * @createTime:2019年9月25日 下午2:12:49
     */
    public String getCronExpression(String jobName, String groupName) throws SchedulerException {

        return getCronTrigger(jobName, groupName).getCronExpression();
    }

}
