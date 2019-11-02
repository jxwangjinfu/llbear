package com.junfeng.platform.oc.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.oc.api.vo.CouponGenerateMessageVO;
import com.junfeng.platform.oc.api.vo.OrderVO;
import com.junfeng.platform.oc.config.mq.RabbitConst;
import com.junfeng.platform.oc.entity.*;
import com.junfeng.platform.oc.mapper.CouponMapper;
import com.junfeng.platform.oc.mapper.CouponMemberMapper;
import com.junfeng.platform.oc.service.CouponMemberService;
import com.junfeng.platform.oc.util.type.CouponTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 优惠券会员表
 *
 * @author wangjf
 * @date 2019-09-29 16:51:58
 */
@Slf4j
@Service
public class CouponMemberServiceImpl extends ServiceImpl<CouponMemberMapper, CouponMember>
        implements CouponMemberService {

    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 优惠券会员表简单分页查询
     *
     * @param couponMember
     *            优惠券会员表
     * @return
     */
    @Override
    public IPage<CouponMember> getCouponMemberPage(Page<CouponMember> page, CouponMember couponMember) {
        return baseMapper.getCouponMemberPage(page, couponMember);
    }

    /**
     * 优惠券生成信息发送至mq
     *
     * @author: wangjf
     * @createTime: 2019/9/30 14:04
     * @param list
     * @return void
     */
    @Override
    public void sendCouponGenerateToMq(List<CouponGenerateMessageVO> list) {

        list.stream().forEach(item -> {
            rabbitTemplate.convertAndSend(RabbitConst.COUPON_GENERATE_NOTIFY_EXCHANGE,
                    RabbitConst.COUPON_GENERATE_ROUTING_KEY_NOTIFY, item);
        });

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updateCoupon(Long memberId, Long couponId) {

        Coupon coupon = getCouponState(couponId);

        if (coupon == null) {
            log.error("优惠券活动无法找到，memberId={}，couponId={}", memberId, couponId);
            return "优惠券领取失败,活动不存在或者已结束";
        }

        //领取优惠券时可以获取多少金额
        int money = 0;
        if (coupon.getTypeCode() == CouponTypeEnum.FULLMINUS.getCode()) {
            JsonCouponFullMinus jsonCouponFullMinus = JSONUtil.toBean(coupon.getDiscountContext(),
                    JsonCouponFullMinus.class);
            money = jsonCouponFullMinus.getMinusAmount();
        } else if (coupon.getTypeCode() == CouponTypeEnum.RANDOM.getCode()) {
            JsonCouponRandom jsonCouponRandom = JSONUtil.toBean(coupon.getDiscountContext(), JsonCouponRandom.class);
            money = jsonCouponRandom.getStart();
        }

        CouponMember couponMember = getCouponMember(couponId, memberId, coupon.getRecipientsNumberLimit());

        if (couponMember == null) {
            log.error("优惠券活动无法找到，memberId={}，couponId={}", memberId, couponId);
            return "优惠券领取失败，无优惠券";
        }

        couponMember.setMcMemberId(memberId);
        couponMember.setState(2);
		couponMember.setMoney(money);
        updateById(couponMember);
        return "获取优惠券成功,金额为 "+money+" 分,过期时间" + coupon.getUseEndTime();

    }

    /**
     * 根据订单信息获取会员优惠券列表
     *
     * @param orderVO
     * @return java.util.List<com.junfeng.platform.oc.entity.CouponMemberDTO>
     * @author: wangjf
     * @createTime: 2019/10/16 15:17
     */
    @Override
    public List<CouponMemberDTO> getCouponList(OrderVO orderVO) {
        List<CouponMemberDTO> list = baseMapper.getCouponList(orderVO);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        list.forEach(item -> {
            if (item.getTypeCode() == CouponTypeEnum.FULLMINUS.getCode()) {
                item.setJsonCouponFullMinus(JSONUtil.toBean(item.getDiscountContext(), JsonCouponFullMinus.class));
            } else if (item.getTypeCode() == CouponTypeEnum.DISCOUNT.getCode()) {
                item.setJsonCouponDiscount(JSONUtil.toBean(item.getDiscountContext(), JsonCouponDiscount.class));
            } else if (item.getTypeCode() == CouponTypeEnum.RANDOM.getCode()) {
                item.setJsonCouponRandom(JSONUtil.toBean(item.getDiscountContext(), JsonCouponRandom.class));
            } else {
            }

        });
        return list;
    }

    /**
     * 更新优惠券状态(更新为使用状态)
     *
     * @param idList
     * @param orderNo
     * @return java.lang.Boolean
     * @author: wangjf
     * @createTime: 2019/10/17 14:28
     */
    @Override
    public Boolean updateCouponMember(List<Long> idList, String orderNo) {
        CouponMember couponMember = new CouponMember();
        couponMember.setState(3);
        couponMember.setOrderNo(orderNo);
        boolean update = update(couponMember, Wrappers.<CouponMember> query().lambda().in(CouponMember::getId, idList));

        return update;
    }

    /**
     * 锁定优惠券
     *
     * @param idList
     * @param orderNo
     * @return java.lang.Boolean
     * @author: wangjf
     * @createTime: 2019/10/18 11:21
     */
    @Override
    public Boolean lockCouponMember(List<Long> idList, String orderNo) {

        CouponMember couponMember = new CouponMember();
        couponMember.setState(10);
        couponMember.setOrderNo(orderNo);
        boolean update = update(couponMember, Wrappers.<CouponMember> query().lambda().in(CouponMember::getId, idList));

        return update;
    }

    /**
     * 销优惠券
     *
     * @param memberId
     * @param orderNo
     * @return java.lang.Boolean
     * @author: wangjf
     * @createTime: 2019/10/18 11:31
     */
    @Override
    public Boolean completeCouponMember(Long memberId, String orderNo) {
        CouponMember couponMember = new CouponMember();
        couponMember.setState(3);
        boolean update = update(couponMember,
                Wrappers.<CouponMember> query().lambda().eq(CouponMember::getOrderNo, orderNo));

        return update;
    }

    /**
     * 返回优惠券
     *
     * @param memberId
     * @param orderNo
     * @return java.lang.Boolean
     * @author: wangjf
     * @createTime: 2019/10/18 11:31
     */
    @Override
    public Boolean cancelCouponMember(Long memberId, String orderNo) {
        CouponMember couponMember = new CouponMember();
        couponMember.setState(2);
        couponMember.setOrderNo(null);
        boolean update = update(couponMember,
                Wrappers.<CouponMember> query().lambda().eq(CouponMember::getOrderNo, orderNo));

        return update;
    }

    /**
     * 获取优惠券信息
     *
     * @author: wangjf
     * @createTime: 2019/9/30 15:23
     * @param couponId
     * @return com.junfeng.platform.oc.entity.Coupon
     */
    private Coupon getCouponState(Long couponId) {
        Coupon coupon = couponMapper.selectById(couponId);
        if (coupon == null) {
            return null;
        }
        return coupon;
    }

    /**
     * 获取未领取的优惠券
     *
     * @author: wangjf
     * @createTime: 2019/9/30 15:52
     * @param couponId
     * @param memberId
     * @param recipientsNumberLimit
     * @return com.junfeng.platform.oc.entity.CouponMember
     */
    private CouponMember getCouponMember(Long couponId, Long memberId, Integer recipientsNumberLimit) {

        // 获取未领取
        List<CouponMember> list = baseMapper
                .selectList(Wrappers.<CouponMember> query().lambda().eq(CouponMember::getOcCouponId, couponId)
                        .isNull(CouponMember::getMcMemberId).eq(CouponMember::getState, 1));
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        Integer count = baseMapper
                .selectCount(Wrappers.<CouponMember> query().lambda().eq(CouponMember::getMcMemberId, memberId));

        if (recipientsNumberLimit > 0 && count >= recipientsNumberLimit) {
            return null;
        }
        return list.get(0);
    }

}
