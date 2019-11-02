package com.junfeng.platform.oc.api.result;

import lombok.Data;

/**
 * 优惠券结果
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/16 17:22
 * @projectName pig
 */
@Data
public class CouponResult {

	private Long id;
	private Long mcMemberId;
	private Long ocCouponId;
	private String couponName;
	private String clientId;
	private Integer orderUsePre;
	private Integer typeCode;
	private Integer money;
	//折扣 单位：分
	private Integer discount;

}
