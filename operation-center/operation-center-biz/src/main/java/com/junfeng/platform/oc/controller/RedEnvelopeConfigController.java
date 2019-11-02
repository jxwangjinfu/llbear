package com.junfeng.platform.oc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.junfeng.platform.oc.entity.RedEnvelopeConfig;
import com.junfeng.platform.oc.service.RedEnvelopeConfigService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 红包使用配置表
 *
 * @author wangjf
 * @date 2019-10-09 14:24:01
 */
@Api(tags = {"红包使用配置表"})
@RestController
@AllArgsConstructor
@RequestMapping("/redenvelopeconfig")
public class RedEnvelopeConfigController {

  private final  RedEnvelopeConfigService redEnvelopeConfigService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param redEnvelopeConfig 红包使用配置表
   * @return
   */
  @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 红包使用配置表")
  @GetMapping("/page")
  public R<IPage<RedEnvelopeConfig>> getRedEnvelopeConfigPage(Page<RedEnvelopeConfig> page, RedEnvelopeConfig redEnvelopeConfig) {
    return R.ok(redEnvelopeConfigService.getRedEnvelopeConfigPage(page,redEnvelopeConfig));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
  @GetMapping("/{id}")
  public R<RedEnvelopeConfig> getById(@PathVariable("id") Long id){
    return R.ok(redEnvelopeConfigService.getById(id));
  }

  /**
   * 新增记录
   * @param redEnvelopeConfig
   * @return R
   */
  @ApiOperation(value = "新增红包使用配置表", notes = "参数： redEnvelopeConfig")
  @SysLog("新增红包使用配置表")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('oc_redenvelopeconfig_add')")
  public R save(@RequestBody RedEnvelopeConfig redEnvelopeConfig){
    return R.ok(redEnvelopeConfigService.save(redEnvelopeConfig));
  }

  /**
   * 修改记录
   * @param redEnvelopeConfig
   * @return R
   */
  @ApiOperation(value = "修改红包使用配置表", notes = "参数： redEnvelopeConfig")
  @SysLog("修改红包使用配置表")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('oc_redenvelopeconfig_edit')")
  public R update(@RequestBody RedEnvelopeConfig redEnvelopeConfig){
    return R.ok(redEnvelopeConfigService.updateById(redEnvelopeConfig));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "删除红包使用配置表", notes = "参数： id")
  @SysLog("删除红包使用配置表")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('oc_redenvelopeconfig_del')")
  public R removeById(@PathVariable Long id){
    return R.ok(redEnvelopeConfigService.removeById(id));
  }

}
