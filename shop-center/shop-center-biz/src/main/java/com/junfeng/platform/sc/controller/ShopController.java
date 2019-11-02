package com.junfeng.platform.sc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.junfeng.platform.sc.entity.Shop;
import com.junfeng.platform.sc.service.ShopService;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 店铺信息
 *
 * @author lw
 * @date 2019-10-21 13:49:30
 */
@Api(tags = {"店铺信息"})
@RestController
@AllArgsConstructor
@RequestMapping("/shop")
public class ShopController {

  private final  ShopService shopService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param shop 店铺信息
   * @return
   */
  @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 店铺信息")
  @GetMapping("/page")
  public R<IPage<Shop>> getShopPage(Page<Shop> page, Shop shop) {
    return R.ok(shopService.getShopPage(page,shop));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
  @GetMapping("/{id}")
  public R<Shop> getById(@PathVariable("id") Long id){
    return R.ok(shopService.getById(id));
  }

  /**
   * 新增记录
   * @param shop
   * @return R
   */
  @ApiOperation(value = "新增店铺信息", notes = "参数： shop")
  @SysLog("新增店铺信息")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('sc_shop_add')")
  public R save(@RequestBody Shop shop){
  	shop.setCreateBy(SecurityUtils.getUser().getUsername());
    return R.ok(shopService.AddShop(shop));
  }

  /**
   * 修改记录
   * @param shop
   * @return R
   */
  @ApiOperation(value = "修改店铺信息", notes = "参数： shop")
  @SysLog("修改店铺信息")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('sc_shop_edit')")
  public R update(@RequestBody Shop shop){
    shop.setUpdateBy(SecurityUtils.getUser().getUsername());
  	return R.ok(shopService.updateById(shop));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "删除店铺信息", notes = "参数： id")
  @SysLog("删除店铺信息")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('sc_shop_del')")
  public R removeById(@PathVariable Long id){
    return R.ok(shopService.removeById(id));
  }

}
