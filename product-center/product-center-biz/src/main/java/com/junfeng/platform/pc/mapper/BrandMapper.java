package com.junfeng.platform.pc.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.pc.api.entity.Brand;

/**
 * 品牌表
 *
 * @author lw
 * @date 2019-10-14 15:18:47
 */
public interface BrandMapper extends BaseMapper<Brand> {
  /**
    * 品牌表简单分页查询
    * @param brand 品牌表
    * @return
    */
  IPage<Brand> getBrandPage(Page page, @Param("brand") Brand brand);


}
