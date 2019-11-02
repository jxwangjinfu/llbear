package com.junfeng.platform.oc.api.vo;

import lombok.Data;

/**
 * 计算积分VO
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/21 13:47
 * @projectName pig
 */
@Data
public class CalculatePointsVO {

    private String clientId;
    private Long memberId;
    //配置ID
    private Long configId;
}
