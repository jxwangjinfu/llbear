package com.junfeng.platform.pc.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.pc.api.entity.Brand;
import com.junfeng.platform.pc.mapper.BrandMapper;
import com.junfeng.platform.pc.service.BrandService;

/**
 * 品牌表
 *
 * @author lw
 * @date 2019-10-14 15:18:47
 */
@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

  /**
   * 品牌表简单分页查询
   * @param brand 品牌表
   * @return
   */
  @Override
  public IPage<Brand> getBrandPage(Page<Brand> page, Brand brand){
      return baseMapper.getBrandPage(page,brand);
  }
}
