package com.junfeng.platform.oc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.oc.api.vo.GiftGenerateMessageVO;
import com.junfeng.platform.oc.entity.GiftMember;

import java.util.List;

/**
 * 赠品会员表
 *
 * @author wangjf
 * @date 2019-10-12 14:09:37
 */
public interface GiftMemberService extends IService<GiftMember> {

    /**
     * 赠品会员表简单分页查询
     *
     * @param giftMember
     *            赠品会员表
     * @return
     */
    IPage<GiftMember> getGiftMemberPage(Page<GiftMember> page, GiftMember giftMember);

    /**
     * 领取赠品
     *
     * @author: wangjf
     * @createTime: 2019/10/12 15:24
     * @param memberId
     * @param giftId
     * @return java.lang.String
     */
    String updateGift(Long memberId, Long giftId);

    /**
     * 礼品消息发送至mq
     * @author: wangjf
     * @createTime: 2019/10/23 15:32
     * @param list
     * @return void
     */
    void sendGiftGenerateToMq(List<GiftGenerateMessageVO> list);

}
