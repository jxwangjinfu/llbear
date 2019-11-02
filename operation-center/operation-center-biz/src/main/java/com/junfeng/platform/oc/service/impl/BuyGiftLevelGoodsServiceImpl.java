package com.junfeng.platform.oc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.oc.entity.BuyGiftLevelGoods;
import com.junfeng.platform.oc.service.BuyGiftLevelGoodsService;
import com.junfeng.platform.oc.mapper.BuyGiftLevelGoodsMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 买赠等级表
 *
 * @author wangjf
 * @date 2019-10-14 15:09:56
 */
@Service("buyGiftLevelGoodsService")
public class BuyGiftLevelGoodsServiceImpl extends ServiceImpl<BuyGiftLevelGoodsMapper, BuyGiftLevelGoods>
        implements BuyGiftLevelGoodsService {

    /**
     * 买赠等级表简单分页查询
     *
     * @param buyGiftLevelGoods
     *            买赠等级表
     * @return
     */
    @Override
    public IPage<BuyGiftLevelGoods> getBuyGiftLevelGoodsPage(Page<BuyGiftLevelGoods> page,
            BuyGiftLevelGoods buyGiftLevelGoods) {
        return baseMapper.getBuyGiftLevelGoodsPage(page, buyGiftLevelGoods);
    }

    /**
     * 根据等级ID获取礼物列表
     *
     * @param ocBuyGiftLevelId
     * @return java.util.List<com.junfeng.platform.oc.entity.BuyGiftLevelGoods>
     * @author: wangjf
     * @createTime: 2019/10/18 15:20
     */
    @Override
    public List<BuyGiftLevelGoods> getBuyGiftLevelGoodsList(Long ocBuyGiftLevelId) {

        List<BuyGiftLevelGoods> list = list(Wrappers.<BuyGiftLevelGoods> query().lambda()
                .eq(BuyGiftLevelGoods::getOcBuyGiftLevelId, ocBuyGiftLevelId));
        return list;
    }

}
