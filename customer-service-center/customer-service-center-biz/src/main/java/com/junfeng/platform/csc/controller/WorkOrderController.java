package com.junfeng.platform.csc.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.csc.controller.model.OrderRecordVo;
import com.junfeng.platform.csc.controller.model.WorkOrderVo;
import com.junfeng.platform.csc.entity.WorkOrder;
import com.junfeng.platform.csc.service.WorkOrderService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 工单信息
 *
 * @author lw
 * @date 2019-10-12 13:57:38
 */
@Api(tags = {"工单信息"})
@RestController
@AllArgsConstructor
@RequestMapping("/workorder")
public class WorkOrderController {

  private final  WorkOrderService workOrderService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param workOrder 工单信息
   * @return
   */
  @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 工单信息")
  @GetMapping("/page")
  public R<IPage<WorkOrder>> getWorkOrderPage(Page<WorkOrder> page, WorkOrder workOrder) {
    return R.ok(workOrderService.getWorkOrderPage(page,workOrder));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
  @GetMapping("/{id}")
  public R<WorkOrder> getById(@PathVariable("id") Long id){
    return R.ok(workOrderService.getById(id));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "删除工单信息", notes = "参数： id")
  @SysLog("删除工单信息")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('csc_workorder_del')")
  public R removeById(@PathVariable Long id){
	  Assert.isTrue(workOrderService.removeById(id),"删除工单信息失败!");
	  return R.ok();
  }


	@ApiOperation(value = "工单创建", notes = "参数： workOrderVo")
	@SysLog("工单创建")
	@PostMapping("/create")
	@PreAuthorize("@cscpms.hasPermission('WorkOrder')")
	public R orderCrate(@RequestBody WorkOrderVo workOrderVo){
		return R.ok(workOrderService.orderCreate(workOrderVo));
	}
	/**
	 * 功能描述: 根据员工id获取一条工单，没有传Null
	 * @author: lw
	 * @createTime: 2019/10/9 11:15
	 * @return com.pig4cloud.pig.common.core.util.R
	 */
	@ApiOperation(value = "工单接取", notes = "无参数")
	@SysLog("工单接取")
	@GetMapping("/order/pending")
	@PreAuthorize("@cscpms.hasPermission('WorkOrder')")
	public R getOrderbyGroupCode() {
		return R.ok(workOrderService.getFirstWorkOrderbyGroupId());
	}

	/**
	 * 功能描述: 工单流转
	 * @author: lw
	 * @createTime: 2019/10/9 15:31
	 * @param orderRecordVo
	 * @return com.pig4cloud.pig.common.core.util.R
	 */
	@ApiOperation(value = "工单流转", notes = "参数： orderRecordVo")
	@SysLog("工单流转")
	@PostMapping("/turn")
	@PreAuthorize("@cscpms.hasPermission('WorkOrder')")
	public R orderRecordTurn(@RequestBody OrderRecordVo orderRecordVo){
		  return R.ok(workOrderService.orderRecordTurn(orderRecordVo));
	}

	/**
	 * 功能描述: 工单完结
	 *
	 * @param orderRecordVo
	 * @return com.pig4cloud.pig.common.core.util.R
	 * @author: lw
	 * @createTime: 2019/10/9 14:35
	 */
	@ApiOperation(value = "工单完结", notes = "参数： orderRecordVo")
	@SysLog("工单完结")
	@PostMapping("/over")
	@PreAuthorize("@cscpms.hasPermission('WorkOrder')")
	public R orderRecordOver(@RequestBody OrderRecordVo orderRecordVo) {
		return R.ok(workOrderService.orderRecordOver(orderRecordVo));
	}

}
