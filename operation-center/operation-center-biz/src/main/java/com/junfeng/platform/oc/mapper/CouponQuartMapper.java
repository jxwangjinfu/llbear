package com.junfeng.platform.oc.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.oc.entity.CouponQuart;

/**
 * 优惠券定时任务触发表
 *
 * @author wangjf
 * @date 2019-09-25 10:47:52
 */
public interface CouponQuartMapper extends BaseMapper<CouponQuart> {
    /**
     * 优惠券定时任务触发表简单分页查询
     * 
     * @param couponQuart
     *            优惠券定时任务触发表
     * @return
     */
    IPage<CouponQuart> getCouponQuartPage(Page page, @Param("couponQuart") CouponQuart couponQuart);

}
