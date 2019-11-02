package com.junfeng.platform.oc.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.oc.entity.CouponQuart;
import com.junfeng.platform.oc.service.CouponQuartService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.Inner;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * 优惠券定时任务触发表
 *
 * @author wangjf
 * @date 2019-09-25 10:47:52
 */
@Api(tags = {"优惠券定时任务触发表"})
@RestController
@AllArgsConstructor
@RequestMapping("/couponquart")
public class CouponQuartController {

    private final CouponQuartService couponQuartService;

    /**
     * 简单分页查询
     * 
     * @param page
     *            分页对象
     * @param couponQuart
     *            优惠券定时任务触发表
     * @return
     */
    @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 优惠券定时任务触发表")
    @GetMapping("/page")
    public R<IPage<CouponQuart>> getCouponQuartPage(Page<CouponQuart> page, CouponQuart couponQuart) {
        return R.ok(couponQuartService.getCouponQuartPage(page, couponQuart));
    }

    /**
     * 通过id查询单条记录
     * 
     * @param id
     * @return R
     */
    @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
    @GetMapping("/{id}")
    public R<CouponQuart> getById(@PathVariable("id") Long id) {
        return R.ok(couponQuartService.getById(id));
    }

    /**
     * 新增记录
     * 
     * @param couponQuart
     * @return R
     */
    @ApiOperation(value = "新增优惠券定时任务触发表", notes = "参数： couponQuart")
    @SysLog("新增优惠券定时任务触发表")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('oc_couponquart_add')")
    public R<Boolean> save(@RequestBody CouponQuart couponQuart) {
        return R.ok(couponQuartService.save(couponQuart));
    }

    /**
     * 修改记录
     * 
     * @param couponQuart
     * @return R
     */
    @ApiOperation(value = "修改优惠券定时任务触发表", notes = "参数： couponQuart")
    @SysLog("修改优惠券定时任务触发表")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('oc_couponquart_edit')")
    public R<Boolean> update(@RequestBody CouponQuart couponQuart) {
        return R.ok(couponQuartService.updateById(couponQuart));
    }

    /**
     * 通过id删除一条记录
     * 
     * @param id
     * @return R
     */
    @ApiOperation(value = "删除优惠券定时任务触发表", notes = "参数： id")
    @SysLog("删除优惠券定时任务触发表")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('oc_couponquart_del')")
    public R<Boolean> removeById(@PathVariable Long id) {
        return R.ok(couponQuartService.removeById(id));
    }
    
    /**
     * 优惠券修改状态
     * @param id
     * @return
     * @author:Wangjf
     * @createTime:2019年9月25日 下午4:50:12
     */
    @Inner
    @PostMapping("/callback/{id}")
    public R<Boolean> couponCallback(@PathVariable Long id) {
        return R.ok(couponQuartService.couponUpdateState(id));
    }

}
