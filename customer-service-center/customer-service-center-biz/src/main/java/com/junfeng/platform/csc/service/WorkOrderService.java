package com.junfeng.platform.csc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.csc.controller.model.OrderRecordVo;
import com.junfeng.platform.csc.controller.model.WorkOrderVo;
import com.junfeng.platform.csc.entity.WorkOrder;

/**
 * 工单信息
 *
 * @author lw
 * @date 2019-10-12 13:57:38
 */
public interface WorkOrderService extends IService<WorkOrder> {

  /**
   * 工单信息简单分页查询
   * @param workOrder 工单信息
   * @return
   */
  IPage<WorkOrder> getWorkOrderPage(Page<WorkOrder> page, WorkOrder workOrder);


	/**
	 * 功能描述: 根据分组
	 * @author: lw
	 * @createTime: 2019/10/8 18:10
	 * @param userid
	 * @return com.junfeng.platform.csc.entity.WorkOrder
	 */
	WorkOrder getFirstWorkOrderbyGroupId();
	/**
	 * 功能描述: 工单完结
	 * @author: lw
	 * @createTime: 2019/10/9 14:38
	 * @param orderRecordVo
	 * @param workOrderRecord
	 * @return boolean
	 */
	boolean orderRecordOver(OrderRecordVo orderRecordVo);

	/**
	 * 功能描述: 工单流转
	 * @author: lw
	 * @createTime: 2019/10/9 15:07
	 * @param orderRecordVo
	 * @param workOrderRecord
	 * @return boolean
	 */
	boolean orderRecordTurn(OrderRecordVo orderRecordVo);

	/**
	 * 功能描述: 创建工单
	 * @author: lw
	 * @createTime: 2019/10/25 15:56
	 * @param workOrderVo
	 * @return boolean
	 */
	boolean orderCreate(WorkOrderVo workOrderVo);
}
