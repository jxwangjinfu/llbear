package com.junfeng.platform.payment.controller.manager;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import com.junfeng.platform.payment.service.PaymentOrderRequestRecordService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 支付订单请求记录
 *
 * @author wangk
 * @date 2019-09-19 11:03:55
 */
@Api(tags = {"支付订单请求记录"})
@RestController
@AllArgsConstructor
@RequestMapping("/paymentorderrequestrecord")
public class PaymentOrderRequestRecordController {

  private final PaymentOrderRequestRecordService paymentOrderRequestRecordService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param paymentOrderRequestRecord 支付订单请求记录
   * @return
   */
  @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 支付订单请求记录")
  @GetMapping("/page")
  public R<IPage<PaymentOrderRequestRecord>> getPaymentOrderRequestRecordPage(Page<PaymentOrderRequestRecord> page, PaymentOrderRequestRecord paymentOrderRequestRecord) {
    return R.ok(paymentOrderRequestRecordService.getPaymentOrderRequestRecordPage(page,paymentOrderRequestRecord));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
  @GetMapping("/{id}")
  public R<PaymentOrderRequestRecord> getById(@PathVariable("id") Long id){
    return R.ok(paymentOrderRequestRecordService.getById(id));
  }

  /**
   * 新增记录
   * @param paymentOrderRequestRecord
   * @return R
   */
  @ApiOperation(value = "新增支付订单请求记录", notes = "参数： paymentOrderRequestRecord")
  @SysLog("新增支付订单请求记录")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('payment_paymentorderrequestrecord_add')")
  public R save(@RequestBody PaymentOrderRequestRecord paymentOrderRequestRecord){
    return R.ok(paymentOrderRequestRecordService.save(paymentOrderRequestRecord));
  }

  /**
   * 修改记录
   * @param paymentOrderRequestRecord
   * @return R
   */
  @ApiOperation(value = "修改支付订单请求记录", notes = "参数： paymentOrderRequestRecord")
  @SysLog("修改支付订单请求记录")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('payment_paymentorderrequestrecord_edit')")
  public R update(@RequestBody PaymentOrderRequestRecord paymentOrderRequestRecord){
    return R.ok(paymentOrderRequestRecordService.updateById(paymentOrderRequestRecord));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "删除支付订单请求记录", notes = "参数： id")
  @SysLog("删除支付订单请求记录")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('payment_paymentorderrequestrecord_del')")
  public R removeById(@PathVariable Long id){
    return R.ok(paymentOrderRequestRecordService.removeById(id));
  }

}
