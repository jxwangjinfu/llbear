package com.junfeng.platform.oc.entity;

import lombok.Data;

/**
 * 折扣券JSON格式
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/8 17:15
 * @projectName pig
 */
@Data
public class JsonCouponDiscount {
    // 折扣率
    private Integer discountRate;
    //最大折扣优惠，单位：分
    private Integer maxDiscount;

}
