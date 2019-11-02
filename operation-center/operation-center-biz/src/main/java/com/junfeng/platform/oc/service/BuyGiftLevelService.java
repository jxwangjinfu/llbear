package com.junfeng.platform.oc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.oc.api.vo.OrderVO;
import com.junfeng.platform.oc.entity.BuyGiftLevel;

/**
 * 买赠等级表
 *
 * @author wangjf
 * @date 2019-10-15 14:32:05
 */
public interface BuyGiftLevelService extends IService<BuyGiftLevel> {

    /**
     * 买赠等级表简单分页查询
     *
     * @param buyGiftLevel
     *            买赠等级表
     * @return
     */
    IPage<BuyGiftLevel> getBuyGiftLevelPage(Page<BuyGiftLevel> page, BuyGiftLevel buyGiftLevel);

    /**
     * 获取买赠
     * @author: wangjf
     * @createTime: 2019/10/18 14:42
     * @param orderVO
     * @return com.junfeng.platform.oc.entity.BuyGiftLevel
     */
    BuyGiftLevel getBuyGiftLevel(OrderVO orderVO);

}
