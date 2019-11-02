package com.junfeng.platform.oc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.oc.entity.RedEnvelope;
import org.apache.ibatis.annotations.Param;

/**
 * 红包
 *
 * @author wangjf
 * @date 2019-10-09 14:23:25
 */
public interface RedEnvelopeMapper extends BaseMapper<RedEnvelope> {
  /**
    * 红包简单分页查询
    * @param redEnvelope 红包
    * @return
    */
  IPage<RedEnvelope> getRedEnvelopePage(Page page, @Param("redEnvelope") RedEnvelope redEnvelope);


}
