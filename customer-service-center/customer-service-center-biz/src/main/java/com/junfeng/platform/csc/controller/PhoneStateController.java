package com.junfeng.platform.csc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.csc.entity.PhoneState;
import com.junfeng.platform.csc.service.PhoneStateService;
import com.pig4cloud.pig.common.core.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 话机当前状态表
 *
 * @author lw
 * @date 2019-09-29 10:38:14
 */
@Api(tags = {"话机当前状态表"})
@RestController
@AllArgsConstructor
@RequestMapping("/phonestate")
public class PhoneStateController {

  private final  PhoneStateService phoneStateService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param phoneState 话机当前状态表
   * @return
   */
  @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 话机当前状态表")
  @GetMapping("/page")
  public R<IPage<PhoneState>> getPhoneStatePage(Page<PhoneState> page, PhoneState phoneState) {
    return R.ok(phoneStateService.getPhoneStatePage(page,phoneState));
  }
}
