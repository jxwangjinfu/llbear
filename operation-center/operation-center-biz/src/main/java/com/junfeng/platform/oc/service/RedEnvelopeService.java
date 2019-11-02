package com.junfeng.platform.oc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.oc.entity.RedEnvelope;

/**
 * 红包
 *
 * @author wangjf
 * @date 2019-10-09 14:23:25
 */
public interface RedEnvelopeService extends IService<RedEnvelope> {

    /**
     * 红包简单分页查询
     *
     * @param redEnvelope
     *            红包
     * @return
     */
    IPage<RedEnvelope> getRedEnvelopePage(Page<RedEnvelope> page, RedEnvelope redEnvelope);

    /**
     * 保存红包
     *
     * @author: wangjf
     * @createTime: 2019/10/9 16:50
     * @param redEnvelope
     * @return java.lang.Boolean
     */
    Boolean saveRedEnvelope(RedEnvelope redEnvelope);

    /**
     * 修改状态
     *
     * @author: wangjf
     * @createTime: 2019/10/15 16:39
     * @param id
     * @param state
     * @return java.lang.Boolean
     */
    Boolean updateState(Long id, Integer state);

}
