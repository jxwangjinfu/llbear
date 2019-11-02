package com.junfeng.platform.oc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.oc.entity.CouponConfig;
import com.junfeng.platform.oc.service.CouponConfigService;
import com.junfeng.platform.oc.mapper.CouponConfigMapper;
import org.springframework.stereotype.Service;

/**
 * 优惠券使用配置表
 *
 * @author wangjf
 * @date 2019-10-09 10:53:35
 */
@Service
public class CouponConfigServiceImpl extends ServiceImpl<CouponConfigMapper, CouponConfig>
        implements CouponConfigService {

    /**
     * 优惠券使用配置表简单分页查询
     *
     * @param couponConfig
     *            优惠券使用配置表
     * @return
     */
    @Override
    public IPage<CouponConfig> getCouponConfigPage(Page<CouponConfig> page, CouponConfig couponConfig) {
        return baseMapper.getCouponConfigPage(page, couponConfig);
    }

}
