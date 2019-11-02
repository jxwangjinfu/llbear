package com.junfeng.wc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.wc.entity.Mp;
import com.junfeng.wc.service.MpService;
import com.junfeng.wc.mapper.MpMapper;
import org.springframework.stereotype.Service;

/**
 * 公众号
 *
 * @author daiysh
 * @date 2019-09-25 10:53:00
 */
@Service("mpService")
public class MpServiceImpl extends ServiceImpl<MpMapper, Mp> implements MpService {

  /**
   * 公众号简单分页查询
   * @param mp 公众号
   * @return
   */
  @Override
  public IPage<Mp> getMpPage(Page<Mp> page, Mp mp){
      return baseMapper.getMpPage(page,mp);
  }

}
