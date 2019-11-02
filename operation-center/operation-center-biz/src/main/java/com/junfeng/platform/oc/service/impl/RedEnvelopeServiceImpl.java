package com.junfeng.platform.oc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.junfeng.platform.oc.api.vo.RedEnvelopeGenerateMessageVO;
import com.junfeng.platform.oc.constant.OperationCenterConstant;
import com.junfeng.platform.oc.entity.RedEnvelope;
import com.junfeng.platform.oc.entity.RedEnvelopeMember;
import com.junfeng.platform.oc.mapper.RedEnvelopeMapper;
import com.junfeng.platform.oc.mapper.RedEnvelopeMemberMapper;
import com.junfeng.platform.oc.service.QuartzLogService;
import com.junfeng.platform.oc.service.RedEnvelopeMemberService;
import com.junfeng.platform.oc.service.RedEnvelopeService;
import com.junfeng.platform.oc.util.ContextHolderUtil;
import com.junfeng.platform.oc.util.type.QuartzTypeEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 红包
 *
 * @author wangjf
 * @date 2019-10-09 14:23:25
 */
@Service
@AllArgsConstructor
public class RedEnvelopeServiceImpl extends ServiceImpl<RedEnvelopeMapper, RedEnvelope> implements RedEnvelopeService {

    private final RedEnvelopeMemberService redEnvelopeMemberService;
    private final RedEnvelopeMemberMapper redEnvelopeMemberMapper;
    private final QuartzLogService quartzLogService;

    /**
     * 红包简单分页查询
     *
     * @param redEnvelope
     *            红包
     * @return
     */
    @Override
    public IPage<RedEnvelope> getRedEnvelopePage(Page<RedEnvelope> page, RedEnvelope redEnvelope) {
        return baseMapper.getRedEnvelopePage(page, redEnvelope);
    }

    /**
     * 保存红包
     *
     * @author: wangjf
     * @createTime: 2019/10/9 16:52
     * @param redEnvelope
     * @return java.lang.Boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveRedEnvelope(RedEnvelope redEnvelope) {
        save(redEnvelope);
        /**
         * 优惠券启动VO
         */
		quartzLogService.sendQcQuartz(redEnvelope.getId(), QuartzTypeEnum.REDENVELOPE.getCode(),1,redEnvelope.getUseStartTime());
        /**
         * 优惠券关闭VO
         */
		quartzLogService.sendQcQuartz(redEnvelope.getId(), QuartzTypeEnum.REDENVELOPE.getCode(),-1,redEnvelope.getUseEndTime());

        List<RedEnvelopeGenerateMessageVO> list = getRedEnvelopeGenerateMessageVO(redEnvelope.getId(),
                redEnvelope.getPublishNumber(), redEnvelope.getMoney());
        redEnvelopeMemberService.sendRedEnvelopeGenerateToMq(list);

        return Boolean.TRUE;
    }

    /**
     * 修改状态
     *
     * @param id
     * @param state
     * @return java.lang.Boolean
     * @author: wangjf
     * @createTime: 2019/10/15 16:39
     */
    @Override
    public Boolean updateState(Long id, Integer state) {

        RedEnvelope redEnvelope = getById(id);
        if (redEnvelope == null) {
            return Boolean.FALSE;
        }

        RedEnvelope updateObj = new RedEnvelope();

        updateObj.setId(id);
        updateObj.setState(state);
        baseMapper.updateById(updateObj);

        RedEnvelopeMember redEnvelopeMember = new RedEnvelopeMember();
        redEnvelopeMember.setState(state);

        redEnvelopeMemberMapper.update(redEnvelopeMember, Wrappers.<RedEnvelopeMember> query().lambda()
                .eq(RedEnvelopeMember::getOcRedEnvelopeId, redEnvelope.getId()));

        return Boolean.TRUE;
    }

    /**
     * 获取分段生成红包信息
     *
     * @author: wangjf
     * @createTime: 2019/9/30 14:16
     * @param redEnvelopeId
     * @param publishNumber
     * @param money
     * @return java.util.List<com.junfeng.platform.oc.api.vo.RedEnvelopeGenerateMessageVO>
     */
    public List<RedEnvelopeGenerateMessageVO> getRedEnvelopeGenerateMessageVO(long redEnvelopeId, int publishNumber,
            int money) {

        List<RedEnvelopeGenerateMessageVO> list = Lists.newArrayList();

        if (publishNumber > OperationCenterConstant.COUPON_GENERATE_PART_SIZE) {
            // 需要分离的次数
            int length = publishNumber / OperationCenterConstant.COUPON_GENERATE_PART_SIZE;

            for (int i = 0; i < length; i++) {
                RedEnvelopeGenerateMessageVO couponGenerateMessageVO = new RedEnvelopeGenerateMessageVO();
                couponGenerateMessageVO.setStartNo(i * OperationCenterConstant.COUPON_GENERATE_PART_SIZE + 1);
                couponGenerateMessageVO.setEndNo(i * (OperationCenterConstant.COUPON_GENERATE_PART_SIZE));
                couponGenerateMessageVO.setOcRedEnvelopeId(redEnvelopeId);
                couponGenerateMessageVO.setMoney(money);
                couponGenerateMessageVO.setCreateBy(ContextHolderUtil.getUsername());
                list.add(couponGenerateMessageVO);
            }

            // 分离次数后还剩余多少，如果大于0则需要多分离一次
            int flag = publishNumber % OperationCenterConstant.COUPON_GENERATE_PART_SIZE;
            if (flag > 0) {
                RedEnvelopeGenerateMessageVO flagRedEnvelopeGenerateMessageVO = new RedEnvelopeGenerateMessageVO();
                flagRedEnvelopeGenerateMessageVO
                        .setStartNo(length * OperationCenterConstant.COUPON_GENERATE_PART_SIZE + 1);
                flagRedEnvelopeGenerateMessageVO
                        .setEndNo(length * OperationCenterConstant.COUPON_GENERATE_PART_SIZE + flag);
                flagRedEnvelopeGenerateMessageVO.setOcRedEnvelopeId(redEnvelopeId);
                flagRedEnvelopeGenerateMessageVO.setMoney(money);
                flagRedEnvelopeGenerateMessageVO.setCreateBy(ContextHolderUtil.getUsername());
                list.add(flagRedEnvelopeGenerateMessageVO);
            }

        } else {
            RedEnvelopeGenerateMessageVO redEnvelopeGenerateMessageVO = new RedEnvelopeGenerateMessageVO();
            redEnvelopeGenerateMessageVO.setStartNo(1);
            redEnvelopeGenerateMessageVO.setEndNo(publishNumber);
            redEnvelopeGenerateMessageVO.setOcRedEnvelopeId(redEnvelopeId);
            redEnvelopeGenerateMessageVO.setMoney(money);
            redEnvelopeGenerateMessageVO.setCreateBy(ContextHolderUtil.getUsername());
            list.add(redEnvelopeGenerateMessageVO);
        }

        return list;

    }

}
