package com.junfeng.platform.csc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.csc.entity.WorkOrderRecord;
import com.junfeng.platform.csc.mapper.WorkOrderRecordMapper;
import com.junfeng.platform.csc.service.WorkOrderRecordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 工单处理记录表
 *
 * @author lw
 * @date 2019-10-08 17:28:11
 */
@AllArgsConstructor
@Service("workOrderRecordService")
public class WorkOrderRecordServiceImpl extends ServiceImpl<WorkOrderRecordMapper, WorkOrderRecord> implements WorkOrderRecordService {

  /**
   * 工单处理记录表简单分页查询
   * @param workOrderRecord 工单处理记录表
   * @return
   */
  @Override
  public IPage<WorkOrderRecord> getWorkOrderRecordPage(Page<WorkOrderRecord> page, WorkOrderRecord workOrderRecord){
      return baseMapper.getWorkOrderRecordPage(page,workOrderRecord);
  }
}
