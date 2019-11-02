package com.junfeng.wc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.wc.entity.Open;
import com.junfeng.wc.service.OpenService;
import com.junfeng.wc.mapper.OpenMapper;
import org.springframework.stereotype.Service;

/**
 * 开放平台
 *
 * @author daiysh
 * @date 2019-09-25 10:55:42
 */
@Service("openService")
public class OpenServiceImpl extends ServiceImpl<OpenMapper, Open> implements OpenService {

  /**
   * 开放平台简单分页查询
   * @param open 开放平台
   * @return
   */
  @Override
  public IPage<Open> getOpenPage(Page<Open> page, Open open){
      return baseMapper.getOpenPage(page,open);
  }

}
