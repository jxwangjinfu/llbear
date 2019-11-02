package com.junfeng.platform.oc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.junfeng.platform.oc.entity.BuyGiftMember;
import com.junfeng.platform.oc.service.BuyGiftMemberService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 买赠会员表
 *
 * @author wangjf
 * @date 2019-10-14 15:10:06
 */
@Api(tags = {"买赠会员表"})
@RestController
@AllArgsConstructor
@RequestMapping("/buygiftmember")
public class BuyGiftMemberController {

  private final  BuyGiftMemberService buyGiftMemberService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param buyGiftMember 买赠会员表
   * @return
   */
  @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 买赠会员表")
  @GetMapping("/page")
  public R<IPage<BuyGiftMember>> getBuyGiftMemberPage(Page<BuyGiftMember> page, BuyGiftMember buyGiftMember) {
    return R.ok(buyGiftMemberService.getBuyGiftMemberPage(page,buyGiftMember));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
  @GetMapping("/{id}")
  public R<BuyGiftMember> getById(@PathVariable("id") Long id){
    return R.ok(buyGiftMemberService.getById(id));
  }

  /**
   * 新增记录
   * @param buyGiftMember
   * @return R
   */
  @ApiOperation(value = "新增买赠会员表", notes = "参数： buyGiftMember")
  @SysLog("新增买赠会员表")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('oc_buygiftmember_add')")
  public R save(@RequestBody BuyGiftMember buyGiftMember){
    return R.ok(buyGiftMemberService.save(buyGiftMember));
  }

  /**
   * 修改记录
   * @param buyGiftMember
   * @return R
   */
  @ApiOperation(value = "修改买赠会员表", notes = "参数： buyGiftMember")
  @SysLog("修改买赠会员表")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('oc_buygiftmember_edit')")
  public R update(@RequestBody BuyGiftMember buyGiftMember){
    return R.ok(buyGiftMemberService.updateById(buyGiftMember));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "删除买赠会员表", notes = "参数： id")
  @SysLog("删除买赠会员表")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('oc_buygiftmember_del')")
  public R removeById(@PathVariable Long id){
    return R.ok(buyGiftMemberService.removeById(id));
  }

}
