package com.junfeng.platform.payment.controller.manager;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.payment.api.entity.PaymentRefundOrder;
import com.junfeng.platform.payment.service.PaymentRefundOrderService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 退款订单
 *
 * @author wangk
 * @date 2019-09-19 11:04:02
 */
@Api(tags = {"退款订单"})
@RestController
@AllArgsConstructor
@RequestMapping("/paymentrefundorder")
public class PaymentRefundOrderController {

  private final PaymentRefundOrderService paymentRefundOrderService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param paymentRefundOrder 退款订单
   * @return
   */
  @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 退款订单")
  @GetMapping("/page")
  public R<IPage<PaymentRefundOrder>> getPaymentRefundOrderPage(Page<PaymentRefundOrder> page, PaymentRefundOrder paymentRefundOrder) {
    return R.ok(paymentRefundOrderService.getPaymentRefundOrderPage(page,paymentRefundOrder));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
  @GetMapping("/{id}")
  public R<PaymentRefundOrder> getById(@PathVariable("id") Long id){
    return R.ok(paymentRefundOrderService.getById(id));
  }

  /**
   * 新增记录
   * @param paymentRefundOrder
   * @return R
   */
  @ApiOperation(value = "新增退款订单", notes = "参数： paymentRefundOrder")
  @SysLog("新增退款订单")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('payment_paymentrefundorder_add')")
  public R save(@RequestBody PaymentRefundOrder paymentRefundOrder){
    return R.ok(paymentRefundOrderService.save(paymentRefundOrder));
  }

  /**
   * 修改记录
   * @param paymentRefundOrder
   * @return R
   */
  @ApiOperation(value = "修改退款订单", notes = "参数： paymentRefundOrder")
  @SysLog("修改退款订单")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('payment_paymentrefundorder_edit')")
  public R update(@RequestBody PaymentRefundOrder paymentRefundOrder){
    return R.ok(paymentRefundOrderService.updateById(paymentRefundOrder));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "删除退款订单", notes = "参数： id")
  @SysLog("删除退款订单")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('payment_paymentrefundorder_del')")
  public R removeById(@PathVariable Long id){
    return R.ok(paymentRefundOrderService.removeById(id));
  }

}
