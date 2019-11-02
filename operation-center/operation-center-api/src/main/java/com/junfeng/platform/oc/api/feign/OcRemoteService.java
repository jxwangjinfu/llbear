package com.junfeng.platform.oc.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.junfeng.platform.oc.api.feign.factory.OcRemoteServiceFallbackFactory;
import com.junfeng.platform.oc.api.result.GroupBuyResult;
import com.junfeng.platform.oc.api.result.OrderResult;
import com.junfeng.platform.oc.api.result.PointsResult;
import com.junfeng.platform.oc.api.vo.OrderVO;
import com.junfeng.platform.oc.api.vo.PointsVO;
import com.junfeng.platform.oc.api.vo.SignInVO;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.constant.ServiceNameConstants;
import com.pig4cloud.pig.common.core.util.R;

/**
 * @projectName:pig
 * @author:Wangjf
 * @date:2019/10/14 17:22
 * @version 1.0
 */
@FeignClient(contextId = "ocRemoteService", value = ServiceNameConstants.OPERACTION_CENTER_SERVICE, fallbackFactory = OcRemoteServiceFallbackFactory.class)
public interface OcRemoteService {

    /**
     * 订单优惠预览
     *
     * @author: wangjf
     * @createTime: 2019/10/14 17:34
     * @param vo
     * @return com.junfeng.platform.oc.api.result.OrderResult
     */
    @PostMapping("/order/preview")
    R<OrderResult> orderPreview(@RequestBody OrderVO vo, @RequestHeader(SecurityConstants.FROM) String from);

    /**
     * 订单优惠计算
     *
     * @author: wangjf
     * @createTime: 2019/10/17 14:20
     * @param vo
     * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.oc.api.result.OrderResult>
     */
    @PutMapping("/order/calculate")
    R<OrderResult> orderCalculate(@RequestBody OrderVO vo, @RequestHeader(SecurityConstants.FROM) String from);

    /**
     * 订单锁定优惠
     *
     * @author: wangjf
     * @createTime: 2019/10/18 10:55
     * @param vo
     * @param from
     * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.oc.api.result.OrderResult>
     */
    @PutMapping("/order/lock")
    R<OrderResult> orderLock(@RequestBody OrderVO vo, @RequestHeader(SecurityConstants.FROM) String from);

    /**
     * 订单销券和销红包
     *
     * @author: wangjf
     * @createTime: 2019/10/18 11:11
     * @param memberId
     * @param orderNo
     * @param from
     * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
     */
    @PutMapping("/order/complete/{memberId}/{orderNo}")
    R<Boolean> orderComplete(@PathVariable("memberId") Long memberId, @PathVariable("orderNo") String orderNo,
            @RequestHeader(SecurityConstants.FROM) String from);

    /**
     * 订单取消，返还优惠券和红包
     *
     * @author: wangjf
     * @createTime: 2019/10/18 11:11
     * @param memberId
     * @param orderNo
     * @param from
     * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
     */
    @PutMapping("/order/cancel/{memberId}/{orderNo}")
    R<Boolean> orderCancel(@PathVariable("memberId") Long memberId, @PathVariable("orderNo") String orderNo,
            @RequestHeader(SecurityConstants.FROM) String from);

    /**
     * 积分计算
     *
     * @author: wangjf
     * @createTime: 2019/10/14 17:34
     * @param vo
     * @return com.junfeng.platform.oc.api.result.PointResult
     */
    @GetMapping("/pointsconfig/getPointResult")
    R<PointsResult> getPointResult(@RequestBody PointsVO vo, @RequestHeader(SecurityConstants.FROM) String from);

    /**
     * 会员积分计算接口
     *
     * @author: wangjf
     * @createTime: 2019/10/18 14:07
     * @param vo
     * @param from
     * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
     */
    @PostMapping("/points/calculate")
    R<Boolean> memberPointCalculate(@RequestBody PointsVO vo, @RequestHeader(SecurityConstants.FROM) String from);

    /**
     * 买赠
     *
     * @author: wangjf
     * @createTime: 2019/10/18 14:23
     * @param orderVO
     * @param from
     * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
     */
    @PostMapping("/order/buy/gift")
    R<Boolean> orderBuyGift(@RequestBody OrderVO orderVO, @RequestHeader(SecurityConstants.FROM) String from);

    /**
     * 根据SKU_ID获取团购活动
     *
     * @author: wangjf
     * @createTime: 2019/10/22 14:29
     * @param id
     * @param from
     * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.oc.api.result.GroupBuyResult>
     */
    @GetMapping("/groupbuy/sku/{id}")
    R<GroupBuyResult> groupbuySkuId(@PathVariable("id") Long id, @RequestHeader(SecurityConstants.FROM) String from);

    /**
     * 会员登录获取积分
     *
     * @author: wangjf
     * @createTime: 2019/10/23 14:06
     * @param signInVO
     * @param from
     * @return com.pig4cloud.pig.common.core.util.R<java.lang.Integer>
     */
    @PostMapping("/points/singin")
    R<Integer> memberSignInPoint(@RequestBody SignInVO signInVO, @RequestHeader(SecurityConstants.FROM) String from);
}
