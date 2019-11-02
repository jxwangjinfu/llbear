package com.junfeng.platform.oc.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.oc.entity.GiftQuart;
import com.junfeng.platform.oc.service.GiftQuartService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.Inner;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * 赠品定时任务触发表
 *
 * @author wangjf
 * @date 2019-10-12 14:55:40
 */
@Api(tags = {"赠品定时任务触发表"})
@RestController
@AllArgsConstructor
@RequestMapping("/giftquart")
public class GiftQuartController {

    private final GiftQuartService giftQuartService;

    /**
     * 简单分页查询
     *
     * @param page
     *            分页对象
     * @param giftQuart
     *            赠品定时任务触发表
     * @return
     */
    @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 赠品定时任务触发表")
    @GetMapping("/page")
    public R<IPage<GiftQuart>> getGiftQuartPage(Page<GiftQuart> page, GiftQuart giftQuart) {
        return R.ok(giftQuartService.getGiftQuartPage(page, giftQuart));
    }

    /**
     * 通过id查询单条记录
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
    @GetMapping("/{id}")
    public R<GiftQuart> getById(@PathVariable("id") Long id) {
        return R.ok(giftQuartService.getById(id));
    }

    /**
     * 新增记录
     *
     * @param giftQuart
     * @return R
     */
    @ApiOperation(value = "新增赠品定时任务触发表", notes = "参数： giftQuart")
    @SysLog("新增赠品定时任务触发表")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('oc_giftquart_add')")
    public R save(@RequestBody GiftQuart giftQuart) {
        return R.ok(giftQuartService.save(giftQuart));
    }

    /**
     * 修改记录
     *
     * @param giftQuart
     * @return R
     */
    @ApiOperation(value = "修改赠品定时任务触发表", notes = "参数： giftQuart")
    @SysLog("修改赠品定时任务触发表")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('oc_giftquart_edit')")
    public R update(@RequestBody GiftQuart giftQuart) {
        return R.ok(giftQuartService.updateById(giftQuart));
    }

    /**
     * 通过id删除一条记录
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "删除赠品定时任务触发表", notes = "参数： id")
    @SysLog("删除赠品定时任务触发表")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('oc_giftquart_del')")
    public R removeById(@PathVariable Long id) {
        return R.ok(giftQuartService.removeById(id));
    }

    /**
     * 修改赠品状态
     *
     * @author: wangjf
     * @createTime: 2019/10/12 15:29
     * @param id
     * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
     */
    @Inner
    @PostMapping("/callback/{id}")
    public R<Boolean> giftCallback(@PathVariable Long id) {
        return R.ok(giftQuartService.giftUpdateState(id));
    }
}
