package com.junfeng.platform.oc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.junfeng.platform.oc.entity.Gift;
import com.junfeng.platform.oc.service.GiftService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 赠品表
 *
 * @author wangjf
 * @date 2019-10-12 14:09:23
 */
@Api(tags = {"赠品表"})
@RestController
@AllArgsConstructor
@RequestMapping("/gift")
public class GiftController {

  private final  GiftService giftService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param gift 赠品表
   * @return
   */
  @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 赠品表")
  @GetMapping("/page")
  public R<IPage<Gift>> getGiftPage(Page<Gift> page, Gift gift) {
    return R.ok(giftService.getGiftPage(page,gift));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
  @GetMapping("/{id}")
  public R<Gift> getById(@PathVariable("id") Long id){
    return R.ok(giftService.getById(id));
  }

  /**
   * 新增记录
   * @param gift
   * @return R
   */
  @ApiOperation(value = "新增赠品表", notes = "参数： gift")
  @SysLog("新增赠品表")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('oc_gift_add')")
  public R save(@RequestBody Gift gift){
    return R.ok(giftService.saveGift(gift));
  }

  /**
   * 修改记录
   * @param gift
   * @return R
   */
  @ApiOperation(value = "修改赠品表", notes = "参数： gift")
  @SysLog("修改赠品表")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('oc_gift_edit')")
  public R update(@RequestBody Gift gift){
    return R.ok(giftService.updateById(gift));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "删除赠品表", notes = "参数： id")
  @SysLog("删除赠品表")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('oc_gift_del')")
  public R removeById(@PathVariable Long id){
    return R.ok(giftService.removeById(id));
  }

}
