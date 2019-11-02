package com.junfeng.platform.sc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.sc.entity.ShopUserRelation;
import org.apache.ibatis.annotations.Param;

/**
 * 店铺用户关联信息
 *
 * @author lw
 * @date 2019-10-21 13:49:45
 */
public interface ShopUserRelationMapper extends BaseMapper<ShopUserRelation> {
  /**
    * 店铺用户关联信息简单分页查询
    * @param shopUserRelation 店铺用户关联信息
    * @return
    */
  IPage<ShopUserRelation> getShopUserRelationPage(Page page, @Param("shopUserRelation") ShopUserRelation shopUserRelation);


}
