package com.junfeng.platform.oc.api.result;

import lombok.Data;

/**
 * 描述
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/14 17:33
 * @projectName pig
 */
@Data
public class PointsResult {

    private String clientId;
    /**
     * 操作类型,0-消耗,1-获取
     */
    private Integer operationType;
    /**
     * 规则类型,1-登录,2-购物,3...
     */
    private Integer pointsType;
    /**
     * 规则名称
     */
    private String pointsName;
    /**
     * 积分值
     */
    private Integer points;

}
