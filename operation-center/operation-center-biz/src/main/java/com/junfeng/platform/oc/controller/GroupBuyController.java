package com.junfeng.platform.oc.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.oc.api.result.GroupBuyResult;
import com.junfeng.platform.oc.entity.GroupBuy;
import com.junfeng.platform.oc.service.GroupBuyService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.Inner;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * 团购活动表
 *
 * @author wangjf
 * @date 2019-10-22 14:05:08
 */
@Api(tags = {"团购活动表"})
@RestController
@AllArgsConstructor
@RequestMapping("/groupbuy")
public class GroupBuyController {

    private final GroupBuyService groupBuyService;

    /**
     * 简单分页查询
     *
     * @param page
     *            分页对象
     * @param groupBuy
     *            团购活动表
     * @return
     */
    @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 团购活动表")
    @GetMapping("/page")
    public R<IPage<GroupBuy>> getGroupBuyPage(Page<GroupBuy> page, GroupBuy groupBuy) {
        return R.ok(groupBuyService.getGroupBuyPage(page, groupBuy));
    }

    /**
     * 通过SKU_ID获取拼团设置
     *
     * @author: wangjf
     * @createTime: 2019/10/22 14:17
     * @param id
     * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.oc.entity.GroupBuyResult>
     */
    @Inner
    @ApiOperation(value = "通过sku_id查询单条记录", notes = "参数： sku_id")
    @GetMapping("/sku/{id}")
    public R<GroupBuyResult> getBySkuId(@PathVariable("id") Long id) {
        return R.ok(groupBuyService.getBySkuId(id));
    }

    /**
     * 通过id查询单条记录
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
    @GetMapping("/{id}")
    public R<GroupBuy> getById(@PathVariable("id") Long id) {
        return R.ok(groupBuyService.getById(id));
    }

    /**
     * 新增记录
     *
     * @param groupBuy
     * @return R
     */
    @ApiOperation(value = "新增团购活动表", notes = "参数： groupBuy")
    @SysLog("新增团购活动表")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('oc_groupbuy_add')")
    public R save(@RequestBody GroupBuy groupBuy) {
        return R.ok(groupBuyService.saveGroupBuy(groupBuy));
    }

    /**
     * 修改记录
     *
     * @param groupBuy
     * @return R
     */
    @ApiOperation(value = "修改团购活动表", notes = "参数： groupBuy")
    @SysLog("修改团购活动表")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('oc_groupbuy_edit')")
    public R update(@RequestBody GroupBuy groupBuy) {
        return R.ok(groupBuyService.updateById(groupBuy));
    }

    /**
     * 通过id删除一条记录
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "删除团购活动表", notes = "参数： id")
    @SysLog("删除团购活动表")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('oc_groupbuy_del')")
    public R removeById(@PathVariable Long id) {
        return R.ok(groupBuyService.removeById(id));
    }

}
