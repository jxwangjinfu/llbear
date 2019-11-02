package com.junfeng.wc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.wc.entity.Miniapp;

/**
 * 小程序
 *
 * @author daiysh
 * @date 2019-09-25 10:49:30
 */
public interface MiniappService extends IService<Miniapp> {

  /**
   * 小程序简单分页查询
   * @param miniapp 小程序
   * @return
   */
  IPage<Miniapp> getMiniappPage(Page<Miniapp> page, Miniapp miniapp);


}
