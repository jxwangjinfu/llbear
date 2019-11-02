package com.junfeng.platform.oc.entity;

import lombok.Data;

/**
 * 会员优惠券信息DTO
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/16 15:08
 * @projectName pig
 */
@Data
public class CouponMemberDTO {

	private Long id;
	private Long mcMemberId;
	private Long ocCouponId;
	private String couponName;
	private String clientId;
	private String discountContext;
	private Integer orderUsePre;
	private Integer typeCode;
	private Integer money;
	private JsonCouponDiscount jsonCouponDiscount;
	private JsonCouponFullMinus jsonCouponFullMinus;
	private JsonCouponRandom jsonCouponRandom;

}
