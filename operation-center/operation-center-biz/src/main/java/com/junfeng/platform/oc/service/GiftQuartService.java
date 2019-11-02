package com.junfeng.platform.oc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.oc.entity.GiftQuart;

/**
 * 赠品定时任务触发表
 *
 * @author wangjf
 * @date 2019-10-12 14:55:40
 */
public interface GiftQuartService extends IService<GiftQuart> {

    /**
     * 赠品定时任务触发表简单分页查询
     *
     * @param giftQuart
     *            赠品定时任务触发表
     * @return
     */
    IPage<GiftQuart> getGiftQuartPage(Page<GiftQuart> page, GiftQuart giftQuart);

    /**
     * 修改赠品状态
     *
     * @author: wangjf
     * @createTime: 2019/10/12 15:28
     * @param id
     * @return void
     */
    Boolean giftUpdateState(Long id);

}
