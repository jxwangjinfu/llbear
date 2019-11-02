package com.junfeng.platform.oc.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.oc.entity.QuartzLog;
import com.junfeng.platform.oc.service.QuartzLogService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.Inner;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * 定时任务触发日志表
 *
 * @author wangjf
 * @date 2019-10-15 14:55:08
 */
@Api(tags = {"定时任务触发日志表"})
@RestController
@AllArgsConstructor
@RequestMapping("/quartzlog")
public class QuartzLogController {

    private final QuartzLogService quartzLogService;

    /**
     * 简单分页查询
     *
     * @param page
     *            分页对象
     * @param quartzLog
     *            定时任务触发日志表
     * @return
     */
    @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 定时任务触发日志表")
    @GetMapping("/page")
    public R<IPage<QuartzLog>> getQuartzLogPage(Page<QuartzLog> page, QuartzLog quartzLog) {
        return R.ok(quartzLogService.getQuartzLogPage(page, quartzLog));
    }

    /**
     * 通过id查询单条记录
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
    @GetMapping("/{id}")
    public R<QuartzLog> getById(@PathVariable("id") Long id) {
        return R.ok(quartzLogService.getById(id));
    }

    /**
     * 新增记录
     *
     * @param quartzLog
     * @return R
     */
    @ApiOperation(value = "新增定时任务触发日志表", notes = "参数： quartzLog")
    @SysLog("新增定时任务触发日志表")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('oc_quartzlog_add')")
    public R save(@RequestBody QuartzLog quartzLog) {
        return R.ok(quartzLogService.save(quartzLog));
    }

    /**
     * 修改记录
     *
     * @param quartzLog
     * @return R
     */
    @ApiOperation(value = "修改定时任务触发日志表", notes = "参数： quartzLog")
    @SysLog("修改定时任务触发日志表")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('oc_quartzlog_edit')")
    public R update(@RequestBody QuartzLog quartzLog) {
        return R.ok(quartzLogService.updateById(quartzLog));
    }

    /**
     * 通过id删除一条记录
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "删除定时任务触发日志表", notes = "参数： id")
    @SysLog("删除定时任务触发日志表")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('oc_quartzlog_del')")
    public R removeById(@PathVariable Long id) {
        return R.ok(quartzLogService.removeById(id));
    }

    /**
     * 外部回调
     * @author: wangjf
     * @createTime: 2019/10/15 15:19
     * @param id
     * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
     */
	@Inner
	@PostMapping("/callback/{id}")
	public R<Boolean> couponCallback(@PathVariable Long id) {
		return R.ok(quartzLogService.updateState(id));
	}

}
