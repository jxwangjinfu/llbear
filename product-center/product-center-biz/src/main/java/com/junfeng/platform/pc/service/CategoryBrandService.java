package com.junfeng.platform.pc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.pc.api.entity.CategoryBrand;

/**
 * 品牌类目关联表
 *
 * @author lw
 * @date 2019-10-14 15:19:51
 */
public interface CategoryBrandService extends IService<CategoryBrand> {

  /**
   * 品牌类目关联表简单分页查询
   * @param categoryBrand 品牌类目关联表
   * @return
   */
  IPage<CategoryBrand> getCategoryBrandPage(Page<CategoryBrand> page, CategoryBrand categoryBrand);


}
