package com.junfeng.platform.oc.controller;

import com.junfeng.platform.oc.api.vo.PointsVO;
import com.pig4cloud.pig.common.security.annotation.Inner;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.oc.api.result.PointsResult;
import com.junfeng.platform.oc.entity.PointsConfig;
import com.junfeng.platform.oc.service.PointsConfigService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * 运营规则表
 *
 * @author wangjf
 * @date 2019-10-17 17:14:05
 */
@Api(tags = {"运营规则表"})
@RestController
@AllArgsConstructor
@RequestMapping("/pointsconfig")
public class PointsConfigController {

    private final PointsConfigService pointsConfigService;

    /**
     * 简单分页查询
     *
     * @param page
     *            分页对象
     * @param pointsConfig
     *            运营规则表
     * @return
     */
    @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 运营规则表")
    @GetMapping("/page")
    public R<IPage<PointsConfig>> getPointsConfigPage(Page<PointsConfig> page, PointsConfig pointsConfig) {
        return R.ok(pointsConfigService.getPointsConfigPage(page, pointsConfig));
    }

    /**
     * 通过id查询单条记录
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
    @GetMapping("/{id}")
    public R<PointsConfig> getById(@PathVariable("id") Long id) {
        return R.ok(pointsConfigService.getById(id));
    }

    /**
     * 新增记录
     *
     * @param pointsConfig
     * @return R
     */
    @ApiOperation(value = "新增运营规则表", notes = "参数： pointsConfig")
    @SysLog("新增运营规则表")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('oc_pointsconfig_add')")
    public R save(@RequestBody PointsConfig pointsConfig) {
        return R.ok(pointsConfigService.save(pointsConfig));
    }

    /**
     * 修改记录
     *
     * @param pointsConfig
     * @return R
     */
    @ApiOperation(value = "修改运营规则表", notes = "参数： pointsConfig")
    @SysLog("修改运营规则表")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('oc_pointsconfig_edit')")
    public R update(@RequestBody PointsConfig pointsConfig) {
        return R.ok(pointsConfigService.updateById(pointsConfig));
    }

    /**
     * 通过id删除一条记录
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "删除运营规则表", notes = "参数： id")
    @SysLog("删除运营规则表")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('oc_pointsconfig_del')")
    public R removeById(@PathVariable Long id) {
        return R.ok(pointsConfigService.removeById(id));
    }

    @Inner
    @ApiOperation(value = "通过pointVO查询单条记录", notes = "参数： pointVO")
    @GetMapping("/getPointResult")
    public R<PointsResult> getPointsResult(PointsVO pointVO) {
        return R.ok(pointsConfigService.getPointsConfig(pointVO));
    }

}
