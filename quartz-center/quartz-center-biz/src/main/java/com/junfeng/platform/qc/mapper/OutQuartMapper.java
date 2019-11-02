package com.junfeng.platform.qc.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.qc.entity.OutQuart;

/**
 * 外部定时任务
 *
 * @author wangjf
 * @date 2019-09-25 10:49:16
 */
public interface OutQuartMapper extends BaseMapper<OutQuart> {
  /**
    * 外部定时任务简单分页查询
    * @param outQuart 外部定时任务
    * @return
    */
  IPage<OutQuart> getOutQuartPage(Page page, @Param("outQuart") OutQuart outQuart);


}
