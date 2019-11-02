package com.junfeng.platform.pc.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.pc.api.entity.Category;
import com.junfeng.platform.pc.service.CategoryService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.util.SecurityUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * 类目表
 *
 * @author lw
 * @date 2019-10-14 15:19:07
 */
@Api(tags = {"类目表"})
@RestController
@AllArgsConstructor
@RequestMapping("/category")
public class CategoryController {

  private final  CategoryService categoryService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param category 类目表
   * @return
   */
  @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 类目表")
  @GetMapping("/page")
  public R<IPage<Category>> getCategoryPage(Page<Category> page, Category category) {
    return R.ok(categoryService.getCategoryPage(page,category));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
  @GetMapping("/{id}")
  public R<Category> getById(@PathVariable("id") Long id){
    return R.ok(categoryService.getById(id));
  }

  /**
   * 新增记录
   * @param category
   * @return R
   */
  @ApiOperation(value = "新增类目表", notes = "参数： category")
  @SysLog("新增类目表")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('pc_category_add')")
  public R save(@RequestBody Category category){
    category.setCreateBy(SecurityUtils.getUser().getUsername());
  	return R.ok(categoryService.save(category));
  }

  /**
   * 修改记录
   * @param category
   * @return R
   */
  @ApiOperation(value = "修改类目表", notes = "参数： category")
  @SysLog("修改类目表")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('pc_category_edit')")
  public R update(@RequestBody Category category){
  	category.setUpdateBy(SecurityUtils.getUser().getUsername());
    return R.ok(categoryService.updateById(category));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "删除类目表", notes = "参数： id")
  @SysLog("删除类目表")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('pc_category_del')")
  public R removeById(@PathVariable Long id){
    return R.ok(categoryService.removeById(id));
  }

}
