package com.junfeng.platform.oc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.junfeng.platform.oc.entity.PointsMemberFlow;
import com.junfeng.platform.oc.service.PointsMemberFlowService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会员获取积分流水
 *
 * @author wangjf
 * @date 2019-10-21 14:55:59
 */
@Api(tags = {"会员获取积分流水"})
@RestController
@AllArgsConstructor
@RequestMapping("/pointsmemberflow")
public class PointsMemberFlowController {

  private final  PointsMemberFlowService pointsMemberFlowService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param pointsMemberFlow 会员获取积分流水
   * @return
   */
  @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 会员获取积分流水")
  @GetMapping("/page")
  public R<IPage<PointsMemberFlow>> getPointsMemberFlowPage(Page<PointsMemberFlow> page, PointsMemberFlow pointsMemberFlow) {
    return R.ok(pointsMemberFlowService.getPointsMemberFlowPage(page,pointsMemberFlow));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
  @GetMapping("/{id}")
  public R<PointsMemberFlow> getById(@PathVariable("id") Long id){
    return R.ok(pointsMemberFlowService.getById(id));
  }

  /**
   * 新增记录
   * @param pointsMemberFlow
   * @return R
   */
  @ApiOperation(value = "新增会员获取积分流水", notes = "参数： pointsMemberFlow")
  @SysLog("新增会员获取积分流水")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('oc_pointsmemberflow_add')")
  public R save(@RequestBody PointsMemberFlow pointsMemberFlow){
    return R.ok(pointsMemberFlowService.save(pointsMemberFlow));
  }

  /**
   * 修改记录
   * @param pointsMemberFlow
   * @return R
   */
  @ApiOperation(value = "修改会员获取积分流水", notes = "参数： pointsMemberFlow")
  @SysLog("修改会员获取积分流水")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('oc_pointsmemberflow_edit')")
  public R update(@RequestBody PointsMemberFlow pointsMemberFlow){
    return R.ok(pointsMemberFlowService.updateById(pointsMemberFlow));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "删除会员获取积分流水", notes = "参数： id")
  @SysLog("删除会员获取积分流水")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('oc_pointsmemberflow_del')")
  public R removeById(@PathVariable Long id){
    return R.ok(pointsMemberFlowService.removeById(id));
  }

}
