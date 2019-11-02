package com.junfeng.platform.oc.api.vo;

import lombok.Data;

/**
 * 红包MQ消息VO
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/9/29 17:01
 * @projectName pig
 */
@Data
public class RedEnvelopeGenerateMessageVO {

    // 开始值
    private Integer startNo;
    // 结束值
    private Integer endNo;
    // 红包活动
    private Long ocRedEnvelopeId;
    // 创建人
    private String createBy;
    // 红包金额
    private Integer money;

}
