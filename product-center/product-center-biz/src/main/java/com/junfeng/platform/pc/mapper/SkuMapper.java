package com.junfeng.platform.pc.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.pc.api.entity.Sku;

/**
 * sku信息
 *
 * @author lw
 * @date 2019-10-12 18:25:42
 */
public interface SkuMapper extends BaseMapper<Sku> {
  /**
    * sku信息简单分页查询
    * @param sku sku信息
    * @return
    */
  IPage<Sku> getSkuPage(Page page, @Param("sku") Sku sku);


}
