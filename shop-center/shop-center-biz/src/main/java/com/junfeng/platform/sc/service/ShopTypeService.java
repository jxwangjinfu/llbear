package com.junfeng.platform.sc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.sc.entity.ShopType;

/**
 * 店铺类型
 *
 * @author lw
 * @date 2019-10-23 14:57:35
 */
public interface ShopTypeService extends IService<ShopType> {

  /**
   * 店铺类型简单分页查询
   * @param shopType 店铺类型
   * @return
   */
  IPage<ShopType> getShopTypePage(Page<ShopType> page, ShopType shopType);

   boolean saveWithCheck(ShopType shopType);
}
