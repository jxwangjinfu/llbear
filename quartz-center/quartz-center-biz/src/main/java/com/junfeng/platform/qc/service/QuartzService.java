package com.junfeng.platform.qc.service;

import java.util.List;

import org.quartz.SchedulerException;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.qc.entity.QuartzEntity;
import com.junfeng.platform.qc.entity.TypeBlob;

/**
 * quartz业务类
 * 
 * @projectName:quartz-center-biz
 * @author:Wangjf
 * @date:2019年9月16日 上午11:14:10
 * @version 1.0
 */
public interface QuartzService extends IService<QuartzEntity> {

    /**
     * 获取列表
     * 
     * @param quartzEntity
     * @return
     * @author:Wangjf
     * @createTime:2019年9月16日 上午11:31:59
     */
    List<QuartzEntity> list(QuartzEntity quartzEntity);

    /**
     * 获取分页数据
     * 
     * @param page
     * @param quartzDTO
     * @return
     * @author:Wangjf
     * @createTime:2019年9月17日 下午2:54:40
     */
    IPage<QuartzEntity> getQuartzPage(Page page, QuartzEntity quartzEntity);

    /**
     * 获取任务参数
     * 
     * @param jobName
     * @param groupName
     * @return
     * @author:Wangjf
     * @createTime:2019年9月17日 下午5:29:34
     */
    TypeBlob getJobData(String jobName, String groupName);

    /**
     * 获取定时任务信息
     * 
     * @param id
     * @return
     * @author:Wangjf
     * @createTime:2019年9月16日 上午11:28:42
     */
    QuartzEntity get(String jobName, String groupName);

    /**
     * 新增任务
     * 
     * @param quartzEntity
     * @return
     * @author:Wangjf
     * @createTime:2019年9月16日 上午11:29:42
     */
    Boolean saveQuartz(QuartzEntity quartzEntity) throws Exception;

    /**
     * 删除定时任务
     * 
     * @param id
     * @return
     * @author:Wangjf
     * @createTime:2019年9月16日 上午11:29:58
     */
    Boolean removeQuartz(String jobName, String groupName) throws SchedulerException;

    /**
     * 立即执行
     * 
     * @param jobName
     * @param groupName
     * @return
     * @author:Wangjf
     * @createTime:2019年9月17日 上午11:21:01
     */
    void trigger(String jobName, String groupName) throws SchedulerException;

    /**
     * 停止任务
     * 
     * @param jobName
     * @param groupName
     * @return
     * @author:Wangjf
     * @createTime:2019年9月17日 上午11:21:13
     */
    void pause(String jobName, String groupName) throws SchedulerException;

    /**
     * 重启任务
     * 
     * @param jobName
     * @param groupName
     * @return
     * @author:Wangjf
     * @createTime:2019年9月17日 上午11:21:23
     */
    void resume(String jobName, String groupName) throws SchedulerException;

}
