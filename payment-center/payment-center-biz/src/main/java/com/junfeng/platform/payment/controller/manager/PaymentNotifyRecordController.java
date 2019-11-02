package com.junfeng.platform.payment.controller.manager;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.payment.api.entity.PaymentNotifyRecord;
import com.junfeng.platform.payment.service.PaymentNotifyRecordService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 支付成功通知记录
 *
 * @author wangk
 * @date 2019-09-19 11:03:50
 */
@Api(tags = {"支付成功通知记录"})
@RestController
@AllArgsConstructor
@RequestMapping("/paymentnotifyrecord")
public class PaymentNotifyRecordController {

  private final PaymentNotifyRecordService paymentNotifyRecordService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param paymentNotifyRecord 支付成功通知记录
   * @return
   */
  @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 支付成功通知记录")
  @GetMapping("/page")
  public R<IPage<PaymentNotifyRecord>> getPaymentNotifyRecordPage(Page<PaymentNotifyRecord> page, PaymentNotifyRecord paymentNotifyRecord) {
    return R.ok(paymentNotifyRecordService.getPaymentNotifyRecordPage(page,paymentNotifyRecord));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
  @GetMapping("/{id}")
  public R<PaymentNotifyRecord> getById(@PathVariable("id") Long id){
    return R.ok(paymentNotifyRecordService.getById(id));
  }

  /**
   * 新增记录
   * @param paymentNotifyRecord
   * @return R
   */
  @ApiOperation(value = "新增支付成功通知记录", notes = "参数： paymentNotifyRecord")
  @SysLog("新增支付成功通知记录")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('payment_paymentnotifyrecord_add')")
  public R save(@RequestBody PaymentNotifyRecord paymentNotifyRecord){
    return R.ok(paymentNotifyRecordService.save(paymentNotifyRecord));
  }

  /**
   * 修改记录
   * @param paymentNotifyRecord
   * @return R
   */
  @ApiOperation(value = "修改支付成功通知记录", notes = "参数： paymentNotifyRecord")
  @SysLog("修改支付成功通知记录")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('payment_paymentnotifyrecord_edit')")
  public R update(@RequestBody PaymentNotifyRecord paymentNotifyRecord){
    return R.ok(paymentNotifyRecordService.updateById(paymentNotifyRecord));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "删除支付成功通知记录", notes = "参数： id")
  @SysLog("删除支付成功通知记录")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('payment_paymentnotifyrecord_del')")
  public R removeById(@PathVariable Long id){
    return R.ok(paymentNotifyRecordService.removeById(id));
  }

}
