package com.junfeng.platform.payment.controller.manager;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.payment.api.entity.PaymentMchInfo;
import com.junfeng.platform.payment.service.PaymentMchInfoService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 商户信息
 *
 * @author wangk
 * @date 2019-09-19 10:18:01
 */
@Api(tags = {"商户信息"})
@RestController
@AllArgsConstructor
@RequestMapping("/paymentmchinfo")
public class PaymentMchInfoController {

  private final PaymentMchInfoService paymentMchInfoService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param paymentMchInfo 商户信息
   * @return
   */
  @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 商户信息")
  @GetMapping("/page")
  public R<IPage<PaymentMchInfo>> getPaymentMchInfoPage(Page<PaymentMchInfo> page, PaymentMchInfo paymentMchInfo) {
    return R.ok(paymentMchInfoService.getPaymentMchInfoPage(page,paymentMchInfo));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
  @GetMapping("/{id}")
  public R<PaymentMchInfo> getById(@PathVariable("id") Long id){
    return R.ok(paymentMchInfoService.getById(id));
  }

  /**
   * 新增记录
   * @param paymentMchInfo
   * @return R
   */
  @ApiOperation(value = "新增商户信息", notes = "参数： paymentMchInfo")
  @SysLog("新增商户信息")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('payment_paymentmchinfo_add')")
  public R save(@RequestBody PaymentMchInfo paymentMchInfo){
    return R.ok(paymentMchInfoService.save(paymentMchInfo));
  }

  /**
   * 修改记录
   * @param paymentMchInfo
   * @return R
   */
  @ApiOperation(value = "修改商户信息", notes = "参数： paymentMchInfo")
  @SysLog("修改商户信息")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('payment_paymentmchinfo_edit')")
  public R update(@RequestBody PaymentMchInfo paymentMchInfo){
    return R.ok(paymentMchInfoService.updateById(paymentMchInfo));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "删除商户信息", notes = "参数： id")
  @SysLog("删除商户信息")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('payment_paymentmchinfo_del')")
  public R removeById(@PathVariable Long id){
    return R.ok(paymentMchInfoService.removeById(id));
  }

}
