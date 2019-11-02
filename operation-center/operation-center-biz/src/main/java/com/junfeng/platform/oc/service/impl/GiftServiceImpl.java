package com.junfeng.platform.oc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.junfeng.platform.oc.api.vo.GiftGenerateMessageVO;
import com.junfeng.platform.oc.constant.OperationCenterConstant;
import com.junfeng.platform.oc.entity.Gift;
import com.junfeng.platform.oc.entity.GiftMember;
import com.junfeng.platform.oc.mapper.GiftMapper;
import com.junfeng.platform.oc.mapper.GiftMemberMapper;
import com.junfeng.platform.oc.service.GiftMemberService;
import com.junfeng.platform.oc.service.GiftService;
import com.junfeng.platform.oc.service.QuartzLogService;
import com.junfeng.platform.oc.util.ContextHolderUtil;
import com.junfeng.platform.oc.util.type.QuartzTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 赠品表
 *
 * @author wangjf
 * @date 2019-10-12 14:09:23
 */
@Service("giftService")
public class GiftServiceImpl extends ServiceImpl<GiftMapper, Gift> implements GiftService {

	@Autowired
    private GiftMemberMapper giftMemberMapper;
	@Autowired
    private QuartzLogService quartzLogService;
	@Autowired
    private GiftMemberService giftMemberService;

    /**
     * 赠品表简单分页查询
     *
     * @param gift
     *            赠品表
     * @return
     */
    @Override
    public IPage<Gift> getGiftPage(Page<Gift> page, Gift gift) {
        return baseMapper.getGiftPage(page, gift);
    }

    /**
     * 保存赠品活动
     *
     * @author: wangjf
     * @createTime: 2019/10/12 15:08
     * @param gift
     * @return java.lang.Boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveGift(Gift gift) {

        // 保存赠品活动
        save(gift);
        // 发送开始定时任务至qc
        quartzLogService.sendQcQuartz(gift.getId(), QuartzTypeEnum.GIFT.getCode(), 1, gift.getUseStartTime());
        // 发送结束定时任务至qc
        quartzLogService.sendQcQuartz(gift.getId(), QuartzTypeEnum.GIFT.getCode(), -1, gift.getUseEndTime());
        List<GiftGenerateMessageVO> list = getGenerateMessageVO(gift.getId(), gift.getPublishNumber());
        giftMemberService.sendGiftGenerateToMq(list);
        // 发送生成赠品消息至mq
        return Boolean.TRUE;
    }

    /**
     * 修改状态
     *
     * @param id
     * @param state
     * @return java.lang.Boolean
     * @author: wangjf
     * @createTime: 2019/10/15 16:44
     */
    @Override
    public Boolean updateState(Long id, Integer state) {

        Gift gift = getById(id);
        if (gift == null) {
            return Boolean.FALSE;
        }

        Gift updateObj = new Gift();

        updateObj.setId(gift.getId());
        updateObj.setState(state);
        baseMapper.updateById(updateObj);

        GiftMember giftMember = new GiftMember();
        giftMember.setState(state);

        giftMemberMapper.update(giftMember, Wrappers.<GiftMember> query().lambda().eq(GiftMember::getOcGiftId, id));

        return Boolean.TRUE;
    }

    /**
     * 功能描述:
     *
     * @author: wangjf
     * @createTime: 2019/10/23 15:36
     * @param giftId
     * @param publishNumber
     * @return java.util.List<com.junfeng.platform.oc.api.vo.GiftGenerateMessageVO>
     */
    private List<GiftGenerateMessageVO> getGenerateMessageVO(long giftId, int publishNumber) {

        List<GiftGenerateMessageVO> list = Lists.newArrayList();

        if (publishNumber > OperationCenterConstant.COUPON_GENERATE_PART_SIZE) {
            // 需要分离的次数
            int length = publishNumber / OperationCenterConstant.COUPON_GENERATE_PART_SIZE;

            for (int i = 0; i < length; i++) {
                GiftGenerateMessageVO couponGenerateMessageVO = new GiftGenerateMessageVO();
                couponGenerateMessageVO.setStartNo(i * OperationCenterConstant.COUPON_GENERATE_PART_SIZE + 1);
                couponGenerateMessageVO.setEndNo(i * (OperationCenterConstant.COUPON_GENERATE_PART_SIZE));
                couponGenerateMessageVO.setOcGiftId(giftId);
                couponGenerateMessageVO.setCreateBy(ContextHolderUtil.getUsername());
                list.add(couponGenerateMessageVO);
            }

            // 分离次数后还剩余多少，如果大于0则需要多分离一次
            int flag = publishNumber % OperationCenterConstant.COUPON_GENERATE_PART_SIZE;
            if (flag > 0) {
                GiftGenerateMessageVO flagCouponGenerateMessageVO = new GiftGenerateMessageVO();
                flagCouponGenerateMessageVO.setStartNo(length * OperationCenterConstant.COUPON_GENERATE_PART_SIZE + 1);
                flagCouponGenerateMessageVO.setEndNo(length * OperationCenterConstant.COUPON_GENERATE_PART_SIZE + flag);
                flagCouponGenerateMessageVO.setOcGiftId(giftId);
                flagCouponGenerateMessageVO.setCreateBy(ContextHolderUtil.getUsername());
                list.add(flagCouponGenerateMessageVO);
            }

        } else {
            GiftGenerateMessageVO couponGenerateMessageVO = new GiftGenerateMessageVO();
            couponGenerateMessageVO.setStartNo(1);
            couponGenerateMessageVO.setEndNo(publishNumber);
            couponGenerateMessageVO.setOcGiftId(giftId);
            couponGenerateMessageVO.setCreateBy(ContextHolderUtil.getUsername());
            list.add(couponGenerateMessageVO);
        }

        return list;

    }

}
