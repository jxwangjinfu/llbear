package com.junfeng.platform.oc.api.vo;

import lombok.Data;

/**
 * 礼品生成
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/9/29 17:01
 * @projectName pig
 */
@Data
public class GiftGenerateMessageVO {

    // 开始值
    private Integer startNo;
    // 结束值
    private Integer endNo;
    // 优惠券活动
    private Long ocGiftId;
    // 创建人
    private String createBy;

}
