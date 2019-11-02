package com.junfeng.platform.csc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.csc.entity.WorkOrderRecord;
import org.apache.ibatis.annotations.Param;

/**
 * 工单处理记录
 *
 * @author lw
 * @date 2019-10-12 14:07:11
 */
public interface WorkOrderRecordMapper extends BaseMapper<WorkOrderRecord> {
  /**
    * 工单处理记录简单分页查询
    * @param workOrderRecord 工单处理记录
    * @return
    */
  IPage<WorkOrderRecord> getWorkOrderRecordPage(Page page, @Param("workOrderRecord") WorkOrderRecord workOrderRecord);


}
