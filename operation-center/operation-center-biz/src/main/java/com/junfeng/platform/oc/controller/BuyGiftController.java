package com.junfeng.platform.oc.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.oc.api.vo.BuyGiftVO;
import com.junfeng.platform.oc.entity.BuyGift;
import com.junfeng.platform.oc.service.BuyGiftService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * 买赠表
 *
 * @author wangjf
 * @date 2019-10-14 15:09:35
 */
@Api(tags = {"买赠表"})
@RestController
@AllArgsConstructor
@RequestMapping("/buygift")
public class BuyGiftController {

    private final BuyGiftService buyGiftService;

    /**
     * 简单分页查询
     *
     * @param page
     *            分页对象
     * @param buyGift
     *            买赠表
     * @return
     */
    @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 买赠表")
    @GetMapping("/page")
    public R<IPage<BuyGift>> getBuyGiftPage(Page<BuyGift> page, BuyGift buyGift) {
        return R.ok(buyGiftService.getBuyGiftPage(page, buyGift));
    }

    /**
     * 通过id查询单条记录
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
    @GetMapping("/{id}")
    public R<BuyGift> getById(@PathVariable("id") Long id) {
        return R.ok(buyGiftService.getById(id));
    }

    /**
     * 新增记录
     *
     * @param buyGift
     * @return R
     */
    @ApiOperation(value = "新增买赠表", notes = "参数： buyGift")
    @SysLog("新增买赠表")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('oc_buygift_add')")
    public R save(@RequestBody BuyGift buyGift) {
        return R.ok(buyGiftService.saveBuyGift(buyGift));
    }

    /**
     * 修改记录
     *
     * @param buyGift
     * @return R
     */
    @ApiOperation(value = "修改买赠表", notes = "参数： buyGift")
    @SysLog("修改买赠表")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('oc_buygift_edit')")
    public R update(@RequestBody BuyGift buyGift) {
        return R.ok(buyGiftService.updateById(buyGift));
    }

    /**
     * 通过id删除一条记录
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "删除买赠表", notes = "参数： id")
    @SysLog("删除买赠表")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('oc_buygift_del')")
    public R removeById(@PathVariable Long id) {
        return R.ok(buyGiftService.removeById(id));
    }

    /**
     * 新增买赠记录
     *
     * @author: wangjf
     * @createTime: 2019/10/25 14:31
     * @param buyGfitVO
     * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
     */
    @ApiOperation(value = "新增买赠记录", notes = "参数： BuyGfitVO")
    @SysLog("新增买赠记录")
    @PostMapping("/save/buygfitvo")
    public R<Boolean> saveBuyGiftVO(@RequestBody BuyGiftVO buyGfitVO) {
        return R.ok(buyGiftService.saveBuyGiftVO(buyGfitVO));
    }

}
