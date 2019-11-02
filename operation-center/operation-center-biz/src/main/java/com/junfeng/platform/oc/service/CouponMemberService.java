package com.junfeng.platform.oc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.oc.api.vo.CouponGenerateMessageVO;
import com.junfeng.platform.oc.api.vo.OrderVO;
import com.junfeng.platform.oc.entity.CouponMember;
import com.junfeng.platform.oc.entity.CouponMemberDTO;

import java.util.List;

/**
 * 优惠券会员表
 *
 * @author wangjf
 * @date 2019-09-29 16:51:58
 */
public interface CouponMemberService extends IService<CouponMember> {

    /**
     * 优惠券会员表简单分页查询
     *
     * @param couponMember
     *            优惠券会员表
     * @return
     */
    IPage<CouponMember> getCouponMemberPage(Page<CouponMember> page, CouponMember couponMember);

    /**
     * 优惠券信息发送至mq中
     *
     * @author: wangjf
     * @createTime: 2019/9/30 14:00
     * @param list
     * @return void
     */
    void sendCouponGenerateToMq(List<CouponGenerateMessageVO> list);

    /**
     * 会员领取优惠券
     *
     * @author: wangjf
     * @createTime: 2019/9/30 15:09
     * @param memberId
     * @param couponId
     * @return java.lang.Boolean
     */
    String updateCoupon(Long memberId, Long couponId);

    /**
     * 根据订单信息获取会员优惠券列表
     *
     * @author: wangjf
     * @createTime: 2019/10/16 15:17
     * @param orderVO
     * @return java.util.List<com.junfeng.platform.oc.entity.CouponMemberDTO>
     */
    List<CouponMemberDTO> getCouponList(OrderVO orderVO);

    /**
     * 更新优惠券状态
     *
     * @author: wangjf
     * @createTime: 2019/10/17 14:28
     * @param idList
     * @param orderNo
     * @return java.lang.Boolean
     */
    Boolean updateCouponMember(List<Long> idList, String orderNo);

    /**
     * 锁定优惠券
     *
     * @author: wangjf
     * @createTime: 2019/10/18 11:21
     * @param idList
     * @param orderNo
     * @return java.lang.Boolean
     */
    Boolean lockCouponMember(List<Long> idList, String orderNo);

    /**
     * 销优惠券
     *
     * @author: wangjf
     * @createTime: 2019/10/18 11:31
     * @param memberId
     * @param orderNo
     * @return java.lang.Boolean
     */
    Boolean completeCouponMember(Long memberId, String orderNo);

    /**
     * 返回优惠券
     *
     * @author: wangjf
     * @createTime: 2019/10/18 11:31
     * @param memberId
     * @param orderNo
     * @return java.lang.Boolean
     */
    Boolean cancelCouponMember(Long memberId, String orderNo);

}
