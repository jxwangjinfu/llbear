package com.junfeng.platform.sc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.sc.entity.ShopType;
import org.apache.ibatis.annotations.Param;

/**
 * 店铺类型
 *
 * @author lw
 * @date 2019-10-23 14:57:35
 */
public interface ShopTypeMapper extends BaseMapper<ShopType> {
  /**
    * 店铺类型简单分页查询
    * @param shopType 店铺类型
    * @return
    */
  IPage<ShopType> getShopTypePage(Page page, @Param("shopType") ShopType shopType);


}
