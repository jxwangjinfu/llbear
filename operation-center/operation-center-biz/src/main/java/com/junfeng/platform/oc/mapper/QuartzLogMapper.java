package com.junfeng.platform.oc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.oc.entity.QuartzLog;
import org.apache.ibatis.annotations.Param;

/**
 * 定时任务触发日志表
 *
 * @author wangjf
 * @date 2019-10-15 14:55:08
 */
public interface QuartzLogMapper extends BaseMapper<QuartzLog> {
  /**
    * 定时任务触发日志表简单分页查询
    * @param quartzLog 定时任务触发日志表
    * @return
    */
  IPage<QuartzLog> getQuartzLogPage(Page page, @Param("quartzLog") QuartzLog quartzLog);


}
