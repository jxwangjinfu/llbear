package com.junfeng.platform.oc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.oc.entity.BuyGiftLevelGoods;
import org.apache.ibatis.annotations.Param;

/**
 * 买赠等级表
 *
 * @author wangjf
 * @date 2019-10-14 15:09:56
 */
public interface BuyGiftLevelGoodsMapper extends BaseMapper<BuyGiftLevelGoods> {
  /**
    * 买赠等级表简单分页查询
    * @param buyGiftLevelGoods 买赠等级表
    * @return
    */
  IPage<BuyGiftLevelGoods> getBuyGiftLevelGoodsPage(Page page, @Param("buyGiftLevelGoods") BuyGiftLevelGoods buyGiftLevelGoods);


}
