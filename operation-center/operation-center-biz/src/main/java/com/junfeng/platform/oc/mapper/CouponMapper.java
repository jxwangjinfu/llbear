package com.junfeng.platform.oc.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.oc.entity.Coupon;

/**
 * 优惠券
 *
 * @author wangjf
 * @date 2019-09-23 15:22:22
 */
public interface CouponMapper extends BaseMapper<Coupon> {
    /**
     * 优惠券简单分页查询
     * 
     * @param coupon
     *            优惠券
     * @return
     */
    IPage<Coupon> getCouponPage(Page page, @Param("coupon") Coupon coupon);

}
