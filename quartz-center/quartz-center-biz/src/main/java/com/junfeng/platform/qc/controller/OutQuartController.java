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
import com.junfeng.platform.qc.api.vo.OutQuartVO;
import com.junfeng.platform.qc.entity.OutQuart;
import com.junfeng.platform.qc.service.OutQuartService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.Inner;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * 外部定时任务
 *
 * @author wangjf
 * @date 2019-09-25 10:49:16
 */
@Api(tags = {"外部定时任务"})
@RestController
@AllArgsConstructor
@RequestMapping("/outquart")
public class OutQuartController {

    private final OutQuartService outQuartService;

    /**
     * 简单分页查询
     *
     * @param page
     *            分页对象
     * @param outQuart
     *            外部定时任务
     * @return
     */
    @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 外部定时任务")
    @GetMapping("/page")
    public R<IPage<OutQuart>> getOutQuartPage(Page<OutQuart> page, OutQuart outQuart) {
        return R.ok(outQuartService.getOutQuartPage(page, outQuart));
    }

    /**
     * 通过id查询单条记录
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
    @GetMapping("/{id}")
    public R<OutQuart> getById(@PathVariable("id") Long id) {
        return R.ok(outQuartService.getById(id));
    }

    /**
     * 新增记录
     *
     * @param outQuart
     * @return R
     */
    @ApiOperation(value = "新增外部定时任务", notes = "参数： outQuart")
    @SysLog("新增外部定时任务")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('qc_outquart_add')")
    public R<Boolean> save(@RequestBody OutQuart outQuart) {
        return R.ok(outQuartService.save(outQuart));
    }

    /**
     * 保存外部定时任务
     *
     * @param outQuartVO
     * @return
     * @author:Wangjf
     * @createTime:2019年9月25日 上午11:27:14
     */
    @Inner
    @PostMapping("/out")
    public R<Boolean> saveOut(@RequestBody OutQuartVO outQuartVO) {
        return R.ok(outQuartService.saveOut(outQuartVO));
    }

    /**
     * 修改记录
     *
     * @param outQuart
     * @return R
     */
    @ApiOperation(value = "修改外部定时任务", notes = "参数： outQuart")
    @SysLog("修改外部定时任务")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('qc_outquart_edit')")
    public R<Boolean> update(@RequestBody OutQuart outQuart) {
        return R.ok(outQuartService.updateById(outQuart));
    }

    /**
     * 修改记录
     *
     * @param outQuartVO
     * @return R
     */
    @Inner
    @PutMapping("/out")
    public R<Boolean> updateOut(@RequestBody OutQuartVO outQuartVO) {
        return R.ok(outQuartService.updateOut(outQuartVO));
    }

    /**
     * 通过id删除一条记录
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "删除外部定时任务", notes = "参数： id")
    @SysLog("删除外部定时任务")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('qc_outquart_del')")
    public R<Boolean> removeById(@PathVariable Long id) {
        return R.ok(outQuartService.removeById(id));
    }

    /**
     * 通过jobName和group删除
     * @param jobName
     * @param jobGroup
     * @return
     * @author:Wangjf
     * @createTime:2019年9月25日 下午2:51:38
     */
    @Inner
    @DeleteMapping("/out/{jobName}/{jobGroup}")
    public R<Boolean> removeOut(@PathVariable String jobName, @PathVariable String jobGroup) {
        return R.ok(outQuartService.deleteOut(jobName, jobGroup));
    }
}
