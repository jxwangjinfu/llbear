package com.junfeng.platform.mc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.mc.api.entity.MemberAddress;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.junfeng.platform.mc.service.MemberAddressService;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会员地址
 *
 * @author daiysh
 * @date 2019-09-23 09:32:24
 */
@Api(tags = {"会员地址"})
@RestController
@AllArgsConstructor
@RequestMapping("/memberaddress")
public class MemberAddressController {

  private final  MemberAddressService memberAddressService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param memberAddress 会员地址
   * @return
   */
  @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 会员地址")
  @GetMapping("/page")
  public R<IPage<MemberAddress>> getMemberAddressPage(Page<MemberAddress> page, MemberAddress memberAddress) {
	  PigUser user = SecurityUtils.getUser();
	  return R.ok(memberAddressService.getMemberAddressPage(page,memberAddress));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
  @GetMapping("/{id}")
  public R<MemberAddress> getById(@PathVariable("id") Long id){
    return R.ok(memberAddressService.getById(id));
  }

  /**
   * 新增记录
   * @param memberAddress
   * @return R
   */
  @ApiOperation(value = "新增会员地址", notes = "参数： memberAddress")
  @SysLog("新增会员地址")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('mc_memberaddress_add')")
  public R save(@RequestBody MemberAddress memberAddress){
    return R.ok(memberAddressService.save(memberAddress));
  }

  /**
   * 修改记录
   * @param memberAddress
   * @return R
   */
  @ApiOperation(value = "修改会员地址", notes = "参数： memberAddress")
  @SysLog("修改会员地址")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('mc_memberaddress_edit')")
  public R update(@RequestBody MemberAddress memberAddress){
    return R.ok(memberAddressService.updateById(memberAddress));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @ApiOperation(value = "删除会员地址", notes = "参数： id")
  @SysLog("删除会员地址")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('mc_memberaddress_del')")
  public R removeById(@PathVariable Long id){
    return R.ok(memberAddressService.removeById(id));
  }

}
