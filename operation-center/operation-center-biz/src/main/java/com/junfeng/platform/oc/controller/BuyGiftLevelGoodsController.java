package com.junfeng.platform.oc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.junfeng.platform.oc.entity.BuyGiftLevelGoods;
import com.junfeng.platform.oc.service.BuyGiftLevelGoodsService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 买赠等级表
 *
 * @author wangjf
 * @date 2019-10-14 15:09:56
 */
@Api(tags = {"买赠等级礼品表"})
@RestController
@AllArgsConstructor
@RequestMapping("/buygiftlevelgoods")
public class BuyGiftLevelGoodsController {

  private final  BuyGiftLevelGoodsService buyGiftLevelGoodsService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param buyGiftLevelGoods 买赠等级表
   * @return
   */
  @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 买赠等级表")
  @GetMapping("/page")
  public R<IPage<BuyGiftLevelGoods>> getBuyGiftLevelGoodsPage(Page<BuyGiftLevelGoods> page, BuyGiftLevelGoods buyGiftLevelGoods) {
    return R.ok(buyGiftLevelGoodsService.getBuyGiftLevelGoodsPage(page,buyGiftLevelGoods));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
  @GetMapping("/{id}")
  public R<BuyGiftLevelGoods> getById(@PathVariable("id") Long id){
    return R.ok(buyGiftLevelGoodsService.getById(id));
  }

  /**
   * 新增记录
   * @param buyGiftLevelGoods
   * @return R
   */
  @ApiOperation(value = "新增买赠等级表", notes = "参数： buyGiftLevelGoods")
  @SysLog("新增买赠等级表")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('oc_buygiftlevelgoods_add')")
  public R save(@RequestBody BuyGiftLevelGoods buyGiftLevelGoods){
    return R.ok(buyGiftLevelGoodsService.save(buyGiftLevelGoods));
  }

  /**
   * 修改记录
   * @param buyGiftLevelGoods
   * @return R
   */
  @ApiOperation(value = "修改买赠等级表", notes = "参数： buyGiftLevelGoods")
  @SysLog("修改买赠等级表")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('oc_buygiftlevelgoods_edit')")
  public R update(@RequestBody BuyGiftLevelGoods buyGiftLevelGoods){
    return R.ok(buyGiftLevelGoodsService.updateById(buyGiftLevelGoods));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "删除买赠等级表", notes = "参数： id")
  @SysLog("删除买赠等级表")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('oc_buygiftlevelgoods_del')")
  public R removeById(@PathVariable Long id){
    return R.ok(buyGiftLevelGoodsService.removeById(id));
  }

}
