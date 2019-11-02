package com.junfeng.platform.oc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.junfeng.platform.oc.entity.BuyGiftLevel;
import com.junfeng.platform.oc.service.BuyGiftLevelService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 买赠等级表
 *
 * @author wangjf
 * @date 2019-10-15 14:32:05
 */
@Api(tags = {"买赠等级表"})
@RestController
@AllArgsConstructor
@RequestMapping("/buygiftlevel")
public class BuyGiftLevelController {

  private final  BuyGiftLevelService buyGiftLevelService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param buyGiftLevel 买赠等级表
   * @return
   */
  @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 买赠等级表")
  @GetMapping("/page")
  public R<IPage<BuyGiftLevel>> getBuyGiftLevelPage(Page<BuyGiftLevel> page, BuyGiftLevel buyGiftLevel) {
    return R.ok(buyGiftLevelService.getBuyGiftLevelPage(page,buyGiftLevel));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
  @GetMapping("/{id}")
  public R<BuyGiftLevel> getById(@PathVariable("id") Long id){
    return R.ok(buyGiftLevelService.getById(id));
  }

  /**
   * 新增记录
   * @param buyGiftLevel
   * @return R
   */
  @ApiOperation(value = "新增买赠等级表", notes = "参数： buyGiftLevel")
  @SysLog("新增买赠等级表")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('oc_buygiftlevel_add')")
  public R save(@RequestBody BuyGiftLevel buyGiftLevel){
    return R.ok(buyGiftLevelService.save(buyGiftLevel));
  }

  /**
   * 修改记录
   * @param buyGiftLevel
   * @return R
   */
  @ApiOperation(value = "修改买赠等级表", notes = "参数： buyGiftLevel")
  @SysLog("修改买赠等级表")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('oc_buygiftlevel_edit')")
  public R update(@RequestBody BuyGiftLevel buyGiftLevel){
    return R.ok(buyGiftLevelService.updateById(buyGiftLevel));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "删除买赠等级表", notes = "参数： id")
  @SysLog("删除买赠等级表")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('oc_buygiftlevel_del')")
  public R removeById(@PathVariable Long id){
    return R.ok(buyGiftLevelService.removeById(id));
  }

}
