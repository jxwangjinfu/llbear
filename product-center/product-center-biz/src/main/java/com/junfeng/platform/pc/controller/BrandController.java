package com.junfeng.platform.pc.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.pc.api.entity.Brand;
import com.junfeng.platform.pc.service.BrandService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.util.SecurityUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * 品牌表
 *
 * @author lw
 * @date 2019-10-14 15:18:47
 */
@Api(tags = {"品牌表"})
@RestController
@AllArgsConstructor
@RequestMapping("/brand")
public class BrandController {

  private final  BrandService brandService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param brand 品牌表
   * @return
   */
  @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 品牌表")
  @GetMapping("/page")
  public R<IPage<Brand>> getBrandPage(Page<Brand> page, Brand brand) {
    return R.ok(brandService.getBrandPage(page,brand));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
  @GetMapping("/{id}")
  public R<Brand> getById(@PathVariable("id") Long id){
    return R.ok(brandService.getById(id));
  }

  /**
   * 新增记录
   * @param brand
   * @return R
   */
  @ApiOperation(value = "新增品牌表", notes = "参数： brand")
  @SysLog("新增品牌表")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('pc_brand_add')")
  public R save(@RequestBody Brand brand){
    brand.setCreateBy(SecurityUtils.getUser().getUsername());
  	return R.ok(brandService.save(brand));
  }

  /**
   * 修改记录
   * @param brand
   * @return R
   */
  @ApiOperation(value = "修改品牌表", notes = "参数： brand")
  @SysLog("修改品牌表")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('pc_brand_edit')")
  public R update(@RequestBody Brand brand) {
	  brand.setUpdateBy(SecurityUtils.getUser().getUsername());
	  return R.ok(brandService.updateById(brand));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "删除品牌表", notes = "参数： id")
  @SysLog("删除品牌表")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('pc_brand_del')")
  public R removeById(@PathVariable Long id){ return R.ok(brandService.removeById(id)); }

}
