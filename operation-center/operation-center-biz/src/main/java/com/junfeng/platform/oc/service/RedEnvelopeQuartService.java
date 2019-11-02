package com.junfeng.platform.oc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.oc.entity.RedEnvelopeQuart;

/**
 * 红包定时任务触发表
 *
 * @author wangjf
 * @date 2019-10-09 14:24:10
 */
public interface RedEnvelopeQuartService extends IService<RedEnvelopeQuart> {

    /**
     * 红包定时任务触发表简单分页查询
     *
     * @param redEnvelopeQuart
     *            红包定时任务触发表
     * @return
     */
    IPage<RedEnvelopeQuart> getRedEnvelopeQuartPage(Page<RedEnvelopeQuart> page, RedEnvelopeQuart redEnvelopeQuart);

    /**
     * 修改红包活动状态:
     *
     * @author: wangjf
     * @createTime: 2019/10/9 17:19
     * @param id
     * @return java.lang.Boolean
     */
    Boolean redEnvelopeUpdateState(Long id);

}
