package com.junfeng.platform.pc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.pc.api.entity.SkuStock;

import java.util.List;

/**
 * sku库存表
 *
 * @author lw
 * @date 2019-10-14 15:41:03
 */
public interface SkuStockService extends IService<SkuStock> {

  /**
   * sku库存表简单分页查询
   * @param skuStock sku库存表
   * @return
   */
  IPage<SkuStock> getSkuStockPage(Page<SkuStock> page, SkuStock skuStock);

  /**
   * 功能描述: 增加库存
   * @author: lw
   * @createTime: 2019/10/17 15:47
   * @param skuStockList
   * @return boolean
   */
  boolean addSkuStock(List<SkuStock> skuStockList);

  /**
   * 功能描述: 减少库存
   * @author: lw
   * @createTime: 2019/10/17 15:47
   * @param skuStockList
   * @return boolean
   */
  boolean reduceSkuStock(List<SkuStock> skuStockList);
}
