package com.junfeng.platform.pc.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.pc.api.entity.SpuDetail;
import com.junfeng.platform.pc.service.SpuDetailService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * spu详细信息
 *
 * @author lw
 * @date 2019-10-14 15:43:56
 */
@Api(tags = {"spu详细信息"})
@RestController
@AllArgsConstructor
@RequestMapping("/spudetail")
public class SpuDetailController {

  private final  SpuDetailService spuDetailService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param spuDetail spu详细信息
   * @return
   */
  @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 spu详细信息")
  @GetMapping("/page")
  public R<IPage<SpuDetail>> getSpuDetailPage(Page<SpuDetail> page, SpuDetail spuDetail) {
    return R.ok(spuDetailService.getSpuDetailPage(page,spuDetail));
  }


  /**
   * 通过id查询单条记录
   * @param spuCode
   * @return R
   */
  @ApiOperation(value = "通过id查询单条记录", notes = "参数： spuCode")
  @GetMapping("/{spuCode}")
  public R<SpuDetail> getById(@PathVariable("spuCode") Long spuCode){
    return R.ok(spuDetailService.getById(spuCode));
  }

  /**
   * 新增记录
   * @param spuDetail
   * @return R
   */
  @ApiOperation(value = "新增spu详细信息", notes = "参数： spuDetail")
  @SysLog("新增spu详细信息")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('pc_spudetail_add')")
  public R save(@RequestBody SpuDetail spuDetail){
    return R.ok(spuDetailService.save(spuDetail));
  }

  /**
   * 修改记录
   * @param spuDetail
   * @return R
   */
  @ApiOperation(value = "修改spu详细信息", notes = "参数： spuDetail")
  @SysLog("修改spu详细信息")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('pc_spudetail_edit')")
  public R update(@RequestBody SpuDetail spuDetail){
    return R.ok(spuDetailService.updateById(spuDetail));
  }

  /**
   * 通过id删除一条记录
   * @param spuCode
   * @return R
   */
  @ApiOperation(value = "删除spu详细信息", notes = "参数： spuCode")
  @SysLog("删除spu详细信息")
  @DeleteMapping("/{spuCode}")
  @PreAuthorize("@pms.hasPermission('pc_spudetail_del')")
  public R removeById(@PathVariable Long spuCode){
    return R.ok(spuDetailService.removeById(spuCode));
  }

}
