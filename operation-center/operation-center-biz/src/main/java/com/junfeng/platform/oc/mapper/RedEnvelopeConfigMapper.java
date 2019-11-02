package com.junfeng.platform.oc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.oc.entity.RedEnvelopeConfig;
import org.apache.ibatis.annotations.Param;

/**
 * 红包使用配置表
 *
 * @author wangjf
 * @date 2019-10-09 14:24:01
 */
public interface RedEnvelopeConfigMapper extends BaseMapper<RedEnvelopeConfig> {
  /**
    * 红包使用配置表简单分页查询
    * @param redEnvelopeConfig 红包使用配置表
    * @return
    */
  IPage<RedEnvelopeConfig> getRedEnvelopeConfigPage(Page page, @Param("redEnvelopeConfig") RedEnvelopeConfig redEnvelopeConfig);


}
