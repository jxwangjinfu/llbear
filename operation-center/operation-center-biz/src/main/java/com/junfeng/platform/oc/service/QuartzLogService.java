package com.junfeng.platform.oc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.oc.entity.QuartzLog;

import java.time.LocalDateTime;

/**
 * 定时任务触发日志表
 *
 * @author wangjf
 * @date 2019-10-15 14:55:08
 */
public interface QuartzLogService extends IService<QuartzLog> {

    /**
     * 定时任务触发日志表简单分页查询
     *
     * @param quartzLog
     *            定时任务触发日志表
     * @return
     */
    IPage<QuartzLog> getQuartzLogPage(Page<QuartzLog> page, QuartzLog quartzLog);

    /**
     * 发送定时任务至qc系统中
     *
     * @author: wangjf
     * @createTime: 2019/10/15 15:12
     * @param quartzLog
     * @param executeDateTime
     * @return java.lang.Boolean
     */
    Boolean sendQcQuartz(QuartzLog quartzLog, LocalDateTime executeDateTime);


    /**
     * 发送定时任务至qc系统中
     * @author: wangjf
     * @createTime: 2019/10/15 15:41
     * @param quartzId
     * @param quartzType
     * @param quartzState
     * @param executeDateTime
     * @return java.lang.Boolean
     */
    Boolean sendQcQuartz(Long quartzId,Integer quartzType,Integer quartzState,LocalDateTime executeDateTime);

    /**
     * 修改状态
     * @author: wangjf
     * @createTime: 2019/10/15 15:24
     * @param id
     * @return java.lang.Boolean
     */
    Boolean updateState(Long id);

}
