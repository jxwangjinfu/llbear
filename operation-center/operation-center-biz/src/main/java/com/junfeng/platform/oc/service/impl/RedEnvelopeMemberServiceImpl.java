package com.junfeng.platform.oc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.oc.api.vo.OrderVO;
import com.junfeng.platform.oc.api.vo.RedEnvelopeGenerateMessageVO;
import com.junfeng.platform.oc.config.mq.RabbitConst;
import com.junfeng.platform.oc.entity.RedEnvelope;
import com.junfeng.platform.oc.entity.RedEnvelopeMember;
import com.junfeng.platform.oc.entity.RedEnvelopeMemberDTO;
import com.junfeng.platform.oc.mapper.RedEnvelopeMapper;
import com.junfeng.platform.oc.mapper.RedEnvelopeMemberMapper;
import com.junfeng.platform.oc.service.RedEnvelopeMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 红包会员表
 *
 * @author wangjf
 * @date 2019-10-09 16:37:15
 */
@Slf4j
@Service
public class RedEnvelopeMemberServiceImpl extends ServiceImpl<RedEnvelopeMemberMapper, RedEnvelopeMember>
        implements RedEnvelopeMemberService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RedEnvelopeMapper redEnvelopeMapper;

    /**
     * 红包会员表简单分页查询
     *
     * @param redEnvelopeMember
     *            红包会员表
     * @return
     */
    @Override
    public IPage<RedEnvelopeMember> getRedEnvelopeMemberPage(Page<RedEnvelopeMember> page,
            RedEnvelopeMember redEnvelopeMember) {
        return baseMapper.getRedEnvelopeMemberPage(page, redEnvelopeMember);
    }

    @Override
    public void sendRedEnvelopeGenerateToMq(List<RedEnvelopeGenerateMessageVO> list) {
        list.stream().forEach(item -> {
            rabbitTemplate.convertAndSend(RabbitConst.RED_ENVELOPE_GENERATE_NOTIFY_EXCHANGE,
                    RabbitConst.RED_ENVELOPE_GENERATE_ROUTING_KEY_NOTIFY, item);
        });
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updateRedEnvelope(Long memberId, Long redEnvelopeId) {

        RedEnvelope redEnvelope = getRedEnvelope(redEnvelopeId);
        if (redEnvelope == null) {
            log.error("红包活动无法找到，memberId={}，redEnvelopeId={}", memberId, redEnvelopeId);
            return "红包活动已经结束或不存在";
        }

        RedEnvelopeMember redEnvelopeMember = getRedEnvelopeMember(redEnvelopeId, memberId,
                redEnvelope.getRecipientsNumberLimit());
        if (redEnvelopeMember == null) {
            log.error("红包已经被领取完了或者领取达到了上限，memberId={}，redEnvelopeId={}", memberId, redEnvelopeId);
            return "红包已经被领取完了或者您的领取达到了上限";
        }
        redEnvelopeMember.setState(2);
        redEnvelopeMember.setMcMemberId(memberId);
        updateById(redEnvelopeMember);

        return "获取红包成功,过期时间" + redEnvelope.getUseEndTime();
    }

    /**
     * 获取会员的可用红包
     *
     * @param orderVO
     * @return java.util.List<com.junfeng.platform.oc.entity.RedEnvelopeMemberDTO>
     * @author: wangjf
     * @createTime: 2019/10/16 16:37
     */
    @Override
    public List<RedEnvelopeMemberDTO> getRedEnvelopeList(OrderVO orderVO) {
        List<RedEnvelopeMemberDTO> list = baseMapper.getRedEnvelopeList(orderVO);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        return list;
    }

    /**
     * 销售销红包
     *
     * @param idList
     * @param orderNo
     * @return java.lang.Boolean
     * @author: wangjf
     * @createTime: 2019/10/17 14:56
     */
    @Override
    public Boolean updateRedEnvelopeMember(List<Long> idList, String orderNo) {
        RedEnvelopeMember redEnvelopeMember = new RedEnvelopeMember();
        redEnvelopeMember.setState(3);
        redEnvelopeMember.setOrderNo(orderNo);
        boolean update = update(redEnvelopeMember,
                Wrappers.<RedEnvelopeMember> query().lambda().in(RedEnvelopeMember::getId, idList));
        return update;
    }

    /**
     * 锁定红包
     *
     * @param idList
     * @param orderNo
     * @return java.lang.Boolean
     * @author: wangjf
     * @createTime: 2019/10/18 11:28
     */
    @Override
    public Boolean lockRedEnvelopeMember(List<Long> idList, String orderNo) {
        RedEnvelopeMember redEnvelopeMember = new RedEnvelopeMember();
        redEnvelopeMember.setState(10);
        redEnvelopeMember.setOrderNo(orderNo);
        boolean update = update(redEnvelopeMember,
                Wrappers.<RedEnvelopeMember> query().lambda().in(RedEnvelopeMember::getId, idList));
        return update;
    }

    /**
     * 销红包
     *
     * @param memberId
     * @param orderNo
     * @return java.lang.Boolean
     * @author: wangjf
     * @createTime: 2019/10/18 11:35
     */
    @Override
    public Boolean completeEnvelopeMember(Long memberId, String orderNo) {

        RedEnvelopeMember redEnvelopeMember = new RedEnvelopeMember();
        redEnvelopeMember.setState(3);
        boolean update = update(redEnvelopeMember,
                Wrappers.<RedEnvelopeMember> query().lambda().eq(RedEnvelopeMember::getOrderNo, orderNo));
        return update;
    }

    /**
     * 退还红包
     *
     * @param memberId
     * @param orderNo
     * @return java.lang.Boolean
     * @author: wangjf
     * @createTime: 2019/10/18 11:35
     */
    @Override
    public Boolean cancelEnvelopeMember(Long memberId, String orderNo) {

        RedEnvelopeMember redEnvelopeMember = new RedEnvelopeMember();
        redEnvelopeMember.setState(2);
        redEnvelopeMember.setOrderNo(null);
        boolean update = update(redEnvelopeMember,
                Wrappers.<RedEnvelopeMember> query().lambda().eq(RedEnvelopeMember::getOrderNo, orderNo));
        return update;
    }

    /**
     * 查询红包活动
     *
     * @author: wangjf
     * @createTime: 2019/10/10 15:09
     * @param redEnvelopeId
     * @return com.junfeng.platform.oc.entity.RedEnvelope
     */
    private RedEnvelope getRedEnvelope(Long redEnvelopeId) {
        RedEnvelope redEnvelope = redEnvelopeMapper.selectById(redEnvelopeId);
        if (redEnvelope != null) {
            return redEnvelope;
        }
        return null;

    }

    /**
     * 获取可用红包
     *
     * @author: wangjf
     * @createTime: 2019/10/10 15:34
     * @param redEnvelopeId
     * @param memberId
     * @param recipientsNumberLimit
     * @return com.junfeng.platform.oc.entity.RedEnvelopeMember
     */
    private RedEnvelopeMember getRedEnvelopeMember(Long redEnvelopeId, Long memberId, Integer recipientsNumberLimit) {

        // 获取未领取
        List<RedEnvelopeMember> list = baseMapper.selectList(
                Wrappers.<RedEnvelopeMember> query().lambda().eq(RedEnvelopeMember::getOcRedEnvelopeId, redEnvelopeId)
                        .isNull(RedEnvelopeMember::getMcMemberId).eq(RedEnvelopeMember::getState, 1));
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        Integer count = baseMapper.selectCount(
                Wrappers.<RedEnvelopeMember> query().lambda().eq(RedEnvelopeMember::getMcMemberId, memberId));

        if (recipientsNumberLimit > 0 && count >= recipientsNumberLimit) {
            return null;
        }
        return list.get(0);

    }

}
