package com.junfeng.platform.oc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.oc.entity.Gift;

/**
 * 赠品表
 *
 * @author wangjf
 * @date 2019-10-12 14:09:23
 */
public interface GiftService extends IService<Gift> {

    /**
     * 赠品表简单分页查询
     *
     * @param gift
     *            赠品表
     * @return
     */
    IPage<Gift> getGiftPage(Page<Gift> page, Gift gift);

    /**
     * 保存赠品
     *
     * @author: wangjf
     * @createTime: 2019/10/12 15:07
     * @param gift
     * @return java.lang.Boolean
     */
    Boolean saveGift(Gift gift);

    /**
     * 修改状态
     *
     * @author: wangjf
     * @createTime: 2019/10/15 16:44
     * @param id
     * @param state
     * @return java.lang.Boolean
     */
    Boolean updateState(Long id, Integer state);

}
