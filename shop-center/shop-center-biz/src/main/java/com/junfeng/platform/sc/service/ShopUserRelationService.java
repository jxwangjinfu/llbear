package com.junfeng.platform.sc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.sc.entity.ShopUserRelation;

/**
 * 店铺用户关联信息
 *
 * @author lw
 * @date 2019-10-21 13:49:45
 */
public interface ShopUserRelationService extends IService<ShopUserRelation> {

  /**
   * 店铺用户关联信息简单分页查询
   * @param shopUserRelation 店铺用户关联信息
   * @return
   */
  IPage<ShopUserRelation> getShopUserRelationPage(Page<ShopUserRelation> page, ShopUserRelation shopUserRelation);


}
