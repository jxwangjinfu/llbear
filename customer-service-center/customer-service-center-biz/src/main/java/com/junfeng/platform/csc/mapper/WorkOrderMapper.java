package com.junfeng.platform.csc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.csc.entity.WorkOrder;
import org.apache.ibatis.annotations.Param;

/**
 * 工单信息
 *
 * @author lw
 * @date 2019-10-12 13:57:38
 */
public interface WorkOrderMapper extends BaseMapper<WorkOrder> {
  /**
    * 工单信息简单分页查询
    * @param workOrder 工单信息
    * @return
    */
  IPage<WorkOrder> getWorkOrderPage(Page page, @Param("workOrder") WorkOrder workOrder);


}
