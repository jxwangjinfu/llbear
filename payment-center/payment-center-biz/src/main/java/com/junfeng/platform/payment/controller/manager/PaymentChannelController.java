package com.junfeng.platform.payment.controller.manager;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.payment.api.entity.PaymentChannel;
import com.junfeng.platform.payment.service.PaymentChannelService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 支付通道
 *
 * @author wangk
 * @date 2019-09-19 11:03:43
 */
@Api(tags = {"支付通道"})
@RestController
@AllArgsConstructor
@RequestMapping("/paymentchannel")
public class PaymentChannelController {

  private final PaymentChannelService paymentChannelService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param paymentChannel 支付通道
   * @return
   */
  @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 支付通道")
  @GetMapping("/page")
  public R<IPage<PaymentChannel>> getPaymentChannelPage(Page<PaymentChannel> page, PaymentChannel paymentChannel) {
    return R.ok(paymentChannelService.getPaymentChannelPage(page,paymentChannel));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
  @GetMapping("/{id}")
  public R<PaymentChannel> getById(@PathVariable("id") Long id){
    return R.ok(paymentChannelService.getById(id));
  }

  /**
   * 新增记录
   * @param paymentChannel
   * @return R
   */
  @ApiOperation(value = "新增支付通道", notes = "参数： paymentChannel")
  @SysLog("新增支付通道")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('payment_paymentchannel_add')")
  public R save(@RequestBody PaymentChannel paymentChannel){
    return R.ok(paymentChannelService.save(paymentChannel));
  }

  /**
   * 修改记录
   * @param paymentChannel
   * @return R
   */
  @ApiOperation(value = "修改支付通道", notes = "参数： paymentChannel")
  @SysLog("修改支付通道")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('payment_paymentchannel_edit')")
  public R update(@RequestBody PaymentChannel paymentChannel){
    return R.ok(paymentChannelService.updateById(paymentChannel));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "删除支付通道", notes = "参数： id")
  @SysLog("删除支付通道")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('payment_paymentchannel_del')")
  public R removeById(@PathVariable Long id){
    return R.ok(paymentChannelService.removeById(id));
  }

}
