package com.junfeng.platform.sc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.sc.entity.ShopUserRelation;
import com.junfeng.platform.sc.service.ShopUserRelationService;
import com.junfeng.platform.sc.mapper.ShopUserRelationMapper;
import org.springframework.stereotype.Service;

/**
 * 店铺用户关联信息
 *
 * @author lw
 * @date 2019-10-21 13:49:45
 */
@Service("shopUserRelationService")
public class ShopUserRelationServiceImpl extends ServiceImpl<ShopUserRelationMapper, ShopUserRelation> implements ShopUserRelationService {

  /**
   * 店铺用户关联信息简单分页查询
   * @param shopUserRelation 店铺用户关联信息
   * @return
   */
  @Override
  public IPage<ShopUserRelation> getShopUserRelationPage(Page<ShopUserRelation> page, ShopUserRelation shopUserRelation){
      return baseMapper.getShopUserRelationPage(page,shopUserRelation);
  }

}
