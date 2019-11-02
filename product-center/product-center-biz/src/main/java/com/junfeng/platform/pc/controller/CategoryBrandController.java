package com.junfeng.platform.pc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.junfeng.platform.pc.api.entity.CategoryBrand;
import com.junfeng.platform.pc.service.CategoryBrandService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 品牌类目关联表
 *
 * @author lw
 * @date 2019-10-14 15:19:51
 */
@Api(tags = {"品牌类目关联表"})
@RestController
@AllArgsConstructor
@RequestMapping("/categorybrand")
public class CategoryBrandController {

  private final  CategoryBrandService categoryBrandService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param categoryBrand 品牌类目关联表
   * @return
   */
  @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 品牌类目关联表")
  @GetMapping("/page")
  public R<IPage<CategoryBrand>> getCategoryBrandPage(Page<CategoryBrand> page, CategoryBrand categoryBrand) {
    return R.ok(categoryBrandService.getCategoryBrandPage(page,categoryBrand));
  }


  /**
   * 通过id查询单条记录
   * @param categoryId
   * @return R
   */
  @ApiOperation(value = "通过id查询单条记录", notes = "参数： categoryId")
  @GetMapping("/{categoryId}")
  public R<CategoryBrand> getById(@PathVariable("categoryId") Long categoryId){
    return R.ok(categoryBrandService.getById(categoryId));
  }

  /**
   * 新增记录
   * @param categoryBrand
   * @return R
   */
  @ApiOperation(value = "新增品牌类目关联表", notes = "参数： categoryBrand")
  @SysLog("新增品牌类目关联表")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('pc_categorybrand_add')")
  public R save(@RequestBody CategoryBrand categoryBrand){
    return R.ok(categoryBrandService.save(categoryBrand));
  }

  /**
   * 修改记录
   * @param categoryBrand
   * @return R
   */
  @ApiOperation(value = "修改品牌类目关联表", notes = "参数： categoryBrand")
  @SysLog("修改品牌类目关联表")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('pc_categorybrand_edit')")
  public R update(@RequestBody CategoryBrand categoryBrand){
    return R.ok(categoryBrandService.updateById(categoryBrand));
  }

  /**
   * 通过id删除一条记录
   * @param categoryId
   * @return R
   */
  @ApiOperation(value = "删除品牌类目关联表", notes = "参数： categoryId")
  @SysLog("删除品牌类目关联表")
  @DeleteMapping("/{categoryId}")
  @PreAuthorize("@pms.hasPermission('pc_categorybrand_del')")
  public R removeById(@PathVariable Long categoryId){
    return R.ok(categoryBrandService.removeById(categoryId));
  }

}
