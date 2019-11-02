package com.junfeng.platform.sc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.junfeng.platform.sc.entity.ShopType;
import com.junfeng.platform.sc.service.ShopTypeService;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 店铺类型
 *
 * @author lw
 * @date 2019-10-23 14:57:35
 */
@Api(tags = {"店铺类型"})
@RestController
@AllArgsConstructor
@RequestMapping("/shoptype")
public class ShopTypeController {

  private final  ShopTypeService shopTypeService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param shopType 店铺类型
   * @return
   */
  @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 店铺类型")
  @GetMapping("/page")
  public R<IPage<ShopType>> getShopTypePage(Page<ShopType> page, ShopType shopType) {
    return R.ok(shopTypeService.getShopTypePage(page,shopType));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
  @GetMapping("/{id}")
  public R<ShopType> getById(@PathVariable("id") Long id){
    return R.ok(shopTypeService.getById(id));
  }

  /**
   * 新增记录
   * @param shopType
   * @return R
   */
  @ApiOperation(value = "新增店铺类型", notes = "参数： shopType")
  @SysLog("新增店铺类型")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('sc_shoptype_add')")
  public R save(@RequestBody ShopType shopType){
    return R.ok(shopTypeService.saveWithCheck(shopType));
  }

  /**
   * 修改记录
   * @param shopType
   * @return R
   */
  @ApiOperation(value = "修改店铺类型", notes = "参数： shopType")
  @SysLog("修改店铺类型")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('sc_shoptype_edit')")
  public R update(@RequestBody ShopType shopType){
	  shopType.setUpdateBy(SecurityUtils.getUser().getUsername());
  	return R.ok(shopTypeService.updateById(shopType));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "删除店铺类型", notes = "参数： id")
  @SysLog("删除店铺类型")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('sc_shoptype_del')")
  public R removeById(@PathVariable Long id){
    return R.ok(shopTypeService.removeById(id));
  }

}
