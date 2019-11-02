package com.junfeng.platform.oc.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.junfeng.platform.mc.api.feign.RemoteMemberService;
import com.junfeng.platform.mc.api.vo.MemberPointsFlowVo;
import com.junfeng.platform.oc.api.result.CouponResult;
import com.junfeng.platform.oc.api.result.OrderResult;
import com.junfeng.platform.oc.api.result.RedEnvelopeResult;
import com.junfeng.platform.oc.api.vo.OrderVO;
import com.junfeng.platform.oc.entity.*;
import com.junfeng.platform.oc.service.*;
import com.junfeng.platform.oc.util.type.CouponTypeEnum;
import com.junfeng.platform.oc.util.type.GiftTypeEnum;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 会员订单相关业务
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/16 17:45
 * @projectName pig
 */
@Service
public class MemberOrderServiceImpl implements MemberOrderService {

    @Autowired
    private CouponConfigService couponConfigService;
    @Autowired
    private RedEnvelopeConfigService redEnvelopeConfigService;
    @Autowired
    private CouponMemberService couponMemberService;
    @Autowired
    private RedEnvelopeMemberService redEnvelopeMemberService;
    @Autowired
    private RemoteMemberService remoteMemberService;
    @Autowired
    private BuyGiftLevelService buyGiftLevelService;
    @Autowired
    private BuyGiftLevelGoodsService buyGiftLevelGoodsService;
    @Autowired
    private GiftMemberService giftMemberService;
    @Autowired
    private BuyGiftMemberService buyGiftMemberService;

    /**
     * 订单预览
     *
     * @param orderVO
     * @return com.junfeng.platform.oc.api.result.OrderResult
     * @author: wangjf
     * @createTime: 2019/10/16 17:43
     */
    @Override
    public OrderResult orderPreview(OrderVO orderVO) {

        OrderResult orderResult = new OrderResult();
        BeanUtil.copyProperties(orderVO, orderResult);

        CouponConfig couponConfig = couponConfigService
                .getOne(Wrappers.<CouponConfig> query().lambda().eq(CouponConfig::getState, 0));
        if (couponConfig == null) {
            // 没配置规则则说明优惠券可以全部叠加
            couponConfig = new CouponConfig();
            couponConfig.setCouponUseRule(0);
        }

        RedEnvelopeConfig redEnvelopeConfig = redEnvelopeConfigService
                .getOne(Wrappers.<RedEnvelopeConfig> query().lambda().eq(RedEnvelopeConfig::getState, 0));

        if (redEnvelopeConfig == null) {
            // 没配置规则则说明红包可以全部叠加
            redEnvelopeConfig = new RedEnvelopeConfig();
            redEnvelopeConfig.setState(0);
        }

        List<CouponMemberDTO> couponList = couponMemberService.getCouponList(orderVO);
        List<RedEnvelopeMemberDTO> redEnvelopeList = redEnvelopeMemberService.getRedEnvelopeList(orderVO);

        // 存放已计算过的优惠券
        List<CouponResult> couponResultList = Lists.newArrayList();
        int totalCouponDiscount = 0;
        if (!CollectionUtils.isEmpty(couponList)) {
            for (CouponMemberDTO couponMemberDTO : couponList) {

                CouponResult couponResult = new CouponResult();
                BeanUtil.copyProperties(couponMemberDTO, couponResult);
                // 计算优惠券可抵扣订单金额
                final int couponDiscount = getCouponDiscount(orderVO.getTotalPrice(), couponMemberDTO);
                couponResult.setDiscount(couponDiscount);
                couponResult.setMoney(couponDiscount);
                totalCouponDiscount += couponDiscount;
                couponResultList.add(couponResult);
                if (couponConfig.getCouponUseRule() == 1) {
                    break;
                }
            }
        }
        orderResult.setListCoupon(couponResultList);
        orderResult.setTotalCoupon(totalCouponDiscount);

        List<RedEnvelopeResult> redEnvelopeResultList = Lists.newArrayList();
        int totalRedEnvelopeDiscount = 0;
        if (!CollectionUtils.isEmpty(redEnvelopeList)) {
            for (RedEnvelopeMemberDTO redEnvelopeMemberDTO : redEnvelopeList) {

                RedEnvelopeResult redEnvelopeResult = new RedEnvelopeResult();
                BeanUtil.copyProperties(redEnvelopeMemberDTO, redEnvelopeResult);

                totalRedEnvelopeDiscount += redEnvelopeMemberDTO.getMoney();
                redEnvelopeResult.setDiscount(redEnvelopeMemberDTO.getMoney());
                redEnvelopeResultList.add(redEnvelopeResult);
                if (redEnvelopeConfig.getRedEnvelopeRule() == 1) {
                    break;
                }

            }
        }
        orderResult.setTotalRedEnvelope(totalRedEnvelopeDiscount);
        orderResult.setListRedEnvelope(redEnvelopeResultList);
        orderResult.setTotalDiscount(totalCouponDiscount + totalRedEnvelopeDiscount);
        return orderResult;
    }

    /**
     * 订单计算
     *
     * @param orderVO
     * @return com.junfeng.platform.oc.api.result.OrderResult
     * @author: wangjf
     * @createTime: 2019/10/17 14:23
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderResult orderCalculate(OrderVO orderVO) {
        OrderResult orderResult = orderPreview(orderVO);
        // 销券
        List<CouponResult> couponResultList = orderResult.getListCoupon();
        if (!CollectionUtils.isEmpty(couponResultList)) {
            final List<Long> couponResultCollect = couponResultList.stream().map(CouponResult::getId)
                    .collect(Collectors.toList());
            couponMemberService.updateCouponMember(couponResultCollect, orderVO.getOrderNo());
        }
        // 销红包
        List<RedEnvelopeResult> redEnvelopeList = orderResult.getListRedEnvelope();
        if (!CollectionUtils.isEmpty(redEnvelopeList)) {
            List<Long> redEnvelopeCollect = redEnvelopeList.stream().map(RedEnvelopeResult::getId)
                    .collect(Collectors.toList());
            redEnvelopeMemberService.updateRedEnvelopeMember(redEnvelopeCollect, orderVO.getOrderNo());
        }

        return orderResult;
    }

    /**
     * 优惠锁定
     *
     * @param orderVO
     * @return com.junfeng.platform.oc.api.result.OrderResult
     * @author: wangjf
     * @createTime: 2019/10/18 11:17
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderResult orderLock(OrderVO orderVO) {
        OrderResult orderResult = orderPreview(orderVO);
        // 锁定券
        List<CouponResult> couponResultList = orderResult.getListCoupon();
        if (!CollectionUtils.isEmpty(couponResultList)) {
            final List<Long> couponResultCollect = couponResultList.stream().map(CouponResult::getId)
                    .collect(Collectors.toList());
            couponMemberService.lockCouponMember(couponResultCollect, orderVO.getOrderNo());
        }
        // 锁定红包
        List<RedEnvelopeResult> redEnvelopeList = orderResult.getListRedEnvelope();
        if (!CollectionUtils.isEmpty(redEnvelopeList)) {
            List<Long> redEnvelopeCollect = redEnvelopeList.stream().map(RedEnvelopeResult::getId)
                    .collect(Collectors.toList());
            redEnvelopeMemberService.lockRedEnvelopeMember(redEnvelopeCollect, orderVO.getOrderNo());
        }
        return orderResult;
    }

    /**
     * 销券和销红包
     *
     * @param memberId
     * @param orderNo
     * @return java.lang.Boolean
     * @author: wangjf
     * @createTime: 2019/10/18 11:18
     */
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean orderComplete(Long memberId, String orderNo) {

        couponMemberService.completeCouponMember(memberId, orderNo);
        redEnvelopeMemberService.completeEnvelopeMember(memberId, orderNo);
        return Boolean.TRUE;
    }

    /**
     * 订单取消，返回优惠券和红包
     *
     * @param memberId
     * @param orderNo
     * @return java.lang.Boolean
     * @author: wangjf
     * @createTime: 2019/10/18 11:18
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean orderCancel(Long memberId, String orderNo) {

        couponMemberService.cancelCouponMember(memberId, orderNo);
        redEnvelopeMemberService.cancelEnvelopeMember(memberId, orderNo);
        return Boolean.TRUE;
    }

    /**
     * 订单买赠
     *
     * @param orderVO
     * @return java.lang.Boolean
     * @author: wangjf
     * @createTime: 2019/10/18 14:37
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean orderBuyGift(OrderVO orderVO) {

        BuyGiftLevel buyGiftLevel = buyGiftLevelService.getBuyGiftLevel(orderVO);
        if (buyGiftLevel == null) {
            return Boolean.TRUE;
        }
        // 订单积分发放
        if (buyGiftLevel.getPoints() > 0) {

            MemberPointsFlowVo memberPointsFlowVo = new MemberPointsFlowVo();
            memberPointsFlowVo.setMemberId(orderVO.getMemberId());
            memberPointsFlowVo.setPoints(buyGiftLevel.getPoints());
            memberPointsFlowVo.setOperationType("1");
            memberPointsFlowVo.setPointsType("1");
            memberPointsFlowVo.setRemark("订单编号:" + orderVO.getOrderNo() + ",购物获取积分");
            // 会员加积分,调用会员中心的远程服务
			//TODO
            //remoteMemberService.addPointsflow(memberPointsFlowVo, SecurityConstants.FROM_IN);
        }
        List<BuyGiftLevelGoods> buyGiftLevelGoodsList = buyGiftLevelGoodsService
                .getBuyGiftLevelGoodsList(buyGiftLevel.getId());
        if (CollectionUtils.isEmpty(buyGiftLevelGoodsList)) {
            return Boolean.TRUE;
        }
        for (BuyGiftLevelGoods buyGiftLevelGoods : buyGiftLevelGoodsList) {

            if (buyGiftLevelGoods.getType() == GiftTypeEnum.COUPON.getCode()) {
                // 优惠券发放
                sendMemberCoupon(orderVO.getMemberId(), buyGiftLevelGoods.getTypeId(),
                        buyGiftLevelGoods.getGiftNumber());
            } else if (buyGiftLevelGoods.getType() == GiftTypeEnum.REDENVELOPE.getCode()) {
                // 红包发放
                sendMemberRedEnvelope(orderVO.getMemberId(), buyGiftLevelGoods.getTypeId(),
                        buyGiftLevelGoods.getGiftNumber());

            } else if (buyGiftLevelGoods.getType() == GiftTypeEnum.GIFT.getCode()) {
                // 礼品发放
                sendMemberGift(orderVO.getMemberId(), buyGiftLevelGoods.getTypeId(), buyGiftLevelGoods.getGiftNumber());
            } else {

            }

        }
        // 保存买赠信息
        BuyGiftMember buyGiftMember = new BuyGiftMember();
        buyGiftMember.setMcMemberId(orderVO.getMemberId());
        buyGiftMember.setOrderNo(orderVO.getOrderNo());
        buyGiftMember.setOcBuyGiftId(buyGiftLevel.getOcBuyGiftId());
        buyGiftMemberService.save(buyGiftMember);
        return Boolean.TRUE;
    }

    /**
     * 发放优惠券
     *
     * @author: wangjf
     * @createTime: 2019/10/18 15:35
     * @param memberId
     * @param couponId
     * @param giftNumber
     */
    private void sendMemberCoupon(Long memberId, Long couponId, Integer giftNumber) {

        for (int i = 0; i < giftNumber; i++) {
            couponMemberService.updateCoupon(memberId, couponId);
        }

    }

    /**
     * 发放红包
     *
     * @author: wangjf
     * @createTime: 2019/10/18 15:36
     * @param memberId
     * @param redEnvelopeId
     * @param giftNumber
     */
    private void sendMemberRedEnvelope(Long memberId, Long redEnvelopeId, Integer giftNumber) {
        for (int i = 0; i < giftNumber; i++) {
            redEnvelopeMemberService.updateRedEnvelope(memberId, redEnvelopeId);
        }
    }

    /**
     * 发放礼物
     *
     * @author: wangjf
     * @createTime: 2019/10/18 15:38
     * @param memberId
     * @param giftId
     * @param giftNumber
     * @return java.lang.Boolean
     */
    private void sendMemberGift(Long memberId, Long giftId, Integer giftNumber) {
        for (int i = 0; i < giftNumber; i++) {
            giftMemberService.updateGift(memberId, giftId);
        }
    }

    /**
     * 优惠券计算
     *
     * @author: wangjf
     * @createTime: 2019/10/17 10:02
     * @param totalPrice
     * @param couponMemberDTO
     * @return java.lang.Integer
     */
    private int getCouponDiscount(Long totalPrice, CouponMemberDTO couponMemberDTO) {

        if (couponMemberDTO.getTypeCode() == CouponTypeEnum.FULLMINUS.getCode()
                || couponMemberDTO.getTypeCode() == CouponTypeEnum.RANDOM.getCode()) {
            return couponMemberDTO.getMoney();
        } else if (couponMemberDTO.getTypeCode() == CouponTypeEnum.DISCOUNT.getCode()) {
            // 折扣券计算方法
            JsonCouponDiscount jsonCouponDiscount = couponMemberDTO.getJsonCouponDiscount();
            final int discount = (int)(jsonCouponDiscount.getDiscountRate() * totalPrice / 100);
            return discount <= jsonCouponDiscount.getMaxDiscount() ? discount : jsonCouponDiscount.getMaxDiscount();
        }
        return 0;
    }

}
