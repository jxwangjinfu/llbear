package com.junfeng.platform.sc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.sc.entity.Shop;
import org.apache.ibatis.annotations.Param;

/**
 * 店铺信息
 *
 * @author lw
 * @date 2019-10-21 13:49:30
 */
public interface ShopMapper extends BaseMapper<Shop> {
  /**
    * 店铺信息简单分页查询
    * @param shop 店铺信息
    * @return
    */
  IPage<Shop> getShopPage(Page page, @Param("shop") Shop shop);


}
