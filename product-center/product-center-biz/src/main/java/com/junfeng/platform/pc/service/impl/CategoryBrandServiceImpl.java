package com.junfeng.platform.pc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.pc.api.entity.CategoryBrand;
import com.junfeng.platform.pc.service.CategoryBrandService;
import com.junfeng.platform.pc.mapper.CategoryBrandMapper;
import org.springframework.stereotype.Service;

/**
 * 品牌类目关联表
 *
 * @author lw
 * @date 2019-10-14 15:19:51
 */
@Service("categoryBrandService")
public class CategoryBrandServiceImpl extends ServiceImpl<CategoryBrandMapper, CategoryBrand> implements CategoryBrandService {

  /**
   * 品牌类目关联表简单分页查询
   * @param categoryBrand 品牌类目关联表
   * @return
   */
  @Override
  public IPage<CategoryBrand> getCategoryBrandPage(Page<CategoryBrand> page, CategoryBrand categoryBrand){
      return baseMapper.getCategoryBrandPage(page,categoryBrand);
  }

}
