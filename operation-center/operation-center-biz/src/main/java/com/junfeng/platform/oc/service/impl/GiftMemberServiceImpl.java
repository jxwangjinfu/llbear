package com.junfeng.platform.oc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.oc.api.vo.GiftGenerateMessageVO;
import com.junfeng.platform.oc.config.mq.RabbitConst;
import com.junfeng.platform.oc.entity.Gift;
import com.junfeng.platform.oc.entity.GiftMember;
import com.junfeng.platform.oc.mapper.GiftMapper;
import com.junfeng.platform.oc.mapper.GiftMemberMapper;
import com.junfeng.platform.oc.service.GiftMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 赠品会员表
 *
 * @author wangjf
 * @date 2019-10-12 14:09:37
 */
@Service
@Slf4j
public class GiftMemberServiceImpl extends ServiceImpl<GiftMemberMapper, GiftMember> implements GiftMemberService {
    @Autowired
    private GiftMapper giftMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 赠品会员表简单分页查询
     *
     * @param giftMember
     *            赠品会员表
     * @return
     */
    @Override
    public IPage<GiftMember> getGiftMemberPage(Page<GiftMember> page, GiftMember giftMember) {
        return baseMapper.getGiftMemberPage(page, giftMember);
    }

    /**
     * 获取礼品
     *
     * @author: wangjf
     * @createTime: 2019/10/18 16:25
     * @param giftId
     * @return com.junfeng.platform.oc.entity.Gift
     */
    private Gift getGift(Long giftId) {

        Gift gift = giftMapper.selectById(giftId);
        if (gift != null) {
            return gift;
        }
        return null;
    }

    /**
     * 领取赠品
     *
     * @param memberId
     * @param giftId
     * @return java.lang.String
     * @author: wangjf
     * @createTime: 2019/10/12 15:24
     */
    @Override
    public String updateGift(Long memberId, Long giftId) {

        Gift gift = getGift(giftId);
        if (gift == null) {
            log.error("礼品活动无法找到，memberId={}，giftId={}", memberId, giftId);
            return "礼品活动已经结束或不存在";
        }

        GiftMember giftMember = getGiftMember(giftId, memberId, gift.getRecipientsNumberLimit());
        if (giftMember == null) {
            log.error("礼品已经被领取完了或者领取达到了上限，memberId={}，giftId={}", memberId, giftId);
            return "礼品已经被领取完了或者您的领取达到了上限";
        }
        giftMember.setState(2);
        giftMember.setMcMemberId(memberId);
        updateById(giftMember);

        return "获取礼品成功,过期时间" + gift.getUseEndTime();
    }

    /**
     * 礼品消息发送至mq
     *
     * @param list
     * @return void
     * @author: wangjf
     * @createTime: 2019/10/23 15:32
     */
    @Override
    public void sendGiftGenerateToMq(List<GiftGenerateMessageVO> list) {
        list.forEach(item -> {
            rabbitTemplate.convertAndSend(RabbitConst.GIFT_GENERATE_NOTIFY_EXCHANGE,
                    RabbitConst.GIFT_GENERATE_ROUTING_KEY_NOTIFY, item);

        });
    }

    /**
     * 查询可用礼品
     *
     * @author: wangjf
     * @createTime: 2019/10/18 16:33
     * @param giftId
     * @param memberId
     * @param recipientsNumberLimit
     * @return com.junfeng.platform.oc.entity.GiftMember
     */
    private GiftMember getGiftMember(Long giftId, Long memberId, Integer recipientsNumberLimit) {

        // 获取未领取
        List<GiftMember> list = baseMapper.selectList(Wrappers.<GiftMember> query().lambda()
                .eq(GiftMember::getOcGiftId, giftId).isNull(GiftMember::getMcMemberId).eq(GiftMember::getState, 1));
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        Integer count = baseMapper
                .selectCount(Wrappers.<GiftMember> query().lambda().eq(GiftMember::getMcMemberId, memberId));

        if (recipientsNumberLimit > 0 && count >= recipientsNumberLimit) {
            return null;
        }
        return list.get(0);

    }

}
