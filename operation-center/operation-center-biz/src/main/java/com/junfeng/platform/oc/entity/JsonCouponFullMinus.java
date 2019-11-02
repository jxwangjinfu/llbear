package com.junfeng.platform.oc.entity;

import lombok.Data;

/**
 * 满减优惠券JSON转换
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/8 17:09
 * @projectName pig
 */
@Data
public class JsonCouponFullMinus {
    // 减免金额 单位：分
    private Integer minusAmount;
}
