package com.junfeng.platform.oc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.oc.entity.RedEnvelopeConfig;

/**
 * 红包使用配置表
 *
 * @author wangjf
 * @date 2019-10-09 14:24:01
 */
public interface RedEnvelopeConfigService extends IService<RedEnvelopeConfig> {

  /**
   * 红包使用配置表简单分页查询
   * @param redEnvelopeConfig 红包使用配置表
   * @return
   */
  IPage<RedEnvelopeConfig> getRedEnvelopeConfigPage(Page<RedEnvelopeConfig> page, RedEnvelopeConfig redEnvelopeConfig);


}
