package com.junfeng.platform.oc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.oc.entity.Coupon;

/**
 * 优惠券
 *
 * @author wangjf
 * @date 2019-09-23 15:22:22
 */
public interface CouponService extends IService<Coupon> {

    /**
     * 优惠券简单分页查询
     *
     * @param coupon
     *            优惠券
     * @return
     */
    IPage<Coupon> getCouponPage(Page<Coupon> page, Coupon coupon);

    /**
     * 保存优惠券
     *
     * @param coupon
     * @return
     * @author:Wangjf
     * @createTime:2019年9月25日 下午4:07:14
     */
    Boolean saveCoupon(Coupon coupon);

    /**
     * 修改状态
     * @author: wangjf
     * @createTime: 2019/10/15 16:21
     * @param id
     * @param state
     * @return java.lang.Boolean
     */
    Boolean updateState(Long id,Integer state);

}
