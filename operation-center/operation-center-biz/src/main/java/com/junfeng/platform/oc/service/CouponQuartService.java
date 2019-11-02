package com.junfeng.platform.oc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.oc.entity.CouponQuart;

/**
 * 优惠券定时任务触发表
 *
 * @author wangjf
 * @date 2019-09-25 10:47:52
 */
public interface CouponQuartService extends IService<CouponQuart> {

  /**
   * 优惠券定时任务触发表简单分页查询
   * @param couponQuart 优惠券定时任务触发表
   * @return
   */
  IPage<CouponQuart> getCouponQuartPage(Page<CouponQuart> page, CouponQuart couponQuart);
  
  /**
   * 根据ID修改优惠券状态
   * @param id
   * @return
   * @author:Wangjf
   * @createTime:2019年9月25日 下午4:52:26
   */
  Boolean couponUpdateState(Long id);


}
