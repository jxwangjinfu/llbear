package com.junfeng.wc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.wc.entity.Miniapp;
import com.junfeng.wc.service.MiniappService;
import com.junfeng.wc.mapper.MiniappMapper;
import org.springframework.stereotype.Service;

/**
 * 小程序
 *
 * @author daiysh
 * @date 2019-09-25 10:49:30
 */
@Service("miniappService")
public class MiniappServiceImpl extends ServiceImpl<MiniappMapper, Miniapp> implements MiniappService {

  /**
   * 小程序简单分页查询
   * @param miniapp 小程序
   * @return
   */
  @Override
  public IPage<Miniapp> getMiniappPage(Page<Miniapp> page, Miniapp miniapp){
      return baseMapper.getMiniappPage(page,miniapp);
  }

}
