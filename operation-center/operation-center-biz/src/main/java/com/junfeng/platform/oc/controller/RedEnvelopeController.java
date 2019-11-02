package com.junfeng.platform.oc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.junfeng.platform.oc.entity.RedEnvelope;
import com.junfeng.platform.oc.service.RedEnvelopeService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 红包
 *
 * @author wangjf
 * @date 2019-10-09 14:23:25
 */
@Api(tags = {"红包"})
@RestController
@AllArgsConstructor
@RequestMapping("/redenvelope")
public class RedEnvelopeController {

  private final  RedEnvelopeService redEnvelopeService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param redEnvelope 红包
   * @return
   */
  @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 红包")
  @GetMapping("/page")
  public R<IPage<RedEnvelope>> getRedEnvelopePage(Page<RedEnvelope> page, RedEnvelope redEnvelope) {
    return R.ok(redEnvelopeService.getRedEnvelopePage(page,redEnvelope));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
  @GetMapping("/{id}")
  public R<RedEnvelope> getById(@PathVariable("id") Long id){
    return R.ok(redEnvelopeService.getById(id));
  }

  /**
   * 新增记录
   * @param redEnvelope
   * @return R
   */
  @ApiOperation(value = "新增红包", notes = "参数： redEnvelope")
  @SysLog("新增红包")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('oc_redenvelope_add')")
  public R save(@RequestBody RedEnvelope redEnvelope){
    return R.ok(redEnvelopeService.saveRedEnvelope(redEnvelope));
  }

  /**
   * 修改记录
   * @param redEnvelope
   * @return R
   */
  @ApiOperation(value = "修改红包", notes = "参数： redEnvelope")
  @SysLog("修改红包")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('oc_redenvelope_edit')")
  public R update(@RequestBody RedEnvelope redEnvelope){
    return R.ok(redEnvelopeService.updateById(redEnvelope));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "删除红包", notes = "参数： id")
  @SysLog("删除红包")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('oc_redenvelope_del')")
  public R removeById(@PathVariable Long id){
    return R.ok(redEnvelopeService.removeById(id));
  }

}
