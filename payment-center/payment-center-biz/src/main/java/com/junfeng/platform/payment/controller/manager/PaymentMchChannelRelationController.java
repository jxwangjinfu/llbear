package com.junfeng.platform.payment.controller.manager;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.payment.api.entity.PaymentMchChannelRelation;
import com.junfeng.platform.payment.service.PaymentMchChannelRelationService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 商户支付通道
 *
 * @author wangk
 * @date 2019-09-25 15:30:24
 */
@Api(tags = {"商户支付通道"})
@RestController
@AllArgsConstructor
@RequestMapping("/paymentmchchannelrelation")
public class PaymentMchChannelRelationController {

  private final PaymentMchChannelRelationService paymentMchChannelRelationService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param paymentMchChannelRelation 商户支付通道
   * @return
   */
  @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 商户支付通道")
  @GetMapping("/page")
  public R<IPage<PaymentMchChannelRelation>> getPaymentMchChannelRelationPage(Page<PaymentMchChannelRelation> page, PaymentMchChannelRelation paymentMchChannelRelation) {
    return R.ok(paymentMchChannelRelationService.getPaymentMchChannelRelationPage(page,paymentMchChannelRelation));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
  @GetMapping("/{id}")
  public R<PaymentMchChannelRelation> getById(@PathVariable("id") Long id){
    return R.ok(paymentMchChannelRelationService.getById(id));
  }

  /**
   * 新增记录
   * @param paymentMchChannelRelation
   * @return R
   */
  @ApiOperation(value = "新增商户支付通道", notes = "参数： paymentMchChannelRelation")
  @SysLog("新增商户支付通道")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('payment_paymentmchchannelrelation_add')")
  public R save(@RequestBody PaymentMchChannelRelation paymentMchChannelRelation){
    return R.ok(paymentMchChannelRelationService.save(paymentMchChannelRelation));
  }

  /**
   * 修改记录
   * @param paymentMchChannelRelation
   * @return R
   */
  @ApiOperation(value = "修改商户支付通道", notes = "参数： paymentMchChannelRelation")
  @SysLog("修改商户支付通道")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('payment_paymentmchchannelrelation_edit')")
  public R update(@RequestBody PaymentMchChannelRelation paymentMchChannelRelation){
    return R.ok(paymentMchChannelRelationService.updateById(paymentMchChannelRelation));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "删除商户支付通道", notes = "参数： id")
  @SysLog("删除商户支付通道")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('payment_paymentmchchannelrelation_del')")
  public R removeById(@PathVariable Long id){
    return R.ok(paymentMchChannelRelationService.removeById(id));
  }

}
