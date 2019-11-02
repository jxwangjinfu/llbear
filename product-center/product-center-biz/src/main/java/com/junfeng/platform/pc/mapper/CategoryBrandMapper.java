package com.junfeng.platform.pc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.pc.api.entity.CategoryBrand;
import org.apache.ibatis.annotations.Param;

/**
 * 品牌类目关联表
 *
 * @author lw
 * @date 2019-10-14 15:19:51
 */
public interface CategoryBrandMapper extends BaseMapper<CategoryBrand> {
  /**
    * 品牌类目关联表简单分页查询
    * @param categoryBrand 品牌类目关联表
    * @return
    */
  IPage<CategoryBrand> getCategoryBrandPage(Page page, @Param("categoryBrand") CategoryBrand categoryBrand);


}
