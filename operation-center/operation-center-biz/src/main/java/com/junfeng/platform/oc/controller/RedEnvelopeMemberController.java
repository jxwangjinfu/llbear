package com.junfeng.platform.oc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.junfeng.platform.oc.entity.RedEnvelopeMember;
import com.junfeng.platform.oc.service.RedEnvelopeMemberService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 红包会员表
 *
 * @author wangjf
 * @date 2019-10-09 16:37:15
 */
@Api(tags = {"红包会员表"})
@RestController
@AllArgsConstructor
@RequestMapping("/redenvelopemember")
public class RedEnvelopeMemberController {

    private final RedEnvelopeMemberService redEnvelopeMemberService;

    /**
     * 简单分页查询
     *
     * @param page
     *            分页对象
     * @param redEnvelopeMember
     *            红包会员表
     * @return
     */
    @ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 红包会员表")
    @GetMapping("/page")
    public R<IPage<RedEnvelopeMember>> getRedEnvelopeMemberPage(Page<RedEnvelopeMember> page,
            RedEnvelopeMember redEnvelopeMember) {
        return R.ok(redEnvelopeMemberService.getRedEnvelopeMemberPage(page, redEnvelopeMember));
    }

    /**
     * 通过id查询单条记录
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
    @GetMapping("/{id}")
    public R<RedEnvelopeMember> getById(@PathVariable("id") Long id) {
        return R.ok(redEnvelopeMemberService.getById(id));
    }

    /**
     * 新增记录
     *
     * @param redEnvelopeMember
     * @return R
     */
    @ApiOperation(value = "新增红包会员表", notes = "参数： redEnvelopeMember")
    @SysLog("新增红包会员表")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('oc_redenvelopemember_add')")
    public R save(@RequestBody RedEnvelopeMember redEnvelopeMember) {
        return R.ok(redEnvelopeMemberService.save(redEnvelopeMember));
    }

    /**
     * 修改记录
     *
     * @param redEnvelopeMember
     * @return R
     */
    @ApiOperation(value = "修改红包会员表", notes = "参数： redEnvelopeMember")
    @SysLog("修改红包会员表")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('oc_redenvelopemember_edit')")
    public R update(@RequestBody RedEnvelopeMember redEnvelopeMember) {
        return R.ok(redEnvelopeMemberService.updateById(redEnvelopeMember));
    }

    /**
     * 通过id删除一条记录
     *
     * @param id
     * @return R
     */
    @ApiOperation(value = "删除红包会员表", notes = "参数： id")
    @SysLog("删除红包会员表")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('oc_redenvelopemember_del')")
    public R removeById(@PathVariable Long id) {
        return R.ok(redEnvelopeMemberService.removeById(id));
    }

    /**
     * 会员领取红包
     *
     * @author: wangjf
     * @createTime: 2019/10/10 13:56
     * @param memberId
     * @param redEnvelopeId
     * @return com.pig4cloud.pig.common.core.util.R<java.lang.String>
     */
    @ApiOperation(value = "会员领取红包", notes = "参数： memberId和redEnvelopeId")
    @SysLog("会员领取红包")
    @PutMapping("/{memberId}/{redEnvelopeId}")
    @PreAuthorize("@pms.hasPermission('oc_redenvelopemember_get')")
    public R<String> updateCoupon(@PathVariable("memberId") Long memberId,
            @PathVariable("redEnvelopeId") Long redEnvelopeId) {
        return R.ok(redEnvelopeMemberService.updateRedEnvelope(memberId, redEnvelopeId));
    }

}
