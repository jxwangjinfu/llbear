package com.junfeng.platform.pc.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.pc.api.entity.SkuStockRecord;
import com.junfeng.platform.pc.service.SkuStockRecordService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * 库存变动流水
 *
 * @author lw
 * @date 2019-10-15 16:44:39
 */
@Api(tags = {"库存变动流水"})
@RestController
@AllArgsConstructor
@RequestMapping("/skustockrecord")
public class SkuStockRecordController {

  private final  SkuStockRecordService skuStockRecordService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param skuStockRecord 库存变动流水
   * @return
   */
  @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 库存变动流水")
  @GetMapping("/page")
  public R<IPage<SkuStockRecord>> getSkuStockRecordPage(Page<SkuStockRecord> page, SkuStockRecord skuStockRecord) {
    return R.ok(skuStockRecordService.getSkuStockRecordPage(page,skuStockRecord));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
  @GetMapping("/{id}")
  public R<SkuStockRecord> getById(@PathVariable("id") Long id){
    return R.ok(skuStockRecordService.getById(id));
  }

  /**
   * 新增记录
   * @param skuStockRecord
   * @return R
   */
  @ApiOperation(value = "新增库存变动流水", notes = "参数： skuStockRecord")
  @SysLog("新增库存变动流水")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('pc_skustockrecord_add')")
  public R save(@RequestBody SkuStockRecord skuStockRecord){
    return R.ok(skuStockRecordService.save(skuStockRecord));
  }

  /**
   * 修改记录
   * @param skuStockRecord
   * @return R
   */
  @ApiOperation(value = "修改库存变动流水", notes = "参数： skuStockRecord")
  @SysLog("修改库存变动流水")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('pc_skustockrecord_edit')")
  public R update(@RequestBody SkuStockRecord skuStockRecord){
    return R.ok(skuStockRecordService.updateById(skuStockRecord));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "删除库存变动流水", notes = "参数： id")
  @SysLog("删除库存变动流水")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('pc_skustockrecord_del')")
  public R removeById(@PathVariable Long id){
    return R.ok(skuStockRecordService.removeById(id));
  }

}
