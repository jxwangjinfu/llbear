package com.junfeng.platform.csc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.csc.entity.WorkOrderRecord;

/**
 * 工单处理记录表
 *
 * @author lw
 * @date 2019-10-08 17:28:11
 */
public interface WorkOrderRecordService extends IService<WorkOrderRecord> {

  /**
   * 工单处理记录表简单分页查询
   * @param workOrderRecord 工单处理记录表
   * @return
   */
  IPage<WorkOrderRecord> getWorkOrderRecordPage(Page<WorkOrderRecord> page, WorkOrderRecord workOrderRecord);
}
