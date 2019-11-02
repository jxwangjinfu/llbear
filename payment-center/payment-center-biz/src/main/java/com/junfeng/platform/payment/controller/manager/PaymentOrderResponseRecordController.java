package com.junfeng.platform.payment.controller.manager;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.payment.api.entity.PaymentOrderResponseRecord;
import com.junfeng.platform.payment.service.PaymentOrderResponseRecordService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 支付订单回调记录
 *
 * @author wangk
 * @date 2019-09-19 11:03:59
 */
@Api(tags = {"支付订单回调记录"})
@RestController
@AllArgsConstructor
@RequestMapping("/paymentorderresponserecord")
public class PaymentOrderResponseRecordController {

  private final PaymentOrderResponseRecordService paymentOrderResponseRecordService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param paymentOrderResponseRecord 支付订单回调记录
   * @return
   */
  @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 支付订单回调记录")
  @GetMapping("/page")
  public R<IPage<PaymentOrderResponseRecord>> getPaymentOrderResponseRecordPage(Page<PaymentOrderResponseRecord> page, PaymentOrderResponseRecord paymentOrderResponseRecord) {
    return R.ok(paymentOrderResponseRecordService.getPaymentOrderResponseRecordPage(page,paymentOrderResponseRecord));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
  @GetMapping("/{id}")
  public R<PaymentOrderResponseRecord> getById(@PathVariable("id") Long id){
    return R.ok(paymentOrderResponseRecordService.getById(id));
  }

  /**
   * 新增记录
   * @param paymentOrderResponseRecord
   * @return R
   */
  @ApiOperation(value = "新增支付订单回调记录", notes = "参数： paymentOrderResponseRecord")
  @SysLog("新增支付订单回调记录")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('payment_paymentorderresponserecord_add')")
  public R save(@RequestBody PaymentOrderResponseRecord paymentOrderResponseRecord){
    return R.ok(paymentOrderResponseRecordService.save(paymentOrderResponseRecord));
  }

  /**
   * 修改记录
   * @param paymentOrderResponseRecord
   * @return R
   */
  @ApiOperation(value = "修改支付订单回调记录", notes = "参数： paymentOrderResponseRecord")
  @SysLog("修改支付订单回调记录")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('payment_paymentorderresponserecord_edit')")
  public R update(@RequestBody PaymentOrderResponseRecord paymentOrderResponseRecord){
    return R.ok(paymentOrderResponseRecordService.updateById(paymentOrderResponseRecord));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "删除支付订单回调记录", notes = "参数： id")
  @SysLog("删除支付订单回调记录")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('payment_paymentorderresponserecord_del')")
  public R removeById(@PathVariable Long id){
    return R.ok(paymentOrderResponseRecordService.removeById(id));
  }

}
