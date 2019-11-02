package com.junfeng.platform.oc.api.result;

import lombok.Data;

import java.util.List;

/**
 * 订单输出
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/14 17:32
 * @projectName pig
 */
@Data
public class OrderResult {
    // 会员ID
    private Long memberId;
    // 订单编号
    private String orderNo;
    // 总价
    private Long totalPrice;
    // 商品所属的店铺id
    private Long shopId;
    // 调用方id
    private String clientId;
    // 整体优惠金额 单位分
    private Integer totalDiscount;
    //优惠券抵扣金额
    private Integer totalCoupon;
    //红包抵扣金额
    private Integer totalRedEnvelope;
    // 购物商品列表
    private List<SkuResult> skuList;
    // 优惠使用情况
    private List<CouponResult> listCoupon;
    // 红包使用情况
    private List<RedEnvelopeResult> listRedEnvelope;

}
