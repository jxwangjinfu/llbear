package com.junfeng.platform.oc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.oc.entity.Coupon;
import com.junfeng.platform.oc.entity.CouponMember;
import com.junfeng.platform.oc.entity.CouponQuart;
import com.junfeng.platform.oc.mapper.CouponMapper;
import com.junfeng.platform.oc.mapper.CouponMemberMapper;
import com.junfeng.platform.oc.mapper.CouponQuartMapper;
import com.junfeng.platform.oc.service.CouponQuartService;

import lombok.SneakyThrows;

/**
 * 优惠券定时任务触发表
 *
 * @author wangjf
 * @date 2019-09-25 10:47:52
 */
@Service
public class CouponQuartServiceImpl extends ServiceImpl<CouponQuartMapper, CouponQuart> implements CouponQuartService {

    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private CouponMemberMapper couponMemberMapper;

    /**
     * 优惠券定时任务触发表简单分页查询
     *
     * @param couponQuart
     *            优惠券定时任务触发表
     * @return
     */
    @Override
    public IPage<CouponQuart> getCouponQuartPage(Page<CouponQuart> page, CouponQuart couponQuart) {
        return baseMapper.getCouponQuartPage(page, couponQuart);
    }

    /**
     * 修改优惠券状态
     *
     * @param id
     * @return
     * @author:Wangjf
     * @createTime:2019年9月25日 下午4:53:16
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean couponUpdateState(Long id) {

        CouponQuart couponQuart = getById(id);
        if (couponQuart == null) {
            return false;
        }
        Coupon coupon = new Coupon();
        coupon.setId(couponQuart.getOcCouponId());
        coupon.setState(couponQuart.getOcCouponState());
        couponMapper.updateById(coupon);
        CouponQuart updateObj = new CouponQuart();
        updateObj.setId(id);
        // 1代表结束
        updateObj.setState("1");
        baseMapper.updateById(updateObj);

        CouponMember couponMember = new CouponMember();
        couponMember.setState(couponQuart.getOcCouponState());

        couponMemberMapper.update(couponMember,
                Wrappers.<CouponMember> query().lambda().eq(CouponMember::getOcCouponId, coupon.getId()));

        return Boolean.TRUE;
    }

}
