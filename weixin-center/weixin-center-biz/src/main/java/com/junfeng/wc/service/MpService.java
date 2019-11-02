package com.junfeng.wc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.wc.entity.Mp;

/**
 * 公众号
 *
 * @author daiysh
 * @date 2019-09-25 10:53:00
 */
public interface MpService extends IService<Mp> {

  /**
   * 公众号简单分页查询
   * @param mp 公众号
   * @return
   */
  IPage<Mp> getMpPage(Page<Mp> page, Mp mp);


}
