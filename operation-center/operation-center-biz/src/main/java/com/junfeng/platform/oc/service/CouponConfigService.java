package com.junfeng.platform.oc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.oc.entity.CouponConfig;

/**
 * 优惠券使用配置表
 *
 * @author wangjf
 * @date 2019-10-09 10:53:35
 */
public interface CouponConfigService extends IService<CouponConfig> {

    /**
     * 优惠券使用配置表简单分页查询
     *
     * @param couponConfig
     *            优惠券使用配置表
     * @return
     */
    IPage<CouponConfig> getCouponConfigPage(Page<CouponConfig> page, CouponConfig couponConfig);

}
