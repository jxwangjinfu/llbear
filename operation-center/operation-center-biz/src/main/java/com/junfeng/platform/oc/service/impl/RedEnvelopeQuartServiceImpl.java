package com.junfeng.platform.oc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.oc.entity.RedEnvelope;
import com.junfeng.platform.oc.entity.RedEnvelopeMember;
import com.junfeng.platform.oc.entity.RedEnvelopeQuart;
import com.junfeng.platform.oc.mapper.RedEnvelopeMapper;
import com.junfeng.platform.oc.mapper.RedEnvelopeMemberMapper;
import com.junfeng.platform.oc.mapper.RedEnvelopeQuartMapper;
import com.junfeng.platform.oc.service.RedEnvelopeQuartService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 红包定时任务触发表
 *
 * @author wangjf
 * @date 2019-10-09 14:24:10
 */
@Service

public class RedEnvelopeQuartServiceImpl extends ServiceImpl<RedEnvelopeQuartMapper, RedEnvelopeQuart>
        implements RedEnvelopeQuartService {

    @Autowired
    private RedEnvelopeMapper redEnvelopeMapper;
    @Autowired
    private RedEnvelopeMemberMapper redEnvelopeMemberMapper;

    /**
     * 红包定时任务触发表简单分页查询
     *
     * @param redEnvelopeQuart
     *            红包定时任务触发表
     * @return
     */
    @Override
    public IPage<RedEnvelopeQuart> getRedEnvelopeQuartPage(Page<RedEnvelopeQuart> page,
            RedEnvelopeQuart redEnvelopeQuart) {
        return baseMapper.getRedEnvelopeQuartPage(page, redEnvelopeQuart);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean redEnvelopeUpdateState(Long id) {
        RedEnvelopeQuart redEnvelopeQuart = getById(id);
        if (redEnvelopeQuart == null) {
            return false;
        }
        RedEnvelope redEnvelope = new RedEnvelope();
        redEnvelope.setId(redEnvelopeQuart.getRedEnvelopeId());
        redEnvelope.setState(redEnvelopeQuart.getRedEnvelopeState());
        redEnvelopeMapper.updateById(redEnvelope);
        RedEnvelopeQuart updateObj = new RedEnvelopeQuart();
        updateObj.setId(id);
        // 1代表结束
        updateObj.setState("1");
        baseMapper.updateById(updateObj);

        RedEnvelopeMember redEnvelopeMember = new RedEnvelopeMember();
        redEnvelopeMember.setState(redEnvelopeQuart.getRedEnvelopeState());

        redEnvelopeMemberMapper.update(redEnvelopeMember, Wrappers.<RedEnvelopeMember> query().lambda()
                .eq(RedEnvelopeMember::getOcRedEnvelopeId, redEnvelope.getId()));

        return Boolean.TRUE;
    }

}
