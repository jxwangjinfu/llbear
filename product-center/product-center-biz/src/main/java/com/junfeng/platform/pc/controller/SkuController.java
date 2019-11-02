package com.junfeng.platform.pc.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.pc.api.entity.Sku;
import com.junfeng.platform.pc.service.SkuService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * sku信息
 *
 * @author lw
 * @date 2019-10-12 18:25:42
 */
@Api(tags = {"sku信息"})
@RestController
@AllArgsConstructor
@RequestMapping("/sku")
public class SkuController {

  private final  SkuService skuService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param sku sku信息
   * @return
   */
  @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 sku信息")
  @GetMapping("/page")
  public R<IPage<Sku>> getSkuPage(Page<Sku> page, Sku sku) {
    return R.ok(skuService.page(page, Wrappers.<Sku>lambdaQuery()));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
  @GetMapping("/{id}")
  public R<Sku> getById(@PathVariable("id") Long id){
    return R.ok(skuService.getById(id));
  }

  /**
   * 新增记录
   * @param sku
   * @return R
   */
  @ApiOperation(value = "新增sku信息", notes = "参数： sku")
  @SysLog("新增sku信息")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('pc_sku_add')")
  public R save(@RequestBody Sku sku){
    return R.ok(skuService.save(sku));
  }

  /**
   * 修改记录
   * @param sku
   * @return R
   */
  @ApiOperation(value = "修改sku信息", notes = "参数： sku")
  @SysLog("修改sku信息")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('pc_sku_edit')")
  public R update(@RequestBody Sku sku){
    return R.ok(skuService.updateById(sku));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "删除sku信息", notes = "参数： id")
  @SysLog("删除sku信息")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('pc_sku_del')")
  public R removeById(@PathVariable Long id){
    return R.ok(skuService.removeById(id));
  }

}
