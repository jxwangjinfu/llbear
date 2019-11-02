package com.junfeng.platform.oc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.oc.entity.RedEnvelopeConfig;
import com.junfeng.platform.oc.service.RedEnvelopeConfigService;
import com.junfeng.platform.oc.mapper.RedEnvelopeConfigMapper;
import org.springframework.stereotype.Service;

/**
 * 红包使用配置表
 *
 * @author wangjf
 * @date 2019-10-09 14:24:01
 */
@Service("redEnvelopeConfigService")
public class RedEnvelopeConfigServiceImpl extends ServiceImpl<RedEnvelopeConfigMapper, RedEnvelopeConfig> implements RedEnvelopeConfigService {

  /**
   * 红包使用配置表简单分页查询
   * @param redEnvelopeConfig 红包使用配置表
   * @return
   */
  @Override
  public IPage<RedEnvelopeConfig> getRedEnvelopeConfigPage(Page<RedEnvelopeConfig> page, RedEnvelopeConfig redEnvelopeConfig){
      return baseMapper.getRedEnvelopeConfigPage(page,redEnvelopeConfig);
  }

}
