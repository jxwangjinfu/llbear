package com.junfeng.platform.oc.service;

import com.google.common.collect.Lists;
import com.junfeng.platform.oc.api.vo.CouponGenerateMessageVO;
import com.junfeng.platform.oc.api.vo.GiftGenerateMessageVO;
import com.junfeng.platform.oc.api.vo.RedEnvelopeGenerateMessageVO;
import com.junfeng.platform.oc.config.mq.RabbitConst;
import com.junfeng.platform.oc.entity.CouponMember;
import com.junfeng.platform.oc.entity.GiftMember;
import com.junfeng.platform.oc.entity.RedEnvelopeMember;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

/**
 * 消息监听器
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/23 14:36
 * @projectName pig
 */
@Slf4j
@Component
public class RabbitListenerService {

    private static final String YHQ_PREFIX = "YHQ";
    private static final String HB_PREFIX = "HB";
    @Autowired
    private RedEnvelopeMemberService redEnvelopeMemberService;
    @Autowired
    private CouponMemberService couponMemberService;
    @Autowired
    private GiftMemberService giftMemberService;

    /**
     * 优惠券生成消息监听
     *
     * @author: wangjf
     * @createTime: 2019/10/23 15:06
     * @param couponGenerateMessageVO
     * @param channel
     * @param deliveryTag
     * @return void
     */
    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = RabbitConst.COUPON_GENERATE_NOTIFY_QUEUE), exchange = @Exchange(value = RabbitConst.COUPON_GENERATE_NOTIFY_EXCHANGE), key = RabbitConst.COUPON_GENERATE_ROUTING_KEY_NOTIFY))
    public void couponGenerateHandler(CouponGenerateMessageVO couponGenerateMessageVO, Channel channel,
            @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws IOException {

        log.info("--------------couponGenerateMessageVO={}", couponGenerateMessageVO);

        List<CouponMember> couponMemberList = getCouponMemberList(couponGenerateMessageVO);

        if (CollectionUtils.isEmpty(couponMemberList)) {
            return;
        }
        couponMemberService.saveBatch(couponMemberList);

        channel.basicAck(deliveryTag, true);
    }

    /**
     * 红包生成消息监听
     *
     * @author: wangjf
     * @createTime: 2019/10/23 15:06
     * @param redEnvelopeGenerateMessageVO
     * @param channel
     * @param deliveryTag
     * @return void
     */
    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = RabbitConst.RED_ENVELOPE_GENERATE_NOTIFY_QUEUE), exchange = @Exchange(value = RabbitConst.RED_ENVELOPE_GENERATE_NOTIFY_EXCHANGE), key = RabbitConst.RED_ENVELOPE_GENERATE_ROUTING_KEY_NOTIFY))
    public void redEnvelopeGenerateHandler(RedEnvelopeGenerateMessageVO redEnvelopeGenerateMessageVO, Channel channel,
            @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws IOException {

        log.info("--------------redEnvelopeGenerateMessageVO={}", redEnvelopeGenerateMessageVO);

        List<RedEnvelopeMember> redEnvelopeMemberList = getRedEnvelopeMemberList(redEnvelopeGenerateMessageVO);

        if (CollectionUtils.isEmpty(redEnvelopeMemberList)) {
            return;
        }
        redEnvelopeMemberService.saveBatch(redEnvelopeMemberList);

        channel.basicAck(deliveryTag, true);
    }

    /**
     * 礼品生成消息监听
     *
     * @author: wangjf
     * @createTime: 2019/10/23 15:30
     * @param giftGenerateMessageVO
     * @param channel
     * @param deliveryTag
     * @return void
     */
    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = RabbitConst.GIFT_GENERATE_NOTIFY_QUEUE), exchange = @Exchange(value = RabbitConst.GIFT_GENERATE_NOTIFY_EXCHANGE), key = RabbitConst.GIFT_GENERATE_ROUTING_KEY_NOTIFY))
    public void giftGenerateHandler(GiftGenerateMessageVO giftGenerateMessageVO, Channel channel,
            @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws IOException {

        log.info("--------------redEnvelopeGenerateMessageVO={}", giftGenerateMessageVO);

        List<GiftMember> giftMemberList = getGiftMemberList(giftGenerateMessageVO);
        if (CollectionUtils.isEmpty(giftMemberList)) {
            return;
        }
        giftMemberService.saveBatch(giftMemberList);
        channel.basicAck(deliveryTag, true);
    }

    /**
     * 功能描述: 通过RedEnvelopeGenerateMessageVO生成RedEnvelopeMember list
     *
     * @author: wangjf
     * @createTime: 2019/9/30 10:36
     * @param redEnvelopeGenerateMessageVO
     * @return java.util.List<com.junfeng.platform.oc.entity.RedEnvelopeMember>
     */
    private List<RedEnvelopeMember> getRedEnvelopeMemberList(
            RedEnvelopeGenerateMessageVO redEnvelopeGenerateMessageVO) {

        List<RedEnvelopeMember> list = Lists.newArrayList();

        for (int i = redEnvelopeGenerateMessageVO.getStartNo(); i <= redEnvelopeGenerateMessageVO.getEndNo(); i++) {
            RedEnvelopeMember redEnvelopeMember = new RedEnvelopeMember();
            redEnvelopeMember.setOcRedEnvelopeId(redEnvelopeGenerateMessageVO.getOcRedEnvelopeId());
            redEnvelopeMember.setRedEnvelopeCode(HB_PREFIX + i);
            redEnvelopeMember.setMoney(redEnvelopeGenerateMessageVO.getMoney());
            redEnvelopeMember.setCreateBy(redEnvelopeGenerateMessageVO.getCreateBy());
            list.add(redEnvelopeMember);
        }
        return list;
    }

    /**
     * 功能描述: 通过CouponGenerateMessageVO生成CouponMember list
     *
     * @author: wangjf
     * @createTime: 2019/9/30 10:36
     * @param couponGenerateMessageVO
     * @return java.util.List<com.junfeng.platform.oc.entity.CouponMember>
     */
    private List<CouponMember> getCouponMemberList(CouponGenerateMessageVO couponGenerateMessageVO) {

        List<CouponMember> list = Lists.newArrayList();

        for (int i = couponGenerateMessageVO.getStartNo(); i <= couponGenerateMessageVO.getEndNo(); i++) {
            CouponMember couponMember = new CouponMember();
            couponMember.setOcCouponId(couponGenerateMessageVO.getOcCouponId());
            couponMember.setCouponCode(YHQ_PREFIX + i);
            couponMember.setCreateBy(couponGenerateMessageVO.getCreateBy());
            list.add(couponMember);
        }
        return list;
    }

    /**
     * 发放礼品
     *
     * @author: wangjf
     * @createTime: 2019/10/23 15:45
     * @param giftGenerateMessageVO
     * @return java.util.List<com.junfeng.platform.oc.entity.GiftMember>
     */
    private List<GiftMember> getGiftMemberList(GiftGenerateMessageVO giftGenerateMessageVO) {

        List<GiftMember> list = Lists.newArrayList();

        for (int i = giftGenerateMessageVO.getStartNo(); i <= giftGenerateMessageVO.getEndNo(); i++) {
            GiftMember giftMember = new GiftMember();
            giftMember.setOcGiftId(giftGenerateMessageVO.getOcGiftId());
            giftMember.setCreateBy(giftGenerateMessageVO.getCreateBy());
            list.add(giftMember);
        }
        return list;
    }
}
