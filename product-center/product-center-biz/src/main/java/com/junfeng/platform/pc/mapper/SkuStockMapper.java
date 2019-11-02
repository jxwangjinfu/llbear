package com.junfeng.platform.pc.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.pc.api.entity.SkuStock;

/**
 * sku库存表
 *
 * @author lw
 * @date 2019-10-14 15:41:03
 */
public interface SkuStockMapper extends BaseMapper<SkuStock> {
  /**
    * sku库存表简单分页查询
    * @param skuStock sku库存表
    * @return
    */
  IPage<SkuStock> getSkuStockPage(Page page, @Param("skuStock") SkuStock skuStock);


}
