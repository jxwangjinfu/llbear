package com.junfeng.platform.oc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.junfeng.platform.oc.entity.GiftMember;
import com.junfeng.platform.oc.service.GiftMemberService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 赠品会员表
 *
 * @author wangjf
 * @date 2019-10-12 14:09:37
 */
@Api(tags = {"赠品会员表"})
@RestController
@AllArgsConstructor
@RequestMapping("/giftmember")
public class GiftMemberController {

    private final GiftMemberService giftMemberService;

    /**
     * 简单分页查询
     *
     * @param page
     *            分页对象
     * @param giftMember
     *            赠品会员表
     * @return
     */
    @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 赠品会员表")
    @GetMapping("/page")
    public R<IPage<GiftMember>> getGiftMemberPage(Page<GiftMember> page, GiftMember giftMember) {
        return R.ok(giftMemberService.getGiftMemberPage(page, giftMember));
    }

    /**
     * 通过id查询单条记录
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
    @GetMapping("/{id}")
    public R<GiftMember> getById(@PathVariable("id") Long id) {
        return R.ok(giftMemberService.getById(id));
    }

    /**
     * 新增记录
     *
     * @param giftMember
     * @return R
     */
    @ApiOperation(value = "新增赠品会员表", notes = "参数： giftMember")
    @SysLog("新增赠品会员表")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('oc_giftmember_add')")
    public R save(@RequestBody GiftMember giftMember) {
        return R.ok(giftMemberService.save(giftMember));
    }

    /**
     * 修改记录
     *
     * @param giftMember
     * @return R
     */
    @ApiOperation(value = "修改赠品会员表", notes = "参数： giftMember")
    @SysLog("修改赠品会员表")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('oc_giftmember_edit')")
    public R update(@RequestBody GiftMember giftMember) {
        return R.ok(giftMemberService.updateById(giftMember));
    }

    /**
     * 通过id删除一条记录
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "删除赠品会员表", notes = "参数： id")
    @SysLog("删除赠品会员表")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('oc_giftmember_del')")
    public R removeById(@PathVariable Long id) {
        return R.ok(giftMemberService.removeById(id));
    }

    /**
     * 会员领取赠品
     * @author: wangjf
     * @createTime: 2019/10/12 15:26
     * @param memberId
     * @param giftId
     * @return com.pig4cloud.pig.common.core.util.R<java.lang.String>
     */
    @ApiOperation(value = "会员领取赠品", notes = "参数： memberId和giftId")
    @SysLog("会员领取赠品")
    @PutMapping("/{memberId}/{giftId}")
    @PreAuthorize("@pms.hasPermission('oc_giftmember_get')")
    public R<String> updateCoupon(@PathVariable("memberId") Long memberId, @PathVariable("giftId") Long giftId) {
        return R.ok(giftMemberService.updateGift(memberId, giftId));
    }

}
