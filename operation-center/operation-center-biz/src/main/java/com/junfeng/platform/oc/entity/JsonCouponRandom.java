package com.junfeng.platform.oc.entity;

import lombok.Data;

/**
 * 随机券JSON格式
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/8 17:18
 * @projectName pig
 */
@Data
public class JsonCouponRandom {
    // 随机开始值
    private Integer start;
    // 随机结束值
    private Integer end;

}
