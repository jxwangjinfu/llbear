package com.junfeng.platform.sc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.sc.entity.Shop;

/**
 * 店铺信息
 *
 * @author lw
 * @date 2019-10-21 13:49:30
 */
public interface ShopService extends IService<Shop> {

  /**
   * 店铺信息简单分页查询
   * @param shop 店铺信息
   * @return
   */
  IPage<Shop> getShopPage(Page<Shop> page, Shop shop);

  /**
   * 新增店铺
   * @author: lw
   * @createTime: 2019/10/21 14:17
   * @param shop
   * @return R
   */
  boolean AddShop(Shop shop);
/**
 * 功能描述: 修改并验证
 * @author: lw
 * @createTime: 2019/10/31 10:26
 * @param entity
 * @return boolean
 */
	boolean updateByIdWithCheck(Shop entity);
}
