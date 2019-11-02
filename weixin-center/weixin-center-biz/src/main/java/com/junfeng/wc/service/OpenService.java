package com.junfeng.wc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.wc.entity.Open;

/**
 * 开放平台
 *
 * @author daiysh
 * @date 2019-09-25 10:55:42
 */
public interface OpenService extends IService<Open> {

  /**
   * 开放平台简单分页查询
   * @param open 开放平台
   * @return
   */
  IPage<Open> getOpenPage(Page<Open> page, Open open);


}
