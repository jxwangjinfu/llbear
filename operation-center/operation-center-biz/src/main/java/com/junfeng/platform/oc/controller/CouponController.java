package com.junfeng.platform.oc.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.oc.entity.Coupon;
import com.junfeng.platform.oc.service.CouponService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * 优惠券
 *
 * @author wangjf
 * @date 2019-09-23 15:22:22
 */
@Api(tags = {"优惠券管理"})
@RestController
@AllArgsConstructor
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;

    /**
     * 简单分页查询
     *
     * @param page
     *            分页对象
     * @param coupon
     *            优惠券
     * @return
     */
	@ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 优惠券管理")
    @GetMapping("/page")
    public R<IPage<Coupon>> getCouponPage(Page<Coupon> page, Coupon coupon) {
        return R.ok(couponService.getCouponPage(page, coupon));
    }

    /**
     * 通过id查询单条记录
     *
     * @param id
     * @return R
     */
	@ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
    @GetMapping("/{id}")
    public R<Coupon> getById(@PathVariable("id") Long id) {
        return R.ok(couponService.getById(id));
    }

    /**
     * 新增记录
     *
     * @param coupon
     * @return R
     */
	@ApiOperation(value = "新增优惠券", notes = "参数： coupon")
    @SysLog("新增优惠券")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('oc_coupon_add')")
    public R<Boolean> save(@RequestBody Coupon coupon) {

        return R.ok(couponService.saveCoupon(coupon));
    }

    /**
     * 修改记录
     *
     * @param coupon
     * @return R
     */
	@ApiOperation(value = "修改优惠券", notes = "参数： coupon")
    @SysLog("修改优惠券")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('oc_coupon_edit')")
    public R<Boolean> update(@RequestBody Coupon coupon) {
        return R.ok(couponService.updateById(coupon));
    }

    /**
     * 通过id删除一条记录
     *
     * @param id
     * @return R
     */
	@ApiOperation(value = "删除优惠券会员表", notes = "参数： id")
    @SysLog("删除优惠券")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('oc_coupon_del')")
    public R<Boolean> removeById(@PathVariable Long id) {
        return R.ok(couponService.removeById(id));
    }

}
