/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */
package com.junfeng.platform.qc.controller;

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
import com.junfeng.platform.qc.entity.QuartzLog;
import com.junfeng.platform.qc.service.QuartzLogService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;

import lombok.AllArgsConstructor;

/**
 * quartz日志表
 *
 * @author wangjf
 * @date 2019-09-18 09:09:37
 */
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
     *            quartz日志表
     * @return
     */
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
    @SysLog("新增quartz日志表")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('qc_quartzlog_add')")
    public R save(@RequestBody QuartzLog quartzLog) {
        return R.ok(quartzLogService.save(quartzLog));
    }

    /**
     * 修改记录
     * 
     * @param quartzLog
     * @return R
     */
    @SysLog("修改quartz日志表")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('qc_quartzlog_edit')")
    public R update(@RequestBody QuartzLog quartzLog) {
        return R.ok(quartzLogService.updateById(quartzLog));
    }

    /**
     * 通过id删除一条记录
     * 
     * @param id
     * @return R
     */
    @SysLog("删除quartz日志表")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('qc_quartzlog_del')")
    public R removeById(@PathVariable Long id) {
        return R.ok(quartzLogService.removeById(id));
    }

}
