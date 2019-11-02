package com.junfeng.platform.oc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.junfeng.platform.oc.entity.CouponConfig;
import com.junfeng.platform.oc.service.CouponConfigService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 优惠券使用配置表
 *
 * @author wangjf
 * @date 2019-10-09 10:53:35
 */
@Api(tags = {"优惠券使用配置表"})
@RestController
@AllArgsConstructor
@RequestMapping("/couponconfig")
public class CouponConfigController {

    private final CouponConfigService couponConfigService;

    /**
     * 简单分页查询
     *
     * @param page
     *            分页对象
     * @param couponConfig
     *            优惠券使用配置表
     * @return
     */
    @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 优惠券使用配置表")
    @GetMapping("/page")
    public R<IPage<CouponConfig>> getCouponConfigPage(Page<CouponConfig> page, CouponConfig couponConfig) {
        return R.ok(couponConfigService.getCouponConfigPage(page, couponConfig));
    }

    /**
     * 通过id查询单条记录
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
    @GetMapping("/{id}")
    public R<CouponConfig> getById(@PathVariable("id") Long id) {
        return R.ok(couponConfigService.getById(id));
    }

    /**
     * 新增记录
     *
     * @param couponConfig
     * @return R
     */
    @ApiOperation(value = "新增优惠券使用配置表", notes = "参数： couponConfig")
    @SysLog("新增优惠券使用配置表")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('oc_couponconfig_add')")
    public R save(@RequestBody CouponConfig couponConfig) {
        return R.ok(couponConfigService.save(couponConfig));
    }

    /**
     * 修改记录
     *
     * @param couponConfig
     * @return R
     */
    @ApiOperation(value = "修改优惠券使用配置表", notes = "参数： couponConfig")
    @SysLog("修改优惠券使用配置表")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('oc_couponconfig_edit')")
    public R update(@RequestBody CouponConfig couponConfig) {
        return R.ok(couponConfigService.updateById(couponConfig));
    }

    /**
     * 通过id删除一条记录
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "删除优惠券使用配置表", notes = "参数： id")
    @SysLog("删除优惠券使用配置表")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('oc_couponconfig_del')")
    public R removeById(@PathVariable Long id) {
        return R.ok(couponConfigService.removeById(id));
    }

}
