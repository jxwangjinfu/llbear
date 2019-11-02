package com.junfeng.platform.oc.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.oc.entity.RedEnvelopeQuart;
import com.junfeng.platform.oc.service.RedEnvelopeQuartService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.Inner;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * 红包定时任务触发表
 *
 * @author wangjf
 * @date 2019-10-09 14:24:10
 */
@Api(tags = {"红包定时任务触发表"})
@RestController
@AllArgsConstructor
@RequestMapping("/redenvelopequart")
public class RedEnvelopeQuartController {

    private final RedEnvelopeQuartService redEnvelopeQuartService;

    /**
     * 简单分页查询
     *
     * @param page
     *            分页对象
     * @param redEnvelopeQuart
     *            红包定时任务触发表
     * @return
     */
    @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 红包定时任务触发表")
    @GetMapping("/page")
    public R<IPage<RedEnvelopeQuart>> getRedEnvelopeQuartPage(Page<RedEnvelopeQuart> page,
            RedEnvelopeQuart redEnvelopeQuart) {
        return R.ok(redEnvelopeQuartService.getRedEnvelopeQuartPage(page, redEnvelopeQuart));
    }

    /**
     * 通过id查询单条记录
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
    @GetMapping("/{id}")
    public R<RedEnvelopeQuart> getById(@PathVariable("id") Long id) {
        return R.ok(redEnvelopeQuartService.getById(id));
    }

    /**
     * 新增记录
     *
     * @param redEnvelopeQuart
     * @return R
     */
    @ApiOperation(value = "新增红包定时任务触发表", notes = "参数： redEnvelopeQuart")
    @SysLog("新增红包定时任务触发表")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('oc_redenvelopequart_add')")
    public R save(@RequestBody RedEnvelopeQuart redEnvelopeQuart) {
        return R.ok(redEnvelopeQuartService.save(redEnvelopeQuart));
    }

    /**
     * 修改记录
     *
     * @param redEnvelopeQuart
     * @return R
     */
    @ApiOperation(value = "修改红包定时任务触发表", notes = "参数： redEnvelopeQuart")
    @SysLog("修改红包定时任务触发表")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('oc_redenvelopequart_edit')")
    public R update(@RequestBody RedEnvelopeQuart redEnvelopeQuart) {
        return R.ok(redEnvelopeQuartService.updateById(redEnvelopeQuart));
    }

    /**
     * 通过id删除一条记录
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "删除红包定时任务触发表", notes = "参数： id")
    @SysLog("删除红包定时任务触发表")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('oc_redenvelopequart_del')")
    public R removeById(@PathVariable Long id) {
        return R.ok(redEnvelopeQuartService.removeById(id));
    }

    /**
     * 红包修改状态
     *
     * @param id
     * @return
     * @author:Wangjf
     * @createTime:2019年9月25日 下午4:50:12
     */
    @Inner
    @PostMapping("/callback/{id}")
    public R<Boolean> couponCallback(@PathVariable Long id) {
        return R.ok(redEnvelopeQuartService.redEnvelopeUpdateState(id));
    }

}
