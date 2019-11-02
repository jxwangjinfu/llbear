package com.junfeng.platform.oc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.oc.entity.BuyGiftLevelGoods;

import java.util.List;

/**
 * 买赠等级表
 *
 * @author wangjf
 * @date 2019-10-14 15:09:56
 */
public interface BuyGiftLevelGoodsService extends IService<BuyGiftLevelGoods> {

    /**
     * 买赠等级表简单分页查询
     *
     * @param buyGiftLevelGoods
     *            买赠等级表
     * @return
     */
    IPage<BuyGiftLevelGoods> getBuyGiftLevelGoodsPage(Page<BuyGiftLevelGoods> page,
            BuyGiftLevelGoods buyGiftLevelGoods);

    /**
     * 根据等级ID获取礼物列表
     *
     * @author: wangjf
     * @createTime: 2019/10/18 15:20
     * @param ocBuyGiftLevelId
     * @return java.util.List<com.junfeng.platform.oc.entity.BuyGiftLevelGoods>
     */
    List<BuyGiftLevelGoods> getBuyGiftLevelGoodsList(Long ocBuyGiftLevelId);

}
