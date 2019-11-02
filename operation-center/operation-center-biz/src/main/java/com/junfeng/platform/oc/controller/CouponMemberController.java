package com.junfeng.platform.oc.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.oc.entity.CouponMember;
import com.junfeng.platform.oc.service.CouponMemberService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * 优惠券会员表
 *
 * @author wangjf
 * @date 2019-09-29 16:51:58
 */
@Api(tags = {"优惠券会员表"})
@RestController
@AllArgsConstructor
@RequestMapping("/couponmember")
public class CouponMemberController {

    private final CouponMemberService couponMemberService;

    /**
     * 简单分页查询
     *
     * @param page
     *            分页对象
     * @param couponMember
     *            优惠券会员表
     * @return
     */
    @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 优惠券会员表")
    @GetMapping("/page")
    public R<IPage<CouponMember>> getCouponMemberPage(Page<CouponMember> page, CouponMember couponMember) {
        return R.ok(couponMemberService.getCouponMemberPage(page, couponMember));
    }

    /**
     * 通过id查询单条记录
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
    @GetMapping("/{id}")
    public R<CouponMember> getById(@PathVariable("id") Long id) {
        return R.ok(couponMemberService.getById(id));
    }

    /**
     * 新增记录
     *
     * @param couponMember
     * @return R
     */
    @ApiOperation(value = "新增优惠券会员表", notes = "参数： couponMember")
    @SysLog("新增优惠券会员表")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('oc_couponmember_add')")
    public R save(@RequestBody CouponMember couponMember) {
        return R.ok(couponMemberService.save(couponMember));
    }

    /**
     * 修改记录
     *
     * @param couponMember
     * @return R
     */
    @ApiOperation(value = "修改优惠券会员表", notes = "参数： couponMember")
    @SysLog("修改优惠券会员表")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('oc_couponmember_edit')")
    public R update(@RequestBody CouponMember couponMember) {
        return R.ok(couponMemberService.updateById(couponMember));
    }

    /**
     * 通过id删除一条记录
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "删除优惠券会员表", notes = "参数： id")
    @SysLog("删除优惠券会员表")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('oc_couponmember_del')")
    public R removeById(@PathVariable Long id) {
        return R.ok(couponMemberService.removeById(id));
    }

    /**
     * 会员获取优惠券
     *
     * @author: wangjf
     * @createTime: 2019/9/30 16:00
     * @param memberId
     * @param couponId
     * @return com.pig4cloud.pig.common.core.util.R<java.lang.String>
     */
    @ApiOperation(value = "会员领取优惠券", notes = "参数： memberId和couponid")
    @SysLog("会员领取优惠券")
    @PutMapping("/{memberId}/{couponId}")
    @PreAuthorize("@pms.hasPermission('oc_couponmember_get')")
    public R<String> updateCoupon(@PathVariable("memberId") Long memberId, @PathVariable("couponId") Long couponId) {
        return R.ok(couponMemberService.updateCoupon(memberId, couponId));
    }

}
