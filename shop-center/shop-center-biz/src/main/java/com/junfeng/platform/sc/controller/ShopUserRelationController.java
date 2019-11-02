package com.junfeng.platform.sc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.junfeng.platform.sc.entity.ShopUserRelation;
import com.junfeng.platform.sc.service.ShopUserRelationService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 店铺用户关联信息
 *
 * @author lw
 * @date 2019-10-21 13:49:45
 */
@Api(tags = {"店铺用户关联信息"})
@RestController
@AllArgsConstructor
@RequestMapping("/shopuserrelation")
public class ShopUserRelationController {

  private final  ShopUserRelationService shopUserRelationService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param shopUserRelation 店铺用户关联信息
   * @return
   */
  @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 店铺用户关联信息")
  @GetMapping("/page")
  public R<IPage<ShopUserRelation>> getShopUserRelationPage(Page<ShopUserRelation> page, ShopUserRelation shopUserRelation) {
    return R.ok(shopUserRelationService.getShopUserRelationPage(page,shopUserRelation));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
  @GetMapping("/{id}")
  public R<ShopUserRelation> getById(@PathVariable("id") Long id){
    return R.ok(shopUserRelationService.getById(id));
  }

  /**
   * 新增记录
   * @param shopUserRelation
   * @return R
   */
  @ApiOperation(value = "新增店铺用户关联信息", notes = "参数： shopUserRelation")
  @SysLog("新增店铺用户关联信息")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('sc_shopuserrelation_add')")
  public R save(@RequestBody ShopUserRelation shopUserRelation){
    return R.ok(shopUserRelationService.save(shopUserRelation));
  }

  /**
   * 修改记录
   * @param shopUserRelation
   * @return R
   */
  @ApiOperation(value = "修改店铺用户关联信息", notes = "参数： shopUserRelation")
  @SysLog("修改店铺用户关联信息")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('sc_shopuserrelation_edit')")
  public R update(@RequestBody ShopUserRelation shopUserRelation){
    return R.ok(shopUserRelationService.updateById(shopUserRelation));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "删除店铺用户关联信息", notes = "参数： id")
  @SysLog("删除店铺用户关联信息")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('sc_shopuserrelation_del')")
  public R removeById(@PathVariable Long id){
    return R.ok(shopUserRelationService.removeById(id));
  }

}
