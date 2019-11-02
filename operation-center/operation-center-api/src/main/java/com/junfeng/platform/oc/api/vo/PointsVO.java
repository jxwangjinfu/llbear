package com.junfeng.platform.oc.api.vo;

import lombok.Data;

/**
 * 积分实体
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/14 17:16
 * @projectName pig
 */
@Data
public class PointsVO {

	private Long memberId;
	private Integer pointsType;
	private String clientId;
}
