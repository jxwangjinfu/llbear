package com.junfeng.platform.pc.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.pc.api.entity.CategorySpecificationTemplate;
import com.junfeng.platform.pc.service.CategorySpecificationTemplateService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.util.SecurityUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * 类目关联模板
 *
 * @author lw
 * @date 2019-10-14 15:19:25
 */
@Api(tags = {"类目关联模板"})
@RestController
@AllArgsConstructor
@RequestMapping("/categoryspecificationtemplate")
public class CategorySpecificationTemplateController {

  private final  CategorySpecificationTemplateService categorySpecificationTemplateService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param categorySpecificationTemplate 类目关联模板
   * @return
   */
  @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 类目关联模板")
  @GetMapping("/page")
  public R<IPage<CategorySpecificationTemplate>> getCategorySpecificationTemplatePage(Page<CategorySpecificationTemplate> page, CategorySpecificationTemplate categorySpecificationTemplate) {
    return R.ok(categorySpecificationTemplateService.getCategorySpecificationTemplatePage(page,categorySpecificationTemplate));
  }


  /**
   * 通过id查询单条记录
   * @param categoryId
   * @return R
   */
  @ApiOperation(value = "通过id查询单条记录", notes = "参数： categoryId")
  @GetMapping("/{categoryId}")
  public R<CategorySpecificationTemplate> getById(@PathVariable("categoryId") Long categoryId){
    return R.ok(categorySpecificationTemplateService.getById(categoryId));
  }

  /**
   * 新增记录
   * @param categorySpecificationTemplate
   * @return R
   */
  @ApiOperation(value = "新增类目关联模板", notes = "参数： categorySpecificationTemplate")
  @SysLog("新增类目关联模板")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('pc_categoryspecificationtemplate_add')")
  public R save(@RequestBody CategorySpecificationTemplate categorySpecificationTemplate){
	  categorySpecificationTemplate.setCreateBy(SecurityUtils.getUser().getUsername());
    return R.ok(categorySpecificationTemplateService.save(categorySpecificationTemplate));
  }

  /**
   * 修改记录
   * @param categorySpecificationTemplate
   * @return R
   */
  @ApiOperation(value = "修改类目关联模板", notes = "参数： categorySpecificationTemplate")
  @SysLog("修改类目关联模板")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('pc_categoryspecificationtemplate_edit')")
  public R update(@RequestBody CategorySpecificationTemplate categorySpecificationTemplate){
  	categorySpecificationTemplate.setUpdateBy(SecurityUtils.getUser().getUsername());
    return R.ok(categorySpecificationTemplateService.updateById(categorySpecificationTemplate));
  }

  /**
   * 通过id删除一条记录
   * @param categoryId
   * @return R
   */
/*  @ApiOperation(value = "删除类目关联模板", notes = "参数： categoryId")
  @SysLog("删除类目关联模板")
  @DeleteMapping("/{categoryId}")
  @PreAuthorize("@pms.hasPermission('pc_categoryspecificationtemplate_del')")
  public R removeById(@PathVariable Long categoryId){
    return R.ok(categorySpecificationTemplateService.removeById(categoryId));
  }*/

}
