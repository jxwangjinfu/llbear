package com.junfeng.platform.oc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.oc.entity.BuyGift;
import org.apache.ibatis.annotations.Param;

/**
 * 买赠表
 *
 * @author wangjf
 * @date 2019-10-14 15:09:35
 */
public interface BuyGiftMapper extends BaseMapper<BuyGift> {
  /**
    * 买赠表简单分页查询
    * @param buyGift 买赠表
    * @return
    */
  IPage<BuyGift> getBuyGiftPage(Page page, @Param("buyGift") BuyGift buyGift);


}
