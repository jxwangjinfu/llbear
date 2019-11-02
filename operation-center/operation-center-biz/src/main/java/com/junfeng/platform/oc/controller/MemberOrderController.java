package com.junfeng.platform.oc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.junfeng.platform.oc.api.result.OrderResult;
import com.junfeng.platform.oc.api.vo.OrderVO;
import com.junfeng.platform.oc.service.MemberOrderService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.security.annotation.Inner;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会员订单优惠接口类
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/17 14:02
 * @projectName pig
 */
@Api(tags = {"会员订单优惠"})
@RestController
@RequestMapping("/order")
public class MemberOrderController {

    @Autowired
    private MemberOrderService memberOrderService;

    /**
     * 订单预览
     *
     * @author: wangjf
     * @createTime: 2019/10/17 14:14
     * @param orderVO
     * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.oc.api.result.OrderResult>
     */
    @Inner
    @PostMapping("/preview")
    public R<OrderResult> orderPreview(@RequestBody OrderVO orderVO) {

        return R.ok(memberOrderService.orderPreview(orderVO));
    }

    /**
     * 外部预览
     *
     * @author: wangjf
     * @createTime: 2019/10/25 15:34
     * @param orderVO
     * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.oc.api.result.OrderResult>
     */
    @ApiOperation(value = "外部订单优惠预览", notes = "参数： orderVO")
    @PostMapping("/out/preview")
    public R<OrderResult> orderOutPreview(@RequestBody OrderVO orderVO) {

        return R.ok(memberOrderService.orderPreview(orderVO));
    }

    /**
     * 订单优惠计算(销优惠券和红包)
     *
     * @author: wangjf
     * @createTime: 2019/10/17 14:22
     * @param orderVO
     * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.oc.api.result.OrderResult>
     */
    @Inner
    @PutMapping("/calculate")
    public R<OrderResult> orderCalculate(@RequestBody OrderVO orderVO) {

        return R.ok(memberOrderService.orderCalculate(orderVO));
    }

    /**
     * 订单锁定
     *
     * @author: wangjf
     * @createTime: 2019/10/18 11:04
     * @param orderVO
     * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.oc.api.result.OrderResult>
     */
    @Inner
    @PutMapping("/lock")
    public R<OrderResult> orderLock(@RequestBody OrderVO orderVO) {
        return R.ok(memberOrderService.orderLock(orderVO));
    }

    /**
     * 外部订单优惠锁定
     *
     * @author: wangjf
     * @createTime: 2019/10/25 15:35
     * @param orderVO
     * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.oc.api.result.OrderResult>
     */
    @ApiOperation(value = "外部订单优惠锁定", notes = "参数： orderVO")
    @PutMapping("/out/lock")
    public R<OrderResult> orderOutLock(@RequestBody OrderVO orderVO) {
        return R.ok(memberOrderService.orderLock(orderVO));
    }

    /**
     * 订单完成
     *
     * @author: wangjf
     * @createTime: 2019/10/18 11:05
     * @param orderNo
     * @param memberId
     * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
     */
    @Inner
    @PutMapping("/complete/{memberId}/{orderNo}")
    public R<Boolean> orderComplete(@PathVariable("memberId") Long memberId, @PathVariable("orderNo") String orderNo) {
        return R.ok(memberOrderService.orderComplete(memberId, orderNo));
    }

    /**
     * 外部订单完成
     *
     * @author: wangjf
     * @createTime: 2019/10/25 15:36
     * @param memberId
     * @param orderNo
     * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
     */
    @ApiOperation(value = "外部订单完成，并销优惠", notes = "参数： orderNo")
    @PutMapping("/out/complete/{memberId}/{orderNo}")
    public R<Boolean> orderOutComplete(@PathVariable("memberId") Long memberId,
            @PathVariable("orderNo") String orderNo) {
        return R.ok(memberOrderService.orderComplete(memberId, orderNo));
    }

    /**
     * 订单取消
     *
     * @author: wangjf
     * @createTime: 2019/10/18 11:06
     * @param orderNo
     * @param memberId
     * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
     */
    @Inner
    @PutMapping("/cancel/{memberId}/{orderNo}")
    public R<Boolean> orderCancel(@PathVariable("memberId") Long memberId, @PathVariable("orderNo") String orderNo) {
        return R.ok(memberOrderService.orderCancel(memberId, orderNo));
    }

    /**
     * 外部订单取消
     *
     * @author: wangjf
     * @createTime: 2019/10/25 15:37
     * @param memberId
     * @param orderNo
     * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
     */
    @ApiOperation(value = "外部订单取消，并返还优惠", notes = "参数： orderNo")
    @PutMapping("/out/cancel/{memberId}/{orderNo}")
    public R<Boolean> orderOutCancel(@PathVariable("memberId") Long memberId, @PathVariable("orderNo") String orderNo) {
        return R.ok(memberOrderService.orderCancel(memberId, orderNo));
    }

    /**
     * 订单买赠
     *
     * @author: wangjf
     * @createTime: 2019/10/18 14:21
     * @param orderVO
     * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
     */
    @Inner
    @PostMapping("/buy/gift")
    public R<Boolean> orderBuyGift(@RequestBody OrderVO orderVO) {
        return R.ok(memberOrderService.orderBuyGift(orderVO));

    }

    /**
     * 外部订单买赠
     *
     * @author: wangjf
     * @createTime: 2019/10/25 15:37
     * @param orderVO
     * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
     */
    @ApiOperation(value = "外部订单买赠", notes = "参数： orderVO")
    @PostMapping("/out/buy/gift")
    public R<Boolean> orderOutBuyGift(@RequestBody OrderVO orderVO) {
        return R.ok(memberOrderService.orderBuyGift(orderVO));

    }

}
