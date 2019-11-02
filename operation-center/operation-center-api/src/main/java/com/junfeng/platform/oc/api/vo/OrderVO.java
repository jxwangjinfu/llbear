package com.junfeng.platform.oc.api.vo;

import lombok.Data;

import java.util.List;

/**
 * 订单实体
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/14 17:15
 * @projectName pig
 */
@Data
public class OrderVO {
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
    // 购物商品列表
    private List<SkuVO> skuList;
}
