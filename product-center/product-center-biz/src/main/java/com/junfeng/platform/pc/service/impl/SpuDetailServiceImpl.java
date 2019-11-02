package com.junfeng.platform.pc.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.pc.api.entity.SpuDetail;
import com.junfeng.platform.pc.mapper.SpuDetailMapper;
import com.junfeng.platform.pc.service.SpuDetailService;

/**
 * spu详细信息
 *
 * @author lw
 * @date 2019-10-14 15:43:56
 */
@Service("spuDetailService")
public class SpuDetailServiceImpl extends ServiceImpl<SpuDetailMapper, SpuDetail> implements SpuDetailService {

  /**
   * spu详细信息简单分页查询
   * @param spuDetail spu详细信息
   * @return
   */
  @Override
  public IPage<SpuDetail> getSpuDetailPage(Page<SpuDetail> page, SpuDetail spuDetail){
      return baseMapper.getSpuDetailPage(page,spuDetail);
  }
}
